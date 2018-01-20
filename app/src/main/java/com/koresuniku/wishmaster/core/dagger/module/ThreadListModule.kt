package com.koresuniku.wishmaster.core.dagger.module

import com.koresuniku.wishmaster.core.thread_list.ThreadListPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by koresuniku on 01.01.18.
 */

@Module
class ThreadListModule {

    @Provides
    @Singleton
    fun provideThreadListPresenter(): ThreadListPresenter = ThreadListPresenter()
}