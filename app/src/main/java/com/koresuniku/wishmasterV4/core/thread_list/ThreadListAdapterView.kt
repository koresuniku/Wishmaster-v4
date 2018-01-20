package com.koresuniku.wishmasterV4.core.thread_list

import com.koresuniku.wishmasterV4.core.base.IMvpView
import com.koresuniku.wishmasterV4.core.data.threads.ThreadListData

/**
 * Created by koresuniku on 07.01.18.
 */

interface ThreadListAdapterView : IMvpView {
    fun onThreadListDataChanged(newThreadListData: ThreadListData)
}
