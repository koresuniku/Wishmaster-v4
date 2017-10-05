package com.koresuniku.wishmaster_v4.core.dagger.component

import com.koresuniku.wishmaster_v4.core.dagger.module.AppModule
import com.koresuniku.wishmaster_v4.core.dagger.module.NetModule
import com.koresuniku.wishmaster_v4.core.dashboard.DashboardPresenter
import com.koresuniku.wishmaster_v4.ui.dashboard.DashboardActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by koresuniku on 03.10.17.
 */

@Singleton
@Component (modules = arrayOf(AppModule::class, NetModule::class))
interface NetComponent {

    fun inject(presenter: DashboardPresenter)
}