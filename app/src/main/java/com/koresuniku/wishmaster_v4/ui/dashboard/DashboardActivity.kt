package com.koresuniku.wishmaster_v4.ui.dashboard

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.util.Log
import butterknife.BindView
import butterknife.ButterKnife

import com.koresuniku.wishmaster_v4.R
import com.koresuniku.wishmaster_v4.application.WishmasterApplication
import com.koresuniku.wishmaster_v4.ui.base.BaseActivity

import javax.inject.Inject

import retrofit2.Retrofit

class DashboardActivity : BaseActivity() {
    private val LOG_TAG = DashboardActivity::class.java.simpleName

    @Inject lateinit var retrofit: Retrofit

    @BindView(R.id.toolbar) lateinit var mToolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        (application as WishmasterApplication).getNetComponent().inject(this)
        ButterKnife.bind(this)

        setSupportActionBar(mToolbar)
    }
}
