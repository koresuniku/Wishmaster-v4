package com.koresuniku.wishmaster_v4.core.dagger.component

import com.koresuniku.wishmaster_v4.application.WishmasterApplication
import com.koresuniku.wishmaster_v4.core.dagger.module.AppModule
import com.koresuniku.wishmaster_v4.core.dagger.module.NetModule
import com.koresuniku.wishmaster_v4.core.dagger.module.SharedPreferencesModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by koresuniku on 14.01.18.
 */

@Singleton
@Component (modules = [(AppModule::class), (NetModule::class), (SharedPreferencesModule::class)])
interface ApplicationComponent {

    fun inject(application: WishmasterApplication)
}