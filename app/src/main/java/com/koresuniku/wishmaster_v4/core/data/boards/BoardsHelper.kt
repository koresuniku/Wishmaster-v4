package com.koresuniku.wishmaster_v4.core.data.boards

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.koresuniku.wishmaster_v4.core.data.DatabaseContract
import java.util.ArrayList

/**
 * Created by koresuniku on 03.10.17.
 */

object BoardsHelper {

    private val mBoardsProjection = arrayOf(
            DatabaseContract.BoardsEntry.COLUMN_BOARD_ID,
            DatabaseContract.BoardsEntry.COLUMN_BOARD_NAME)

    fun getBoardsProjection() = mBoardsProjection

    fun getBoardsDataFromDatabase(context: Context): BoardsData {
        val data = BoardsData()
        val boardList = ArrayList<WishmasterBoard>()

        val cursor: Cursor = context.contentResolver.query(
                DatabaseContract.BoardsEntry.CONTENT_URI,
                mBoardsProjection, null, null, null)

        val columnBoardId = cursor.getColumnIndex(
                DatabaseContract.BoardsEntry.COLUMN_BOARD_ID)
        val columnBoardName = cursor.getColumnIndex(
                DatabaseContract.BoardsEntry.COLUMN_BOARD_NAME)
        val columnBoardCategory = cursor.getColumnIndex(
                DatabaseContract.BoardsEntry.COLUMN_BOARD_CATEGORY)

        var wishmasterBoard: WishmasterBoard

        cursor.moveToFirst()
        while (cursor.moveToNext()) {
            wishmasterBoard = WishmasterBoard()
            wishmasterBoard.setBoardId(cursor.getString(columnBoardId))
            wishmasterBoard.setBoardName(cursor.getString(columnBoardName))
            wishmasterBoard.setBoardCategory(cursor.getString(columnBoardCategory))
            boardList.add(wishmasterBoard)
        }
        cursor.close()

        data.setBoardList(boardList)
        return data
    }

    fun queryBoard(context: Context, boardId: String): Cursor {
        val cursor = context.contentResolver.query(DatabaseContract.BoardsEntry.CONTENT_URI,
                mBoardsProjection, DatabaseContract.BoardsEntry.COLUMN_BOARD_ID + " =? ",
                arrayOf(boardId), null, null)
        cursor.close()
        return cursor
    }

    fun insetAllBoardsIntoDatabase(context: Context, data: BoardsData) {
        var values: ContentValues

        data.getBoardList().forEach {
            values = ContentValues()
            values.put(DatabaseContract.BoardsEntry.COLUMN_BOARD_ID, it.getBoardId())
            values.put(DatabaseContract.BoardsEntry.COLUMN_BOARD_NAME, it.getBoardName())
            values.put(DatabaseContract.BoardsEntry.COLUMN_BOARD_CATEGORY, it.getBoardCategory())
            context.contentResolver.insert(DatabaseContract.BoardsEntry.CONTENT_URI, values)
        }
    }

    fun insertSubtractedBoardsFromInputData(context: Context, inputData: BoardsData) {
        val existingBoardsData = getBoardsDataFromDatabase(context)
        val resultData = inputData.getBoardList().subtract(existingBoardsData.getBoardList())

        resultData.forEach {
            insertBoard(context, it.getBoardId(), it.getBoardName(), it.getBoardCategory())
        }
    }

    private fun insertBoard(context: Context, boardId: String, boardName: String, category: String) {
        val values = ContentValues()
        values.put(DatabaseContract.BoardsEntry.COLUMN_BOARD_ID, boardId)
        values.put(DatabaseContract.BoardsEntry.COLUMN_BOARD_NAME, boardName)
        values.put(DatabaseContract.BoardsEntry.COLUMN_BOARD_CATEGORY, category)
        context.contentResolver.insert(DatabaseContract.BoardsEntry.CONTENT_URI, values)
    }

    fun deleteOldBoards(context: Context, inputData: BoardsData) {
        val existingBoardsData = getBoardsDataFromDatabase(context)
        val resultData = existingBoardsData.getBoardList().subtract(inputData.getBoardList())

        resultData.forEach { deleteBoard(context, it.getBoardId()) }
    }

    private fun deleteBoard(context: Context, boardId: String) {
        context.contentResolver.delete(DatabaseContract.BoardsEntry.CONTENT_URI,
                DatabaseContract.BoardsEntry.COLUMN_BOARD_ID + " =? ", arrayOf(boardId))
    }
}