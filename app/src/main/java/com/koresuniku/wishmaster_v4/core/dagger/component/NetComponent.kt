package com.koresuniku.wishmaster_v4.core.dagger.component

import com.koresuniku.wishmaster_v4.core.dagger.module.AppModule
import com.koresuniku.wishmaster_v4.core.dagger.module.NetModule
import com.koresuniku.wishmaster_v4.ui.DashboardActivity
import dagger.Component

/**
 * Created by koresuniku on 03.10.17.
 */

@Component (modules = arrayOf(AppModule::class, NetModule::class))
interface NetComponent {
    fun inject(activity: DashboardActivity)
}