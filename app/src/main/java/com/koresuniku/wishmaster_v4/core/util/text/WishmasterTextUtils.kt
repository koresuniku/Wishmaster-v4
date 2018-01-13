package com.koresuniku.wishmaster_v4.core.util.text

import android.text.Html
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
        return getSpannedFromHtml(if (boardId == "b") "" else subject)
    }

    fun getResumeInfo(postCount: Int, fileCount: Int): String {
        var result = ""
        result += getCorrectRussianEndings(
                count = postCount, stringForZeroOrMultiple = "постов",
                stringForOne = "пост", stringForTwoThreeFour = "поста")
        result += ", "
        result += getCorrectRussianEndings(
                count = fileCount, stringForZeroOrMultiple = "файлов",
                stringForOne = "файл", stringForTwoThreeFour = "файла")
        return result
    }

    private fun getCorrectRussianEndings(count: Int, stringForZeroOrMultiple: String,
                                         stringForOne: String, stringForTwoThreeFour: String): String {
        if (count == 0) return count.toString() + " " + stringForZeroOrMultiple
        val lastNumber: Int
        var signsCount = -1
        if (count < 10) {
            lastNumber = count
        } else {
            signsCount = count.toString().length
            lastNumber = Integer.parseInt(count.toString().substring(signsCount - 1, signsCount))
        }
        if (count in 10..20) {
            return count.toString() + " " + stringForZeroOrMultiple
        }
        if (lastNumber == 1) {
            if (count >= 10 && count.toString().substring(signsCount - 2, signsCount) == "11") {
                return count.toString() + " " + stringForZeroOrMultiple
            }
            return count.toString() + " " + stringForOne
        }
        if (lastNumber == 2 || lastNumber == 3 || lastNumber == 4) {
            return count.toString() + " " + stringForTwoThreeFour
        }
        return count.toString() + " " + stringForZeroOrMultiple
    }
}