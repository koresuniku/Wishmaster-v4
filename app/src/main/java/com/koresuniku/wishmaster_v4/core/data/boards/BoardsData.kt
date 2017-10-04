package com.koresuniku.wishmaster_v4.core.data.boards

/**
 * Created by koresuniku on 04.10.17.
 */

class BoardsData {
    private lateinit var boardList: List<WishmasterBoard>

    fun getBoardList() = boardList

    fun setBoardList(boardList: List<WishmasterBoard>) {
        this.boardList = boardList
    }
}