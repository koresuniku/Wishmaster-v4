package com.koresuniku.wishmaster_v4.core.dagger.component

import com.koresuniku.wishmaster_v4.application.WishmasterApplication
import com.koresuniku.wishmaster_v4.core.dagger.module.NetModule
import com.koresuniku.wishmaster_v4.core.dashboard.DashboardPresenter
import dagger.Component
import javax.inject.Singleton

/**
 * Created by koresuniku on 19.01.18.
 */

@Singleton
@Component (modules = [(NetModule::class)])
interface NetComponent {
    fun inject(application: WishmasterApplication)
    fun inject(presenter: DashboardPresenter)
}