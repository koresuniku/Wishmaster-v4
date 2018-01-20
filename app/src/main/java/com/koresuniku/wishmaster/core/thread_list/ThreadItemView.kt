package com.koresuniku.wishmaster.core.thread_list

import android.text.Spannable
import android.text.Spanned
import com.koresuniku.wishmaster.core.base.IMvpView
import com.koresuniku.wishmaster.core.gallery.ImageItemData

/**
 * Created by koresuniku on 07.01.18.
 */

interface ThreadItemView : IMvpView {
    fun setSubject(subject: Spanned, hasImages: Boolean)
    fun setComment(comment: Spannable)
    fun setResumeInfo(resume: String)
    fun setSingleImage(imageItemData: ImageItemData)
    fun setMultipleImages(imageItemDataList: List<ImageItemData>)
}