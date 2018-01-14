package com.koresuniku.wishmaster_v4.core.dagger.component

import com.koresuniku.wishmaster_v4.core.dagger.module.*
import com.koresuniku.wishmaster_v4.core.thread_list.ThreadListPresenter
import com.koresuniku.wishmaster_v4.ui.thread_list.ThreadListActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by koresuniku on 01.01.18.
 */

@Singleton
@Component(modules = [(AppModule::class), (ThreadListModule::class), (DatabaseModule::class),
    (NetModule::class), (SharedPreferencesModule::class)])
interface ThreadListComponent {

    fun inject(presenter: ThreadListPresenter)

    fun inject(activity: ThreadListActivity)
}