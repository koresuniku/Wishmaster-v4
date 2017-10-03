package com.koresuniku.wishmaster_v4.application

import android.app.Application
import com.koresuniku.wishmaster_v4.core.dagger.component.DaggerNetComponent
import com.koresuniku.wishmaster_v4.core.dagger.module.AppModule
import com.koresuniku.wishmaster_v4.core.dagger.module.NetModule
import com.koresuniku.wishmaster_v4.core.dvach.Dvach

/**
 * Created by koresuniku on 03.10.17.
 */

class WishmasterApplication : Application() {

    private lateinit var mDaggerNetComponent: DaggerNetComponent

    override fun onCreate() {
        super.onCreate()

        mDaggerNetComponent = DaggerNetComponent.builder()
                .appModule(AppModule(this))
                .netModule(NetModule(Dvach.BASE_URL))
                .build() as DaggerNetComponent
    }

    fun getNetComponent() = mDaggerNetComponent
}