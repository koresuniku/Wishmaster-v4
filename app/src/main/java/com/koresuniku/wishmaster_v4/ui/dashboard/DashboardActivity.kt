package com.koresuniku.wishmaster_v4.ui.dashboard

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.widget.Toolbar
import android.util.Log
import butterknife.BindView
import butterknife.ButterKnife

import com.koresuniku.wishmaster_v4.R
import com.koresuniku.wishmaster_v4.core.dashboard.DashboardView
import com.koresuniku.wishmaster_v4.core.dashboard.DashboardPresenter
import com.koresuniku.wishmaster_v4.core.data.boards.BoardsData
import com.koresuniku.wishmaster_v4.ui.base.BaseDrawerActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import javax.inject.Inject

class DashboardActivity : BaseDrawerActivity(), DashboardView {
    private val LOG_TAG = DashboardActivity::class.java.simpleName

    @Inject lateinit var mPresenter: DashboardPresenter

    @BindView(R.id.toolbar) lateinit var mToolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWishmasterApplication().getDashBoardComponent().inject(this)
        ButterKnife.bind(this)
        mPresenter.bindView(this)

        setSupportActionBar(mToolbar)

        loadBoards()
    }

    private fun loadBoards() {
        val loadBoardsSingle = mPresenter.loadBoards().cache()
        mCompositeDisposable.add(loadBoardsSingle
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( { boardsData: BoardsData ->
                    Log.d(LOG_TAG, "received board data: " + boardsData.getBoardList().size)

                }, { throwable: Throwable -> throwable.printStackTrace() }))

    }

    @LayoutRes override fun provideContentLayoutResource(): Int = R.layout.activity_dashboard
}
