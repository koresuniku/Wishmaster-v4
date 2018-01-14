package com.koresuniku.wishmaster_v4.core.gallery

import android.util.Log
import com.koresuniku.wishmaster_v4.application.SharedPreferencesKeystore
import com.koresuniku.wishmaster_v4.application.SharedPreferencesStorage
import com.koresuniku.wishmaster_v4.core.data.threads.File
import com.koresuniku.wishmaster_v4.ui.util.UiUtils
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Function3
import io.reactivex.schedulers.Schedulers

/**
 * Created by koresuniku on 14.01.18.
 */

object WishmasterImageUtils {

    fun getImageLayoutConfiguration(file: File,
                                    sharedPreferencesStorage: SharedPreferencesStorage,
                                    compositeDisposable: CompositeDisposable): Single<ImageLayoutConfiguration> {
        return Single.create({ e ->
            compositeDisposable.add(getImageConfigurationFromSharedPreferences(
                    sharedPreferencesStorage, compositeDisposable)
                    .subscribe({ config -> e.onSuccess(computeImageLayoutConfiguration(file, config)) }))
        })
    }

    private fun computeImageLayoutConfiguration(file: File,
                                                imageSharedPreferencesConfiguration: ImageSharedPreferencesConfiguration) : ImageLayoutConfiguration {
        val fileWidth = file.width.toInt()
        val fileHeight = file.height.toInt()
        val aspectRatio: Float = fileWidth.toFloat() / fileHeight.toFloat()

        Log.d("WIU", "aspectRatio: $aspectRatio")

        val actualWidth = imageSharedPreferencesConfiguration.width
        var actualHeight = (actualWidth / aspectRatio).toInt()
        val min = imageSharedPreferencesConfiguration.min
        val max = imageSharedPreferencesConfiguration.max

        if (min > actualHeight) actualHeight = min
        if (max < actualHeight) actualHeight = max

        return ImageLayoutConfiguration(actualWidth, actualHeight)
    }

    private fun getImageConfigurationFromSharedPreferences(sharedPreferencesStorage: SharedPreferencesStorage,
                                                           compositeDisposable: CompositeDisposable) : Single<ImageSharedPreferencesConfiguration> {
        return Single.zip(
                getDefaultImageWidthInDpSingle(sharedPreferencesStorage, compositeDisposable).observeOn(Schedulers.computation()),
                getMinimumImageHeightInDpSingle(sharedPreferencesStorage, compositeDisposable).observeOn(Schedulers.computation()),
                getMaximumImageHeightInDpSingle(sharedPreferencesStorage, compositeDisposable).observeOn(Schedulers.computation()),
                Function3({ width, min, max -> ImageSharedPreferencesConfiguration(
                        UiUtils.convertDpToPixel(width.toFloat()).toInt(),
                        UiUtils.convertDpToPixel(min.toFloat()).toInt(),
                        UiUtils.convertDpToPixel(max.toFloat()).toInt())
                }))
    }

    private fun getDefaultImageWidthInDpSingle(sharedPreferencesStorage: SharedPreferencesStorage,
                                               compositeDisposable: CompositeDisposable): Single<Int> {
        return Single.create({ e ->
            compositeDisposable.add(sharedPreferencesStorage.readInt(
                    SharedPreferencesKeystore.DEFAULT_IMAGE_WIDTH_IN_DP_KEY,
                    SharedPreferencesKeystore.DEFAULT_IMAGE_WIDTH_IN_DP_DEFAULT)
                    .observeOn(Schedulers.io()).subscribe(e::onSuccess))
        })
    }

    private fun getMinimumImageHeightInDpSingle(sharedPreferencesStorage: SharedPreferencesStorage,
                                               compositeDisposable: CompositeDisposable): Single<Int> {
        return Single.create({ e ->
            compositeDisposable.add(sharedPreferencesStorage.readInt(
                    SharedPreferencesKeystore.MINIMUM_IMAGE_HEIGHT_IN_DP_KEY,
                    SharedPreferencesKeystore.MINIMUM_IMAGE_HEIGHT_IN_DP_DEFAULT)
                    .observeOn(Schedulers.io()).subscribe(e::onSuccess))
        })
    }

    private fun getMaximumImageHeightInDpSingle(sharedPreferencesStorage: SharedPreferencesStorage,
                                               compositeDisposable: CompositeDisposable): Single<Int> {
        return Single.create({ e ->
            compositeDisposable.add(sharedPreferencesStorage.readInt(
                    SharedPreferencesKeystore.MAXIMUM_IMAGE_HEIGHT_IN_DP_KEY,
                    SharedPreferencesKeystore.MAXIMUM_IMAGE_HEIGHT_IN_DP_DEFAULT)
                    .observeOn(Schedulers.io()).subscribe(e::onSuccess))
        })
    }

    private data class ImageSharedPreferencesConfiguration(val width: Int, val min: Int, val max: Int)
}