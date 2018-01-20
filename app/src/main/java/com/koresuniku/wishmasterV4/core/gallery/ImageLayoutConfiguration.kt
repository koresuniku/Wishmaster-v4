package com.koresuniku.wishmasterV4.core.gallery

/**
 * Created by koresuniku on 14.01.18.
 */

data class ImageLayoutConfiguration(val widthInPx: Int, val heightInPx: Int) {
    override fun toString(): String = "widthInPx: $widthInPx, heightInPx: $heightInPx"
}