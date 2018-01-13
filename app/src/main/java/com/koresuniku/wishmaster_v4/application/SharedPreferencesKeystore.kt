package com.koresuniku.wishmaster_v4.application

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

/**
 * Created by koresuniku on 12.11.17.
 */

object SharedPreferencesKeystore {
    val SHARED_PREFERENCES_NAME = "sharedPreferences"

    val DASHBOARD_PREFERRED_TAB_POSITION = "dashboard_preferred_tab_position"
    val DASHBOARD_PREFERRED_TAB_POSITION_DEFAULT = 1

    val DEFAULT_IMAGE_WIDTH_IN_PX = "default_image_width_in_px"
    val DEFAULT_IMAGE_WIDTH_IN_PX_DEFAULT = 0
}