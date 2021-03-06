package com.koresuniku.wishmaster.ui.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.koresuniku.wishmaster.R
import com.koresuniku.wishmaster.ui.util.UiUtils

/**
 * Created by koresuniku on 03.10.17.
 */

abstract class BaseDrawerActivity : BaseRxActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupContentView()
        UiUtils.showSystemUI(this)
    }

    private fun setupContentView() {
        val mainLayout = LayoutInflater.from(this).inflate(R.layout.activity_drawer, null, false)
        val contentContainer = mainLayout.findViewById<View>(R.id.content_container) as ViewGroup
        val contentLayout = LayoutInflater.from(this).inflate(provideContentLayoutResource(), null, false)

        contentContainer.addView(contentLayout)
        setContentView(mainLayout)
    }

    @LayoutRes abstract fun provideContentLayoutResource(): Int

}