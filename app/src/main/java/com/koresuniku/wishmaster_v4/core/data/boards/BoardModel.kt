package com.koresuniku.wishmaster_v4.core.data.boards

/**
 * Created by koresuniku on 04.10.17.
 */

class BoardModel {
    private lateinit var mBoardId: String
    private lateinit var mBoardName: String
    private lateinit var mBoardCategory: String

    fun getBoardId() = mBoardId
    fun getBoardName() = mBoardName
    fun getBoardCategory() = mBoardCategory

    fun setBoardId(boardId: String) {
        this.mBoardId = boardId
    }

    fun setBoardName(boardName: String) {
        this.mBoardName = boardName
    }

    fun setBoardCategory(boardCategory: String) {
        this.mBoardCategory = boardCategory
    }

    override fun equals(other: Any?): Boolean {
        return if (other is BoardModel) {
            other.getBoardId() == getBoardId()
        } else false
    }
}