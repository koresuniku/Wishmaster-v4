package com.koresuniku.wishmaster_v4.core.dashboard

import com.koresuniku.wishmaster_v4.core.base.IMvpView
import com.koresuniku.wishmaster_v4.core.data.boards.BoardsData

/**
 * Created by koresuniku on 13.11.17.
 */

interface BoardListView : IMvpView {
    fun onBoardsDataReceived(boardsData: BoardsData)
}