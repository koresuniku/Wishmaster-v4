package com.koresuniku.wishmaster_v4.core.dashboard

import android.content.Context
import com.koresuniku.wishmaster_v4.core.base.IActivityMvpView
import com.koresuniku.wishmaster_v4.core.base.IMvpView

/**
 * Created by koresuniku on 03.10.17.
 */

interface DashboardView : IActivityMvpView {
    fun showLoading()
    fun launchThreadListActivity(boardId: String)
}