package com.koresuniku.wishmasterV4.core.thread_list

import android.text.Spannable
import android.text.Spanned
import com.koresuniku.wishmasterV4.core.base.IMvpView
import com.koresuniku.wishmasterV4.core.gallery.ImageItemData

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