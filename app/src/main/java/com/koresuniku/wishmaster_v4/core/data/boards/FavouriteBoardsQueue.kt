package com.koresuniku.wishmaster_v4.core.data.boards

/**
 * Created by koresuniku on 27.11.17.
 */

data class FavouriteBoardsQueue(val boards: List<Pair<String, String>>) {
    companion object {
        val FAVOURITE_BOARDS_KEY = "favourite_boards"
        val FAVOURITE_BOARDS_DEFAULT_VALUE = "favourite_boards_default"

        fun getStringForPreferences(boards: List<Pair<String, String>>): String {
            val queue = String()
            boards.forEach {
                queue.plus(it.first)
                queue.plus("|")
                queue.plus(it.second)
                queue.plus(" ")
            }
            return queue.substring(0, queue.length - 1)
        }

        fun getQueueObjectFromPreferences(queue: String): FavouriteBoardsQueue {
            val boards = ArrayList<Pair<String, String>>()
            val queuePairs = queue.split(" ")
            queuePairs.forEach {
                val singleBoardData = it.split("|")
                val pair = Pair(singleBoardData[0], singleBoardData[1])
                boards.add(pair)
            }
            return FavouriteBoardsQueue(boards)
        }
    }

}