package com.koresuniku.wishmaster.core.gallery

import com.koresuniku.wishmaster.core.data.threads.File

/**
 * Created by koresuniku on 18.01.18.
 */

data class ImageItemData(val file: File,
                         val configuration: ImageLayoutConfiguration,
                         val summary: String)