package com.koresuniku.wishmaster_v4.ui.dashboard

import android.content.SharedPreferences
import android.os.Bundle
import android.os.PersistableBundle
import android.support.annotation.LayoutRes
import android.support.design.widget.Snackbar
import android.support.design.widget.TabLayout
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import butterknife.BindView
import butterknife.ButterKnife

import com.koresuniku.wishmaster_v4.R
import com.koresuniku.wishmaster_v4.application.SharedPreferencesManager
import com.koresuniku.wishmaster_v4.core.dashboard.DashboardView
import com.koresuniku.wishmaster_v4.core.dashboard.DashboardPresenter
import com.koresuniku.wishmaster_v4.core.data.boards.BoardsData
import com.koresuniku.wishmaster_v4.ui.base.BaseDrawerActivity
import com.koresuniku.wishmaster_v4.ui.view.widget.DashboardViewPager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import javax.inject.Inject

class DashboardActivity : BaseDrawerActivity(), DashboardView {
    private val LOG_TAG = DashboardActivity::class.java.simpleName

    @Inject lateinit var presenter: DashboardPresenter
    @Inject lateinit var sharedPreferences: SharedPreferences

    @BindView(R.id.toolbar) lateinit var mToolbar: Toolbar
    @BindView(R.id.tab_layout) lateinit var mTabLayout: TabLayout
    @BindView(R.id.loading_layout) lateinit var mLoadingLayout: ViewGroup
    @BindView(R.id.error_layout) lateinit var mErrorLayout: ViewGroup
    @BindView(R.id.yoba) lateinit var mYobaImage: ImageView
    @BindView(R.id.dashboard_viewpager) lateinit var mViewPager: DashboardViewPager
    @BindView(R.id.try_again_button) lateinit var mTryAgainButton: Button

    private lateinit var mViewPagerAdapter: DashboardViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWishmasterApplication().getDashBoardComponent().inject(this)
        ButterKnife.bind(this)
        presenter.bindView(this)

        setSupportActionBar(mToolbar)
        setupViewPager()
        setupTabLayout()

        loadBoards()
    }

    private fun loadBoards() {
        val loadBoardsSingle = presenter.loadBoards().cache()
        mCompositeDisposable.add(loadBoardsSingle
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( { boardsData: BoardsData ->
                    Log.d(LOG_TAG, "received board data: " + boardsData.getBoardList().size)
                    hideLoading()
                }, { throwable: Throwable ->
                    throwable.printStackTrace()
                    hideLoading()
                    showError(throwable)
                }))

    }

    @LayoutRes override fun provideContentLayoutResource(): Int = R.layout.activity_dashboard

    override fun showLoadingBoards() {
        mLoadingLayout.visibility = View.VISIBLE
        mViewPager.setPagingEnabled(false)
        val rotationAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_rotate_infinitely)
        mYobaImage.startAnimation(rotationAnimation)
    }

    private fun hideLoading() {
        mViewPager.setPagingEnabled(true)
        mYobaImage.clearAnimation()
        mLoadingLayout.visibility = View.GONE
    }

    private fun showError(throwable: Throwable) {
        mErrorLayout.visibility = View.VISIBLE
        mViewPager.setPagingEnabled(false)
        val snackbar = Snackbar.make(mErrorLayout, throwable.message.toString(), Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction(R.string.bljad, { snackbar.dismiss() })
        snackbar.show()
        mTryAgainButton.setOnClickListener { snackbar.dismiss(); hideError(); showLoadingBoards(); loadBoards() }
    }

    private fun hideError() {
        mErrorLayout.visibility = View.GONE
    }

    private fun setupTabLayout() {
        mTabLayout.setupWithViewPager(mViewPager)

        mTabLayout.getTabAt(0)?.setIcon(R.drawable.ic_favorite_black_24dp)
        mTabLayout.getTabAt(1)?.setIcon(R.drawable.ic_format_list_bulleted_black_24dp)
        mTabLayout.getTabAt(2)?.setIcon(R.drawable.ic_star_black_24dp)
        mTabLayout.getTabAt(3)?.setIcon(R.drawable.ic_history_black_24dp)
    }

    private fun setupViewPager() {
        mViewPagerAdapter = DashboardViewPagerAdapter(supportFragmentManager)
        mViewPager.adapter = mViewPagerAdapter
        mViewPager.currentItem = sharedPreferences.getInt(
                SharedPreferencesManager.DASHBOARD_PREFFERED_TAB_POSITION,
                SharedPreferencesManager.DASHBOARD_PREFFERED_TAB_POSITION_DEFAULT)
    }
}
