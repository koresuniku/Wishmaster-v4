package com.koresuniku.wishmaster_v4.core.thread_list

import android.text.Spannable
import android.text.Spanned
import com.koresuniku.wishmaster_v4.core.base.IMvpView
import com.koresuniku.wishmaster_v4.core.gallery.ImageLayoutConfiguration

/**
 * Created by koresuniku on 07.01.18.
 */

interface ThreadItemView : IMvpView {
    fun setSubject(subject: Spanned)
    fun setComment(comment: Spanned)
    fun setResumeInfo(resume: String)
    fun setSingleImage(configuration: ImageLayoutConfiguration)
    fun setMultipleImages(configuration: ImageLayoutConfiguration)
}