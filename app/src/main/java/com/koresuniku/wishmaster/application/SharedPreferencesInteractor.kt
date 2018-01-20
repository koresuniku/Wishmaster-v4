package com.koresuniku.wishmaster.application

import android.content.Context
import android.util.Log
import com.koresuniku.wishmaster.R
import com.koresuniku.wishmaster.core.domain.client.RetrofitHolder
import com.koresuniku.wishmaster.ui.util.DeviceUtils
import com.koresuniku.wishmaster.ui.util.UiUtils
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.functions.Function5
import io.reactivex.schedulers.Schedulers

/**
 * Created by koresuniku on 14.01.18.
 */

object SharedPreferencesInteractor {

    fun onApplicationCreate(context: Context,
                            sharedPreferencesStorage: SharedPreferencesStorage,
                            retrofitHolder: RetrofitHolder,
                            sharedPreferencesUIParams: SharedPreferencesUIParams) {
        setDefaultImageWidth(context, sharedPreferencesStorage, sharedPreferencesUIParams)
        setRetrofitBaseUrl(sharedPreferencesStorage, retrofitHolder)
    }

//    private fun setDefaultImageWidth(context: Context,
//                                     sharedPreferencesStorage: SharedPreferencesStorage) {
//        sharedPreferencesStorage.readInt(
//                SharedPreferencesKeystore.DEFAULT_IMAGE_WIDTH_IN_DP_KEY,
//                SharedPreferencesKeystore.DEFAULT_IMAGE_WIDTH_IN_DP_DEFAULT)
//                .subscribeOn(Schedulers.io())
//                .filter { value -> value == SharedPreferencesKeystore.DEFAULT_IMAGE_WIDTH_IN_DP_DEFAULT }
//                .subscribe {
//                    if (sharedPreferencesStorage.writeIntSameThread(
//                            SharedPreferencesKeystore.DEFAULT_IMAGE_WIDTH_IN_DP_KEY,
//                            UiUtils.getDefaultImageWidthInDp(
//                                    DeviceUtils.getMaximumDisplayWidthInPx(context), context))) {
//                        computeThreadPostItemWidth(context, sharedPreferencesStorage)
//                                .subscribeOn(Schedulers.computation())
//                                .subscribe()
//                    }
//                }
//    }

    private fun setDefaultImageWidth(context: Context,
                                     sharedPreferencesStorage: SharedPreferencesStorage,
                                     sharedPreferencesUIParams: SharedPreferencesUIParams) {
        readDefaultImageWidthDependentValues(sharedPreferencesStorage)
                .subscribeOn(Schedulers.io())
                .subscribe( { values ->
                    if (values.imageWidth == SharedPreferencesKeystore.DEFAULT_IMAGE_WIDTH_IN_DP_DEFAULT) {
                        val newImageWidth = UiUtils.getDefaultImageWidthInDp(
                                DeviceUtils.getMaximumDisplayWidthInPx(context), context)
                        values.imageWidth = newImageWidth
                        if (sharedPreferencesStorage.writeIntSameThread(
                                SharedPreferencesKeystore.DEFAULT_IMAGE_WIDTH_IN_DP_KEY, newImageWidth)) {
                            computeThreadPostItemWidth(context, sharedPreferencesStorage, sharedPreferencesUIParams)
                                    .subscribeOn(Schedulers.computation())
                                    .subscribe()
                        }
                    } else if (values.threadPostItemHorizontalWidth == SharedPreferencesKeystore.THREAD_POST_ITEM_WIDTH_DEFAULT
                            || values.threadPostItemVerticalWidth == SharedPreferencesKeystore.THREAD_POST_ITEM_WIDTH_DEFAULT
                            || values.threadPostItemSingleImageHorizontalWidth == SharedPreferencesKeystore.THREAD_POST_ITEM_WIDTH_SINGLE_IMAGE_DEFAULT
                            || values.threadPostItemSingleImageVerticalWidth == SharedPreferencesKeystore.THREAD_POST_ITEM_WIDTH_SINGLE_IMAGE_DEFAULT) {
                        computeThreadPostItemWidth(context, sharedPreferencesStorage, sharedPreferencesUIParams)
                                .subscribeOn(Schedulers.computation())
                                .subscribe()
                    } else {
                        Log.d("SPI", values.imageWidth.toString())
                        sharedPreferencesUIParams.imageWidth = values.imageWidth
                        sharedPreferencesUIParams.threadPostItemHorizontalWidth = values.threadPostItemHorizontalWidth
                        sharedPreferencesUIParams.threadPostItemVerticalWidth = values.threadPostItemVerticalWidth
                        sharedPreferencesUIParams.threadPostItemSingleImageHorizontalWidth = values.threadPostItemSingleImageHorizontalWidth
                        sharedPreferencesUIParams.threadPostItemSingleImageVerticalWidth = values.threadPostItemSingleImageVerticalWidth
                    }
                })
    }

    private fun readDefaultImageWidthDependentValues(
            sharedPreferencesStorage: SharedPreferencesStorage) :
            Single<SharedPreferencesUIParams> {
        return Single.zip(
                readDefaultImageWidth(sharedPreferencesStorage),
                readThreadPostItemHorizontalWidth(sharedPreferencesStorage),
                readThreadPostItemVerticalWidth(sharedPreferencesStorage),
                readThreadPostItemSingleImageHorizontalWidth(sharedPreferencesStorage),
                readThreadPostItemSingleImageVerticalWidth(sharedPreferencesStorage),
                Function5({ imageWidth, hw, vw, sihw, sivw ->
                    SharedPreferencesUIParams(imageWidth, hw, vw, sihw, sivw)
                }))
    }

    private fun setRetrofitBaseUrl(sharedPreferencesStorage: SharedPreferencesStorage,
                                   retrofitHolder: RetrofitHolder) {
        sharedPreferencesStorage.readString(
                SharedPreferencesKeystore.BASE_URL_KEY,
                SharedPreferencesKeystore.BASE_URL_DEFAULT)
                .subscribeOn(Schedulers.io())
                .filter { value -> value != SharedPreferencesKeystore.BASE_URL_DEFAULT }
                .subscribe(retrofitHolder::changeBaseUrl)
    }

    private fun computeThreadPostItemWidth(context: Context,
                                           sharedPreferencesStorage: SharedPreferencesStorage,
                                           sharedPreferencesUIParams: SharedPreferencesUIParams): Completable {
        return Completable.create({ e -> kotlin.run {
            var displayWidth = DeviceUtils.getDisplayWidth(context)
            var displayHeight = DeviceUtils.getDisplayHeight(context)

            if (displayWidth < displayHeight) {
                val tempWidth = displayWidth
                displayWidth = displayHeight
                displayHeight = tempWidth
            }

            val sideMargin = context.resources.getDimension(R.dimen.thread_post_side_margin_default).toInt()
            val threadPostItemHorizontalWidth = displayWidth - sideMargin * 2
            val threadPostItemVerticalWidth = displayHeight - sideMargin * 2

            sharedPreferencesStorage.readInt(
                    SharedPreferencesKeystore.DEFAULT_IMAGE_WIDTH_IN_DP_KEY,
                    SharedPreferencesKeystore.DEFAULT_IMAGE_WIDTH_IN_DP_DEFAULT)
                    .subscribeOn(Schedulers.io())
                    .subscribe({ value -> kotlin.run {
                        val threadPostItemSingleImageHorizontal = threadPostItemHorizontalWidth - value - sideMargin
                        val threadPostItemSingleImageVertical = threadPostItemVerticalWidth - value - sideMargin

                        sharedPreferencesStorage.writeIntBackground(
                                SharedPreferencesKeystore.THREAD_POST_ITEM_WIDTH_HORIZONTAL_IN_PX_KEY,
                                threadPostItemHorizontalWidth)
                        sharedPreferencesStorage.writeIntBackground(
                                SharedPreferencesKeystore.THREAD_POST_ITEM_WIDTH_VERTICAL_IN_PX_KEY,
                                threadPostItemVerticalWidth)
                        sharedPreferencesStorage.writeIntBackground(
                                SharedPreferencesKeystore.THREAD_POST_ITEM_WIDTH_SINGLE_IMAGE_HORIZONTAL_IN_PX_KEY,
                                threadPostItemSingleImageHorizontal)
                        sharedPreferencesStorage.writeIntBackground(
                                SharedPreferencesKeystore.THREAD_POST_ITEM_WIDTH_SINGLE_IMAGE_VERTICAL_IN_PX_KEY,
                                threadPostItemSingleImageVertical)

                        sharedPreferencesUIParams.threadPostItemHorizontalWidth = threadPostItemHorizontalWidth
                        sharedPreferencesUIParams.threadPostItemVerticalWidth = threadPostItemVerticalWidth
                        sharedPreferencesUIParams.threadPostItemSingleImageHorizontalWidth = threadPostItemSingleImageHorizontal
                        sharedPreferencesUIParams.threadPostItemSingleImageVerticalWidth = threadPostItemSingleImageVertical

                        e.onComplete()
                    }})
        }})
    }

    private fun readDefaultImageWidth(sharedPreferencesStorage: SharedPreferencesStorage): Single<Int> {
        return Single.create({ e ->
                sharedPreferencesStorage.readInt(
                        SharedPreferencesKeystore.DEFAULT_IMAGE_WIDTH_IN_DP_KEY,
                        SharedPreferencesKeystore.DEFAULT_IMAGE_WIDTH_IN_DP_DEFAULT)
                        .observeOn(Schedulers.io())
                        .subscribe(e::onSuccess)
        })
    }

    private fun readThreadPostItemHorizontalWidth(sharedPreferencesStorage: SharedPreferencesStorage): Single<Int> {
        return Single.create({ e ->
            sharedPreferencesStorage.readInt(
                    SharedPreferencesKeystore.THREAD_POST_ITEM_WIDTH_HORIZONTAL_IN_PX_KEY,
                    SharedPreferencesKeystore.THREAD_POST_ITEM_WIDTH_DEFAULT)
                    .observeOn(Schedulers.io())
                    .subscribe(e::onSuccess)
        })
    }

    private fun readThreadPostItemVerticalWidth(sharedPreferencesStorage: SharedPreferencesStorage): Single<Int> {
        return Single.create({ e ->
            sharedPreferencesStorage.readInt(
                    SharedPreferencesKeystore.THREAD_POST_ITEM_WIDTH_VERTICAL_IN_PX_KEY,
                    SharedPreferencesKeystore.THREAD_POST_ITEM_WIDTH_DEFAULT)
                    .observeOn(Schedulers.io())
                    .subscribe(e::onSuccess)
        })
    }

    private fun readThreadPostItemSingleImageHorizontalWidth(sharedPreferencesStorage: SharedPreferencesStorage): Single<Int> {
        return Single.create({ e ->
            sharedPreferencesStorage.readInt(
                    SharedPreferencesKeystore.THREAD_POST_ITEM_WIDTH_SINGLE_IMAGE_HORIZONTAL_IN_PX_KEY,
                    SharedPreferencesKeystore.THREAD_POST_ITEM_WIDTH_SINGLE_IMAGE_DEFAULT)
                    .observeOn(Schedulers.io())
                    .subscribe(e::onSuccess)
        })
    }

    private fun readThreadPostItemSingleImageVerticalWidth(sharedPreferencesStorage: SharedPreferencesStorage): Single<Int> {
        return Single.create({ e ->
            sharedPreferencesStorage.readInt(
                    SharedPreferencesKeystore.THREAD_POST_ITEM_WIDTH_SINGLE_IMAGE_VERTICAL_IN_PX_KEY,
                    SharedPreferencesKeystore.THREAD_POST_ITEM_WIDTH_SINGLE_IMAGE_DEFAULT)
                    .observeOn(Schedulers.io())
                    .subscribe(e::onSuccess)
        })
    }

//    private data class SharedPreferencesUIParams(val imageWidth: Int,
//                                                        val threadPostItemHorizontalWidth: Int,
//                                                        val threadPostItemVerticalWidth: Int,
//                                                        val threadPostItemSingleImageHorizontalWidth: Int,
//                                                        val threadPostItemSingleImageVerticalWidth: Int)
}