package com.koresuniku.wishmaster_v4.ui.dashboard

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.widget.Toolbar
import butterknife.BindView
import butterknife.ButterKnife

import com.koresuniku.wishmaster_v4.R
import com.koresuniku.wishmaster_v4.application.WishmasterApplication
import com.koresuniku.wishmaster_v4.ui.base.BaseDrawerActivity

import javax.inject.Inject

import retrofit2.Retrofit

class DashboardActivity : BaseDrawerActivity() {

    private val LOG_TAG = DashboardActivity::class.java.simpleName

    @Inject lateinit var retrofit: Retrofit

    @BindView(R.id.toolbar) lateinit var mToolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (wishmasterApplication as WishmasterApplication).getNetComponent().inject(this)
        ButterKnife.bind(this)

        setSupportActionBar(mToolbar)
    }

    @LayoutRes override fun provideContentLayoutResource(): Int = R.layout.activity_dashboard
}
