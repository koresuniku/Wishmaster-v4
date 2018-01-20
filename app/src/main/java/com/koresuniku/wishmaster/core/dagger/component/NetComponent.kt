package com.koresuniku.wishmaster.core.dagger.component

import com.koresuniku.wishmaster.application.WishmasterApplication
import com.koresuniku.wishmaster.core.dagger.module.NetModule
import com.koresuniku.wishmaster.core.dashboard.DashboardPresenter
import com.koresuniku.wishmaster.core.thread_list.ThreadListPresenter
import com.koresuniku.wishmaster.ui.thread_list.ThreadListRecyclerViewAdapter
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
    fun inject(presenter: ThreadListPresenter)
    fun inject(adapter: ThreadListRecyclerViewAdapter)
}