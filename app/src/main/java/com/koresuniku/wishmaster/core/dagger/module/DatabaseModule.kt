package com.koresuniku.wishmaster.core.dagger.module

import android.content.Context
import com.koresuniku.wishmaster.core.data.database.DatabaseHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by koresuniku on 10.11.17.
 */

@Module
class DatabaseModule(val context: Context) {

    @Provides
    @Singleton
    fun provideDatabaseHelper(context: Context) = DatabaseHelper(context)
}