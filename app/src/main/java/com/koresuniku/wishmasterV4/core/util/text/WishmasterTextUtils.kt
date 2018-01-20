package com.koresuniku.wishmasterV4.core.util.text

import android.text.*
import com.koresuniku.wishmasterV4.core.data.boards.BoardModel
import com.koresuniku.wishmasterV4.core.data.threads.File
import io.reactivex.Single

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

    fun obtainImageResume(file: File): String {
        val width = file.width
        val height = file.height
        val size = file.size
        val format = file.thumbnail
                .removePrefix(file.thumbnail.subSequence(0, file.thumbnail.indexOf(".") + 1))
                .toUpperCase()
        return "$width * $height\n$size, $format"
    }

    fun getSpannedFromHtml(input: String): Spanned { return Html.fromHtml(input) }

    fun getSubjectSpanned(subject: String, boardId: String): Spanned {
        return getSpannedFromHtml(if (boardId == "b") "" else subject)
    }

    fun cutComment(rawComment: String, textViewWidth: Int): Single<Spannable> {
//        ViewUtils.measureView(textView)
//        val staticLayout = StaticLayout(rawComment, textView.paint, textView.measuredWidth,
//                Layout.Alignment.ALIGN_NORMAL, 1.0f, 0f, false)
//        Log.d("WTU", "static layout lines: ${staticLayout.lineCount}")
//
//        return SpannableString(rawComment.subSequence(
//                0, if (staticLayout.lineCount > 6) staticLayout.getLineEnd(5) else rawComment.length))
        return Single.create({ e -> kotlin.run {
            val comment = getSpannedFromHtml(rawComment)
            val staticLayout = StaticLayout(rawComment, TextPaint(), textViewWidth,
                    Layout.Alignment.ALIGN_NORMAL, 1.0f, 0f, false)
            e.onSuccess(SpannableString(comment.subSequence(
                0, if (staticLayout.lineCount > 6) staticLayout.getLineEnd(5) else rawComment.length)))
        }})
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