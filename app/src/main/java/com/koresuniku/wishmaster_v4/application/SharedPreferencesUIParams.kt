package com.koresuniku.wishmaster_v4.application

import javax.inject.Inject

/**
 * Created by koresuniku on 19.01.18.
 */

class SharedPreferencesUIParams @Inject constructor() {
    var imageWidth: Int = SharedPreferencesKeystore.DEFAULT_IMAGE_WIDTH_IN_DP_DEFAULT
    var threadPostItemHorizontalWidth: Int = SharedPreferencesKeystore.THREAD_POST_ITEM_WIDTH_DEFAULT
    var threadPostItemVerticalWidth: Int = SharedPreferencesKeystore.THREAD_POST_ITEM_WIDTH_DEFAULT
    var threadPostItemSingleImageHorizontalWidth: Int = SharedPreferencesKeystore.THREAD_POST_ITEM_WIDTH_SINGLE_IMAGE_DEFAULT
    var threadPostItemSingleImageVerticalWidth: Int = SharedPreferencesKeystore.THREAD_POST_ITEM_WIDTH_SINGLE_IMAGE_DEFAULT

    constructor(iw: Int, hw: Int, vw: Int, sihv: Int, sivw: Int): this() {
        this.imageWidth = iw
        this.threadPostItemHorizontalWidth = hw
        this.threadPostItemVerticalWidth = vw
        this.threadPostItemSingleImageHorizontalWidth = sihv
        this.threadPostItemSingleImageVerticalWidth = sivw
    }

//    fun setImageWidth(imageWidth: Int) {
//        this.imageWidth = imageWidth
//    }

    override fun toString(): String {
        return "imageWidthInDp: $imageWidth, " +
                "threadPostItemHorizontalWidth: $threadPostItemHorizontalWidth, " +
                "threadPostItemVerticalWidth: $threadPostItemVerticalWidth, " +
                "threadPostItemSingleImageHorizontalWidth: $threadPostItemSingleImageHorizontalWidth, " +
                "threadPostItemSingleImageVerticalWidth: $threadPostItemSingleImageVerticalWidth"
    }
}