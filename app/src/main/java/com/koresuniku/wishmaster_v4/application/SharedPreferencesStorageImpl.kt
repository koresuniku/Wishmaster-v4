package com.koresuniku.wishmaster_v4.application

import android.content.Context
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by koresuniku on 29.11.17.
 */

class SharedPreferencesStorageImpl @Inject constructor(val context: Context) : SharedPreferencesStorage {

    override fun writeString(key: String, value: String) {
        context.getSharedPreferences(SharedPreferencesKeystore.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
                .edit()
                .putString(key, value)
                .apply()
    }

    override fun readString(key: String, defaultValue: String): Observable<String> {
        return Observable.fromCallable {
            context.getSharedPreferences(SharedPreferencesKeystore.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
                    .getString(key, defaultValue)
        }
    }

    override fun writeInt(key: String, value: Int) {
        context.getSharedPreferences(SharedPreferencesKeystore.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
                .edit()
                .putInt(key, value)
                .apply()
    }

    override fun readInt(key: String, defaultValue: Int): Observable<Int> {
        return Observable.fromCallable {
            context.getSharedPreferences(SharedPreferencesKeystore.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
                    .getInt(key, defaultValue)
        }
    }
}