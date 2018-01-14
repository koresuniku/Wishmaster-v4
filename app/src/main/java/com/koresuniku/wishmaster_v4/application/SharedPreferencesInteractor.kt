package com.koresuniku.wishmaster_v4.application

import android.content.Context
import com.koresuniku.wishmaster_v4.ui.util.DeviceUtils
import com.koresuniku.wishmaster_v4.ui.util.UiUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by koresuniku on 14.01.18.
 */

object SharedPreferencesInteractor {

    fun onApplicationCreate(sharedPreferencesStorage: SharedPreferencesStorage, context: Context) {
        sharedPreferencesStorage.readInt(
                SharedPreferencesKeystore.DEFAULT_IMAGE_WIDTH_IN_DP_KEY,
                SharedPreferencesKeystore.DEFAULT_IMAGE_WIDTH_IN_DP_DEFAULT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { value ->
                    if (value == SharedPreferencesKeystore.DEFAULT_IMAGE_WIDTH_IN_DP_DEFAULT) {
                        sharedPreferencesStorage.writeInt(
                                SharedPreferencesKeystore.DEFAULT_IMAGE_WIDTH_IN_DP_KEY,
                                UiUtils.getDefaultImageWidthInDp(
                                        DeviceUtils.getMaximumDisplayWidthInPx(context), context))
                    }}
    }
}