package com.koresuniku.wishmaster.core.dagger.module

import com.koresuniku.wishmaster.application.SharedPreferencesUIParams
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by koresuniku on 19.01.18.
 */

@Module
class SharedPreferencesUIParamsModule {

    @Provides
    @Singleton
    fun provideSharedPreferencesUIParams(): SharedPreferencesUIParams = SharedPreferencesUIParams()
}