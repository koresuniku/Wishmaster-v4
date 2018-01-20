package com.koresuniku.wishmaster.core.dagger.component

import com.koresuniku.wishmaster.core.dagger.module.*
import com.koresuniku.wishmaster.ui.thread_list.ThreadListActivity
import com.koresuniku.wishmaster.ui.thread_list.ThreadListRecyclerViewAdapter
import dagger.Component
import javax.inject.Singleton

/**
 * Created by koresuniku on 01.01.18.
 */

@Singleton
@Component(modules = [(ThreadListModule::class)])
interface ThreadListComponent {

    fun inject(activity: ThreadListActivity)
    fun inject(threadListAdapterView: ThreadListRecyclerViewAdapter)

}