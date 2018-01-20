package com.koresuniku.wishmasterV4.core.dagger.component

import com.koresuniku.wishmasterV4.core.dagger.module.*
import com.koresuniku.wishmasterV4.ui.thread_list.ThreadListActivity
import com.koresuniku.wishmasterV4.ui.thread_list.ThreadListRecyclerViewAdapter
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