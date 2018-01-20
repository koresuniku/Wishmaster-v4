package com.koresuniku.wishmaster.core.dashboard

import com.koresuniku.wishmaster.core.base.IActivityMvpView

/**
 * Created by koresuniku on 03.10.17.
 */

interface DashboardView : IActivityMvpView, DashboardSearchView {
    fun showLoading()
}