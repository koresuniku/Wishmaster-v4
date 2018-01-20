package com.koresuniku.wishmaster.core.thread_list

import com.koresuniku.wishmaster.core.base.IActivityMvpView

/**
 * Created by koresuniku on 01.01.18.
 */

interface ThreadListView : IActivityMvpView {
    fun getBoardId(): String
}