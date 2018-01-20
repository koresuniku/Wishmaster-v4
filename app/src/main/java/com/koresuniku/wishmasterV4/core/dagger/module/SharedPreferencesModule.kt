package com.koresuniku.wishmasterV4.core.dagger.module

import android.content.Context
import com.koresuniku.wishmasterV4.application.SharedPreferencesStorage
import com.koresuniku.wishmasterV4.application.SharedPreferencesStorageImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by koresuniku on 12.11.17.
 */

@Module
class SharedPreferencesModule(val context: Context) {

    @Provides
    @Singleton
    fun provideSharedPreferencesStorage(context: Context): SharedPreferencesStorage =
            SharedPreferencesStorageImpl(context)
}