package com.koresuniku.wishmaster.core.dagger.component

import com.koresuniku.wishmaster.core.dagger.module.DatabaseModule
import com.koresuniku.wishmaster.core.dashboard.DashboardPresenter
import com.koresuniku.wishmaster.core.thread_list.ThreadListPresenter
import dagger.Component
import javax.inject.Singleton

/**
 * Created by koresuniku on 19.01.18.
 */

@Singleton
@Component (modules = [(DatabaseModule::class)])
interface DatabaseComponent {
    fun inject(presenter: DashboardPresenter)
    fun inject(presenter: ThreadListPresenter)
}