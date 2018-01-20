package com.koresuniku.wishmaster.core.dagger.module

import com.koresuniku.wishmaster.core.dashboard.DashboardPresenter
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