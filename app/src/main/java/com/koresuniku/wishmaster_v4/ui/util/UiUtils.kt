package com.koresuniku.wishmaster_v4.ui.util

import android.content.res.Resources
import android.graphics.PorterDuff
import android.widget.ImageView

/**
 * Created by koresuniku on 03.10.17.
 */

object UiUtils {

    fun setImageViewColorFilter(imageView: ImageView, color: Int) {
        imageView.setColorFilter(
                imageView.resources.getColor(color),
                PorterDuff.Mode.SRC_ATOP)
    }

    fun convertPixelsToDp(px: Float): Float {
        val metrics = Resources.getSystem().displayMetrics
        val dp = px / (metrics.densityDpi / 160f)
        return Math.round(dp).toFloat()
    }

    fun convertDpToPixel(dp: Float): Float {
        val metrics = Resources.getSystem().displayMetrics
        val px = dp * (metrics.densityDpi / 160f)
        return Math.round(px).toFloat()
    }

}