package com.koresuniku.wishmasterV4.core.dagger.module

import com.koresuniku.wishmasterV4.core.thread_list.ThreadListPresenter
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