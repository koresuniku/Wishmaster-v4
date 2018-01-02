package com.koresuniku.wishmaster_v4.core.thread_list

import android.util.Log
import com.koresuniku.wishmaster_v4.core.base.BaseRxPresenter
import com.koresuniku.wishmaster_v4.core.data.database.DatabaseHelper
import com.koresuniku.wishmaster_v4.core.data.threads.ThreadListData
import com.koresuniku.wishmaster_v4.core.data.threads.ThreadsMapper
import com.koresuniku.wishmaster_v4.core.domain.thread_list_api.ThreadListApiService
import com.koresuniku.wishmaster_v4.core.domain.thread_list_api.ThreadListJsonSchemaCatalogResponse
import com.koresuniku.wishmaster_v4.core.domain.thread_list_api.ThreadListJsonSchemaPageResponse
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by koresuniku on 01.01.18.
 */

class ThreadListPresenter @Inject constructor(): BaseRxPresenter<ThreadListView>() {
    private val LOG_TAG = ThreadListPresenter::class.java.simpleName

    @Inject lateinit var threadListApiService: ThreadListApiService
    @Inject lateinit var databaseHelper: DatabaseHelper

    override fun bindView(mvpView: ThreadListView) {
        super.bindView(mvpView)
        mvpView.getWishmasterApplication().getThreadListComponent().inject(this)
    }

    override fun unbindView() {
        super.unbindView()
        databaseHelper.readableDatabase.close()
    }

    fun loadThreadList(): Single<ThreadListData> {
        return Single.create({ e -> kotlin.run {
            mView?.showLoading()
            compositeDisposable.add(loadThreadListDirectly()
                    .subscribe({ schemaCatalog: ThreadListJsonSchemaCatalogResponse ->
                        if (schemaCatalog.threads.isEmpty()) {
                            Log.d(LOG_TAG, "schemaCatalog threads is empty!")
                            compositeDisposable.add(loadThreadListByPages()
                                    .subscribe(
                                            { schemaByPages -> e.onSuccess(ThreadsMapper.mapPageResponseToThreadListData(schemaByPages)) },
                                            { t -> e.onError(t) }))
                        } else e.onSuccess(ThreadsMapper.mapCatalogResponseToThreadListData(schemaCatalog))
                    }, { t -> e.onError(t) }))
        }})
    }

    private fun loadThreadListDirectly(): Single<ThreadListJsonSchemaCatalogResponse> {
        return Single.create({ e -> kotlin.run {
            mView?.let { compositeDisposable.add(threadListApiService.getThreadsObservable(it.getBoardId())
                    .subscribe({ schema -> e.onSuccess(schema) }, { t -> t.printStackTrace(); e.onError(t) }))
            }
        }})
    }

    private fun loadThreadListByPages(): Single<ThreadListJsonSchemaPageResponse> {
        return Single.create({ e -> kotlin.run { mView?.let {
            val boardId = it.getBoardId()
            val indexResponse = threadListApiService.getThreadsByPageCall(boardId, "index").execute()
            indexResponse.body()?.let {
                Log.d(LOG_TAG, "raw pages: ${it.pages}")
                it.threads = arrayListOf()
                //Абу, почини API!
                for (i in 1 until it.pages.size - 1) {
                    val nextPageResponse = threadListApiService.getThreadsByPageCall(boardId, i.toString()).execute()
                    Log.d(LOG_TAG, "inside pages count: $i, threads: ${nextPageResponse.body()?.threads?.size}")
                    it.threads.addAll(nextPageResponse.body()?.threads ?: emptyList())
                }
                e.onSuccess(it)
            }
        }
        }})
    }
}
