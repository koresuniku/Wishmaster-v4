package com.koresuniku.wishmaster_v4.core.util.text

import com.koresuniku.wishmaster_v4.core.data.boards.BoardModel

/**
 * Created by koresuniku on 04.01.18.
 */

object WishmasterTextUtils {

    fun obtainBoardIdDashName(boardModel: BoardModel): String {
        return "/${boardModel.getBoardId()}/ - ${boardModel.getBoardName()}"
    }

    fun obtainBoardIdDashName(boardId: String, boardName: String): String {
        return "/$boardId/ - $boardName"
    }
}