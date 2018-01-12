package com.koresuniku.wishmaster_v4.core.util.text

import android.text.Html
import android.text.Spannable
import android.text.Spanned
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

    fun getSpannedFromHtml(input: String): Spanned { return Html.fromHtml(input) }

    fun getSubjectSpanned(subject: String, boardId: String): Spanned {
        return if (boardId == "b") getSpannedFromHtml("")
        else getSpannedFromHtml(subject)
    }
}