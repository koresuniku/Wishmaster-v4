package com.koresuniku.wishmaster.core.thread_list

import com.koresuniku.wishmaster.core.base.IMvpView
import com.koresuniku.wishmaster.core.data.threads.ThreadListData

/**
 * Created by koresuniku on 07.01.18.
 */

interface ThreadListAdapterView : IMvpView {
    fun onThreadListDataChanged(newThreadListData: ThreadListData)
}
