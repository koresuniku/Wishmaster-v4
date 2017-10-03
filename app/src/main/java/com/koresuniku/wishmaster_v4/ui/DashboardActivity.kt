package com.koresuniku.wishmaster_v4.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import com.koresuniku.wishmaster_v4.R
import com.koresuniku.wishmaster_v4.application.WishmasterApplication

import javax.inject.Inject

import retrofit2.Retrofit

class DashboardActivity : AppCompatActivity() {
    private val LOG_TAG = DashboardActivity::class.java.simpleName

    @Inject lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        (application as WishmasterApplication).getNetComponent().inject(this)

        Log.d(LOG_TAG, "retrofit: ${retrofit.baseUrl()}")


    }
}
