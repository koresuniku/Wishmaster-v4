package com.koresuniku.wishmaster_v4.application

import android.app.Application
import com.koresuniku.wishmaster_v4.core.dagger.component.DaggerDashboardComponent
import com.koresuniku.wishmaster_v4.core.dagger.component.DaggerNetComponent
import com.koresuniku.wishmaster_v4.core.dagger.module.AppModule
import com.koresuniku.wishmaster_v4.core.dagger.module.DashboardModule
import com.koresuniku.wishmaster_v4.core.dagger.module.NetModule
import com.koresuniku.wishmaster_v4.core.dvach.Dvach

/**
 * Created by koresuniku on 03.10.17.
 */

class WishmasterApplication : Application() {

    private lateinit var mDaggerNetComponent: DaggerNetComponent
    private lateinit var mDaggerDashboardComponent: DaggerDashboardComponent

    override fun onCreate() {
        super.onCreate()

        mDaggerNetComponent = DaggerNetComponent.builder()
                .appModule(AppModule(this))
                .netModule(NetModule(Dvach.BASE_URL))
                .build() as DaggerNetComponent

        mDaggerDashboardComponent = DaggerDashboardComponent.builder()
                .dashboardModule(DashboardModule())
                .build() as DaggerDashboardComponent
    }

    fun getNetComponent() = mDaggerNetComponent

    fun getDashBoardComponent() = mDaggerDashboardComponent
}