package com.koresuniku.wishmaster_v4.core.dagger.module

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
* Created by koresuniku on 03.10.17.
*/

@Module
class AppModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideApplication() = application

    @Provides
    @Singleton
    fun provideContext() = application.applicationContext
}