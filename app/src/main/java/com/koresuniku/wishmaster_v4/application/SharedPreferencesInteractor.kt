package com.koresuniku.wishmaster_v4.application

import android.content.Context
import com.koresuniku.wishmaster_v4.ui.util.DeviceUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by koresuniku on 14.01.18.
 */

object SharedPreferencesInteractor {

    fun onApplicationCreate(sharedPreferencesStorage: SharedPreferencesStorage, context: Context) {
        sharedPreferencesStorage.readInt(
                SharedPreferencesKeystore.DEFAULT_IMAGE_WIDTH_IN_PX,
                SharedPreferencesKeystore.DEFAULT_IMAGE_WIDTH_IN_PX_DEFAULT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ value ->
                    if (value == SharedPreferencesKeystore.DEFAULT_IMAGE_WIDTH_IN_PX_DEFAULT) {
                        sharedPreferencesStorage.writeInt(
                                SharedPreferencesKeystore.DEFAULT_IMAGE_WIDTH_IN_PX,
                                DeviceUtils.getMaximumDisplayWidthInPx(context))
                    }})
    }
}