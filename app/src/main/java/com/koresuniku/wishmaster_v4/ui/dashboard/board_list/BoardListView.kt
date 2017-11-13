package com.koresuniku.wishmaster_v4.ui.dashboard.board_list

import com.koresuniku.wishmaster_v4.core.data.boards.BoardsData

/**
 * Created by koresuniku on 13.11.17.
 */

interface BoardListView {
    fun onBoardDataReceived(boardsData: BoardsData)
}