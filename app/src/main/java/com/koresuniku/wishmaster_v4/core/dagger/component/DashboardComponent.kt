package com.koresuniku.wishmaster_v4.core.dagger.component

import com.koresuniku.wishmaster_v4.core.dagger.module.DashboardModule
import com.koresuniku.wishmaster_v4.ui.dashboard.DashboardActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by koresuniku on 05.10.17.
 */

@Singleton
@Component (modules = arrayOf(DashboardModule::class))
interface DashboardComponent {

    fun inject(activity: DashboardActivity)
}