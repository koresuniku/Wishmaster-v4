package com.koresuniku.wishmaster_v4.core.dagger.component

import com.koresuniku.wishmaster_v4.core.dagger.module.AppModule
import com.koresuniku.wishmaster_v4.core.dagger.module.DatabaseModule
import com.koresuniku.wishmaster_v4.core.dagger.module.NetModule
import com.koresuniku.wishmaster_v4.core.dagger.module.ThreadListModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by koresuniku on 01.01.18.
 */

@Singleton
@Component(modules = [(AppModule::class), (ThreadListModule::class), (DatabaseModule::class), (NetModule::class)])
interface ThreadListComponent {
}