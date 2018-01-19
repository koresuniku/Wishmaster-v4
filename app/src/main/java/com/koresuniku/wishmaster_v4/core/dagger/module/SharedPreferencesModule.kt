package com.koresuniku.wishmaster_v4.core.dagger.module

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.koresuniku.wishmaster_v4.application.SharedPreferencesStorage
import com.koresuniku.wishmaster_v4.application.SharedPreferencesStorageImpl
import dagger.Module
import dagger.Provides
import javax.inject.Scope
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