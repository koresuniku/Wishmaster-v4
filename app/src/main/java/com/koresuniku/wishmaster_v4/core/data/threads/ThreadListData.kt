package com.koresuniku.wishmaster_v4.core.data.threads

/**
 * Created by koresuniku on 01.01.18.
 */

class ThreadListData {

    private lateinit var boardName: String
    private lateinit var defaultName: String
    private lateinit var threadList: List<Thread>

    fun getThreadList() = threadList
    fun getBoardName() = boardName
    fun getDefaultName() = defaultName

    fun setBoardList(threadList: List<Thread>) { this.threadList = threadList }
    fun setBoardName(boardName: String) { this.boardName = boardName }
    fun setDefaultName(defaultName: String) { this.defaultName = defaultName }
}