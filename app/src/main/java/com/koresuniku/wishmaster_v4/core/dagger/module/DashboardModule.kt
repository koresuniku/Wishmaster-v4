package com.koresuniku.wishmaster_v4.core.dagger.module

import com.koresuniku.wishmaster_v4.core.base.BasePresenter
import com.koresuniku.wishmaster_v4.core.base.IMvpView
import com.koresuniku.wishmaster_v4.core.dashboard.DashBoardView
import com.koresuniku.wishmaster_v4.core.dashboard.DashboardPresenter
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
    fun provideDashboardPresenter(presenter: DashboardPresenter): DashboardPresenter = presenter
}