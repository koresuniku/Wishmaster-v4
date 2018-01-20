package com.koresuniku.wishmasterV4.core.dagger.module

import com.koresuniku.wishmasterV4.core.dashboard.DashboardPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by koresuniku on 05.10.17.
 */

@Module
class DashboardModule {

    @Provides
    @Singleton
    fun provideDashboardPresenter(): DashboardPresenter = DashboardPresenter()
}