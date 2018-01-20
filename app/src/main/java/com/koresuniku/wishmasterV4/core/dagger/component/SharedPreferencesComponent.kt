package com.koresuniku.wishmasterV4.core.dagger.component

import com.koresuniku.wishmasterV4.application.WishmasterApplication
import com.koresuniku.wishmasterV4.core.dagger.module.SharedPreferencesModule
import com.koresuniku.wishmasterV4.core.dagger.module.SharedPreferencesUIParamsModule
import com.koresuniku.wishmasterV4.core.dashboard.DashboardPresenter
import com.koresuniku.wishmasterV4.core.thread_list.ThreadListPresenter
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