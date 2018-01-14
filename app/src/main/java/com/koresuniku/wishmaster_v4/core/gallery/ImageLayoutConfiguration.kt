package com.koresuniku.wishmaster_v4.core.gallery

/**
 * Created by koresuniku on 14.01.18.
 */

data class ImageLayoutConfiguration(val widthInPx: Int, val imageHeightInPx: Int) {
    override fun toString(): String = "widthInPx: $widthInPx, heightInPx: $imageHeightInPx"
}