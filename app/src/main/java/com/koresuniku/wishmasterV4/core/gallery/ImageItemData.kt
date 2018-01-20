package com.koresuniku.wishmasterV4.core.gallery

import com.koresuniku.wishmasterV4.core.data.threads.File

/**
 * Created by koresuniku on 18.01.18.
 */

data class ImageItemData(val file: File,
                         val configuration: ImageLayoutConfiguration,
                         val summary: String)