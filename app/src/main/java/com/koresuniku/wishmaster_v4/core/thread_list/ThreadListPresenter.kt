package com.koresuniku.wishmaster_v4.core.thread_list

import android.util.Log
import com.koresuniku.wishmaster_v4.core.base.BaseRxPresenter
import com.koresuniku.wishmaster_v4.core.data.database.DatabaseHelper
import com.koresuniku.wishmaster_v4.core.data.threads.ThreadListData
import com.koresuniku.wishmaster_v4.core.data.threads.ThreadsMapper
import com.koresuniku.wishmaster_v4.core.domain.thread_list_api.ThreadListApiService
import com.koresuniku.wishmaster_v4.core.domain.thread_list_api.ThreadListJsonSchemaResponse
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
                    .subscribe({ schema: ThreadListJsonSchemaResponse ->
                        if (schema.threads.isEmpty()) {
                            Log.d(LOG_TAG, "schema threads is empty!")
                        } else e.onSuccess(ThreadsMapper.mapResponseToThreadListData(schema))
                    }, { t -> e.onError(t) }))
        }})
    }

    private fun loadThreadListDirectly(): Single<ThreadListJsonSchemaResponse> {
        return Single.create({ e -> kotlin.run {
            mView?.let { compositeDisposable.add(
                    threadListApiService.getThreadsObservable(it.getBoardId())
                            .subscribe(
                                    { schema -> e.onSuccess(schema) },
                                    { t -> t.printStackTrace(); e.onError(t) } ))
            }
        }})
    }
}
