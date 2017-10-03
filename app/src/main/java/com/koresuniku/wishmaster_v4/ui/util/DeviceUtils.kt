package com.koresuniku.wishmaster_v4.ui.util

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.graphics.Point
import android.os.Build
import android.view.KeyCharacterMap
import android.view.KeyEvent
import android.view.ViewConfiguration

/**
 * Created by koresuniku on 03.10.17.
 */

object DeviceUtils {

    fun deviceHasNavigationBar(context: Context): Boolean {
        val hasMenuKey = ViewConfiguration.get(context).hasPermanentMenuKey()
        val hasBackKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK)
        return !hasMenuKey && !hasBackKey
    }

    fun sdkIsLollipopOrHigher(): Boolean = Build.VERSION.SDK_INT >= 20

    fun sdkIsKitkatOrHigher(): Boolean = Build.VERSION.SDK_INT >= 19

    fun sdkIsMarshmallowOrHigher(): Boolean = Build.VERSION.SDK_INT >= 23

    fun getDisplayWidthInDp(activity: Activity): Float {
        val display = activity.windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        var width = size.x
        if (activity.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            width = size.y
        }
        return UiUtils.convertPixelsToDp(width.toFloat())
    }

    fun getMaximumDisplayWidthInPx(activity: Activity): Int {
        val display = activity.windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val width: Int
        width = if (size.x > size.y) {
            size.x
        } else size.y

        return width
    }
}