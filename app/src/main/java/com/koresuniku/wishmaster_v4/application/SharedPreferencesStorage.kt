package com.koresuniku.wishmaster_v4.application

import android.content.Context
import io.reactivex.Observable

/**
 * Created by koresuniku on 29.11.17.
 */

interface SharedPreferencesStorage {

    fun writeString(key: String, value: String)

    fun readString(key: String, defaultValue: String): Observable<String>

    fun writeInt(key: String, value: Int)

    fun readInt(key: String, defaultValue: Int): Observable<Int>

}