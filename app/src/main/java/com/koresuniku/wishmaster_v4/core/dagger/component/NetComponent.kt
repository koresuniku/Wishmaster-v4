package com.koresuniku.wishmaster_v4.core.dagger.component

import com.koresuniku.wishmaster_v4.core.dagger.module.NetModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by koresuniku on 19.01.18.
 */

@Singleton
@Component (modules = [(NetModule::class)])
interface NetComponent {

}