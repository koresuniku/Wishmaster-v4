package com.koresuniku.wishmaster_v4.core.util

import com.koresuniku.wishmaster_v4.core.domain.Dvach
import java.util.regex.Pattern

/**
 * Created by koresuniku on 02.01.18.
 */

object SearchInputMatcher {

    val BOARD_CODE = 0
    val THREAD_CODE = 1
    val POST_CODE = 2

    fun matchInput(input: String): SearchInputResponse {
        var result = checkIfBoard(input).code
        if (result != SearchInputResponse.UNKNOWN_ADDRESS) return SearchInputResponse(result)

        result = checkIfThread(input).code
        if (result != SearchInputResponse.UNKNOWN_ADDRESS) return SearchInputResponse(result)

        result = checkIfPost(input).code
        if (result != SearchInputResponse.UNKNOWN_ADDRESS) return SearchInputResponse(result)

        return SearchInputResponse(SearchInputResponse.UNKNOWN_ADDRESS)
    }

    private fun checkIfBoard(input: String): SearchInputResponse {
        val pattern = Pattern.compile("/*[a-zA-Z0-9]+/*")
        val matcher = pattern.matcher(input)
        return if (matcher.matches()) {
            val boardCode = input.replace(Regex("/+"), "")
            SearchInputResponse(boardCode)
        } else SearchInputResponse(SearchInputResponse.UNKNOWN_ADDRESS)
    }

    private fun checkIfThread(input: String): SearchInputResponse {
        Dvach.MIRRORS.forEach {
            val pattern = Pattern.compile("^https?://$it/+[a-zA-Z0-9]+/+res/+[0-9]+\\.html$")
            val matcher = pattern.matcher(input)
            if (matcher.matches()) {
                val threadCode = input
                        .replace(Regex("^https?://$it/+[a-zA-Z0-9]+/+res/+"), "")
                        .replace(Regex("\\.html"), "")
                        .replace(Regex("/+"), "")
                return SearchInputResponse(threadCode)
            }
        }
        return SearchInputResponse(SearchInputResponse.UNKNOWN_ADDRESS)
    }

    private fun checkIfPost(input: String): SearchInputResponse {
        Dvach.MIRRORS.forEach {
            val pattern = Pattern.compile("^https?://$it/+[a-zA-Z0-9]+/+res/+[0-9]+\\.html#[0-9]+$")
            val matcher = pattern.matcher(input)
            if (matcher.matches()) {
                val postCode = input
                        .replace(Regex("^https?://$it/+[a-zA-Z0-9]+/+res/+"), "")
                        .replace(Regex("\\.html[0-9]+\$"), "")
                        .replace(Regex("/+"), "")
                return SearchInputResponse(postCode)
            }
        }
        return SearchInputResponse(SearchInputResponse.UNKNOWN_ADDRESS)
    }

}