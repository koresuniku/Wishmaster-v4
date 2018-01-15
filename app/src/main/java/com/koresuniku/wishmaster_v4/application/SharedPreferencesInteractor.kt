package com.koresuniku.wishmaster_v4.application

import android.content.Context
import com.koresuniku.wishmaster_v4.core.domain.client.RetrofitHolder
import com.koresuniku.wishmaster_v4.ui.util.DeviceUtils
import com.koresuniku.wishmaster_v4.ui.util.UiUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by koresuniku on 14.01.18.
 */

object SharedPreferencesInteractor {

    fun onApplicationCreate(context: Context,
                            sharedPreferencesStorage: SharedPreferencesStorage,
                            retrofitHolder: RetrofitHolder) {
        setDefaultImageWidth(context, sharedPreferencesStorage)
        setRetrofitBaseUrl(sharedPreferencesStorage, retrofitHolder)
    }

    private fun setDefaultImageWidth(context: Context,
                                     sharedPreferencesStorage: SharedPreferencesStorage) {
        sharedPreferencesStorage.readInt(
                SharedPreferencesKeystore.DEFAULT_IMAGE_WIDTH_IN_DP_KEY,
                SharedPreferencesKeystore.DEFAULT_IMAGE_WIDTH_IN_DP_DEFAULT)
                .subscribeOn(Schedulers.io())
                .filter { value -> value == SharedPreferencesKeystore.DEFAULT_IMAGE_WIDTH_IN_DP_DEFAULT }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    sharedPreferencesStorage.writeInt(
                            SharedPreferencesKeystore.DEFAULT_IMAGE_WIDTH_IN_DP_KEY,
                            UiUtils.getDefaultImageWidthInDp(
                                    DeviceUtils.getMaximumDisplayWidthInPx(context), context))
                }
    }

    private fun setRetrofitBaseUrl(sharedPreferencesStorage: SharedPreferencesStorage,
                                   retrofitHolder: RetrofitHolder) {
        sharedPreferencesStorage.readString(
                SharedPreferencesKeystore.BASE_URL_KEY,
                SharedPreferencesKeystore.BASE_URL_DEFAULT)
                .subscribeOn(Schedulers.io())
                .filter { value -> value != SharedPreferencesKeystore.BASE_URL_DEFAULT }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(retrofitHolder::changeBaseUrl)
    }
}