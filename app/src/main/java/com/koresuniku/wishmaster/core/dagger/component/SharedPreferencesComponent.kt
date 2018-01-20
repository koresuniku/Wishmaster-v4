package com.koresuniku.wishmaster.core.dagger.component

import com.koresuniku.wishmaster.application.WishmasterApplication
import com.koresuniku.wishmaster.core.dagger.module.SharedPreferencesModule
import com.koresuniku.wishmaster.core.dagger.module.SharedPreferencesUIParamsModule
import com.koresuniku.wishmaster.core.dashboard.DashboardPresenter
import com.koresuniku.wishmaster.core.thread_list.ThreadListPresenter
import dagger.Component
import javax.inject.Singleton

/**
 * Created by koresuniku on 19.01.18.
 */

@Singleton
@Component (modules = [(SharedPreferencesModule::class), (SharedPreferencesUIParamsModule::class)])
interface SharedPreferencesComponent {
    fun inject(application: WishmasterApplication)
    fun inject(presenter: DashboardPresenter)
    fun inject(presenter: ThreadListPresenter)
}