package com.koresuniku.wishmaster_v4.ui.thread_list

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import butterknife.BindView
import butterknife.ButterKnife
import com.koresuniku.wishmaster_v4.R
import com.koresuniku.wishmaster_v4.application.IntentKeystore
import com.koresuniku.wishmaster_v4.application.SharedPreferencesStorage
import com.koresuniku.wishmaster_v4.core.data.boards.BoardModel
import com.koresuniku.wishmaster_v4.core.thread_list.ThreadListPresenter
import com.koresuniku.wishmaster_v4.core.thread_list.ThreadListView
import com.koresuniku.wishmaster_v4.core.util.text.WishmasterTextUtils
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


    @BindView(R.id.toolbar) lateinit var mToolbar: Toolbar
    @BindView(R.id.loading_layout) lateinit var mLoadingLayout: ViewGroup
    @BindView(R.id.yoba) lateinit var mYobaImage: ImageView
    @BindView(R.id.error_layout) lateinit var mErrorLayout: ViewGroup
    @BindView(R.id.try_again_button) lateinit var mTryAgainButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWishmasterApplication().getThreadListComponent().inject(this)
        ButterKnife.bind(this)
        presenter.bindView(this)

        setupToolbar()

        showLoading(true)
        loadThreads()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        presenter.unbindView()
        overridePendingTransition(R.anim.slide_in_back, R.anim.slide_out_back)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun getBoardId(): String = intent.getStringExtra(IntentKeystore.BOARD_ID_CODE)

    override fun provideContentLayoutResource(): Int = R.layout.activity_thread_list

    private fun setupToolbar() {
        setSupportActionBar(mToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.loading_text)
    }

    private fun setupTitle(boardName: String) {
        supportActionBar?.title = WishmasterTextUtils.obtainBoardIdDashName(getBoardId(), boardName)
    }

    private fun loadThreads() {
        presenter.reloadThreads()
        mCompositeDisposable.add(presenter.getLoadThreadsSingle()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { threadListData ->
                            Log.d(LOG_TAG, "data size: ${threadListData.getThreadList().size}")
                            Log.d(LOG_TAG, "first thread: ${threadListData.getThreadList()[0]}")
                            hideLoading()
                            setupTitle(threadListData.getBoardName())
                        }, { e -> e.printStackTrace(); hideLoading(); showError(e) })
        )
    }

    private fun showLoading(delay: Boolean) {
        mLoadingLayout.visibility = View.VISIBLE
        val rotationAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_rotate_infinitely)
        mYobaImage.postDelayed(
                { mYobaImage.startAnimation(rotationAnimation) },
                if (delay) resources.getInteger(R.integer.slide_anim_duration).toLong() else 0)
    }

    private fun hideLoading() {
        runOnUiThread {
            mLoadingLayout
                    .animate()
                    .alpha(0f)
                    .setDuration(resources.getInteger(R.integer.loading_fade_duration).toLong())
                    .start()
            mLoadingLayout.postDelayed(
                    { mYobaImage.clearAnimation(); mLoadingLayout.visibility = View.GONE },
                    resources.getInteger(R.integer.loading_fade_duration).toLong())
        }
    }

    private fun showError(throwable: Throwable) {
        runOnUiThread {
            mErrorLayout.visibility = View.VISIBLE
            val snackbar = Snackbar.make(mErrorLayout, throwable.message.toString(), Snackbar.LENGTH_INDEFINITE)
            snackbar.setAction(R.string.bljad, { snackbar.dismiss() })
            snackbar.show()
            mTryAgainButton.setOnClickListener {
                snackbar.dismiss()
                hideError()
                showLoading(false)
                loadThreads()
            }
        }
    }

    private fun hideError() {
        runOnUiThread { mErrorLayout.visibility = View.GONE }
    }
}