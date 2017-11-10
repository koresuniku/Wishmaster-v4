package com.koresuniku.wishmaster_v4.ui.dashboard

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import android.widget.TextView
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
    @BindView(R.id.loading_layout) lateinit var mLoadingLayout: ViewGroup
    @BindView(R.id.yoba_image) lateinit var mYobaImage: ImageView
    @BindView(R.id.loading_boards_text) lateinit var mLoadingBoardsText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWishmasterApplication().getDashBoardComponent().inject(this)
        ButterKnife.bind(this)
        mPresenter.bindView(this)

        setSupportActionBar(mToolbar)

        loadBoards()
//        showLoadingBoards()
    }

    private fun loadBoards() {
        val loadBoardsSingle = mPresenter.loadBoards().cache()
        mCompositeDisposable.add(loadBoardsSingle
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( { boardsData: BoardsData ->
                    Log.d(LOG_TAG, "received board data: " + boardsData.getBoardList().size)
                    hideLoading()
                }, { throwable: Throwable ->
                    throwable.printStackTrace()
                    hideLoading()
                }))

    }

    @LayoutRes override fun provideContentLayoutResource(): Int = R.layout.activity_dashboard

    override fun showLoadingBoards() {
        val rotationAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_rotate_infinitely)
        mYobaImage.startAnimation(rotationAnimation)
    }

    private fun hideLoading() {
        mYobaImage.clearAnimation()
        mYobaImage.setImageDrawable(null)
        mLoadingLayout.visibility = View.GONE
    }
}
