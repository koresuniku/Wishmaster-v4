package com.koresuniku.wishmasterV4.core.dashboard

import com.koresuniku.wishmasterV4.core.base.IMvpView
import com.koresuniku.wishmasterV4.core.data.boards.BoardListData

/**
 * Created by koresuniku on 13.11.17.
 */

interface BoardListView : IMvpView {
    fun onBoardsDataReceived(boardListData: BoardListData)
}