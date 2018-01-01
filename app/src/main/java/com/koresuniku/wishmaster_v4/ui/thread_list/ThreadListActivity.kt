package com.koresuniku.wishmaster_v4.ui.thread_list

import android.os.Bundle
import android.util.Log
import butterknife.ButterKnife
import com.koresuniku.wishmaster_v4.R
import com.koresuniku.wishmaster_v4.application.IntentKeystore
import com.koresuniku.wishmaster_v4.application.SharedPreferencesStorage
import com.koresuniku.wishmaster_v4.core.dashboard.DashboardPresenter
import com.koresuniku.wishmaster_v4.core.thread_list.ThreadListPresenter
import com.koresuniku.wishmaster_v4.core.thread_list.ThreadListView
import com.koresuniku.wishmaster_v4.ui.base.BaseDrawerActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by koresuniku on 01.01.18.
 */

class ThreadListActivity : BaseDrawerActivity(), ThreadListView {
    private val LOG_TAG = ThreadListActivity::class.java.simpleName

    @Inject lateinit var presenter: ThreadListPresenter
    @Inject lateinit var sharedPreferencesStorage: SharedPreferencesStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWishmasterApplication().getThreadListComponent().inject(this)
        ButterKnife.bind(this)
        presenter.bindView(this)

        loadThreads()
    }

    override fun getBoardId(): String = intent.getStringExtra(IntentKeystore.BOARD_ID_CODE)

    override fun provideContentLayoutResource(): Int = R.layout.activity_thread_list

    private fun loadThreads() {
        mCompositeDisposable.add(presenter.loadThreadList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ threadListData -> Log.d(LOG_TAG, "data size: ${threadListData.getThreadList().size}" )}, {})
        )
    }

    override fun showLoading() {

    }
}