package com.koresuniku.wishmaster.core.dashboard

import com.koresuniku.wishmaster.core.base.IMvpView
import com.koresuniku.wishmaster.core.data.boards.BoardListData

/**
 * Created by koresuniku on 13.11.17.
 */

interface BoardListView : IMvpView {
    fun onBoardsDataReceived(boardListData: BoardListData)
}