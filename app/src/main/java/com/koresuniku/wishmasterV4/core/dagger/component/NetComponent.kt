package com.koresuniku.wishmasterV4.core.dagger.component

import com.koresuniku.wishmasterV4.application.WishmasterApplication
import com.koresuniku.wishmasterV4.core.dagger.module.NetModule
import com.koresuniku.wishmasterV4.core.dashboard.DashboardPresenter
import com.koresuniku.wishmasterV4.core.thread_list.ThreadListPresenter
import com.koresuniku.wishmasterV4.ui.thread_list.ThreadListRecyclerViewAdapter
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