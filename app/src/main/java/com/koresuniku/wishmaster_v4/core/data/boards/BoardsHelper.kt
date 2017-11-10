package com.koresuniku.wishmaster_v4.core.data.boards

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.koresuniku.wishmaster_v4.core.data.DatabaseContract
import java.util.ArrayList

/**
 * Created by koresuniku on 03.10.17.
 */

object BoardsHelper {

    private val mBoardsProjection = arrayOf(
            DatabaseContract.BoardsEntry.COLUMN_BOARD_ID,
            DatabaseContract.BoardsEntry.COLUMN_BOARD_NAME,
            DatabaseContract.BoardsEntry.COLUMN_BOARD_CATEGORY)

    fun getBoardsProjection() = mBoardsProjection

    val CREATE_TABLE_BOARDS_STATEMENT = "CREATE TABLE " + DatabaseContract.BoardsEntry.TABLE_NAME + " (" +
            DatabaseContract.BoardsEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            DatabaseContract.BoardsEntry.COLUMN_BOARD_ID + " TEXT NOT NULL, " +
            DatabaseContract.BoardsEntry.COLUMN_BOARD_NAME + " TEXT NOT NULL, " +
            DatabaseContract.BoardsEntry.COLUMN_BOARD_CATEGORY + " TEXT NOT NULL" + ");"


    fun getBoardsDataFromDatabase(database: SQLiteDatabase): BoardsData {
        val data = BoardsData()
        val boardList = ArrayList<BoardModel>()

        val cursor: Cursor = database.query(
                DatabaseContract.BoardsEntry.TABLE_NAME, mBoardsProjection,
                null, null, null, null, null)

        val columnBoardId = cursor.getColumnIndex(
                DatabaseContract.BoardsEntry.COLUMN_BOARD_ID)
        val columnBoardName = cursor.getColumnIndex(
                DatabaseContract.BoardsEntry.COLUMN_BOARD_NAME)
        val columnBoardCategory = cursor.getColumnIndex(
                DatabaseContract.BoardsEntry.COLUMN_BOARD_CATEGORY)

        var boardModel: BoardModel

        cursor.moveToFirst()
        while (cursor.moveToNext()) {
            boardModel = BoardModel()
            boardModel.setBoardId(cursor.getString(columnBoardId))
            boardModel.setBoardName(cursor.getString(columnBoardName))
            boardModel.setBoardCategory(cursor.getString(columnBoardCategory))
            boardList.add(boardModel)
        }
        cursor.close()
        database.close()

        data.setBoardList(boardList)
        return data
    }

    fun queryBoard(database: SQLiteDatabase, boardId: String): Cursor {
        val cursor = database.query(DatabaseContract.BoardsEntry.TABLE_NAME,
                mBoardsProjection, DatabaseContract.BoardsEntry.COLUMN_BOARD_ID + " =? ",
                arrayOf(boardId), null, null, null)
        cursor.close()
        return cursor
    }

    fun insertAllBoardsIntoDatabase(database: SQLiteDatabase, data: BoardsData) {
        var values: ContentValues

        data.getBoardList().forEach {
            values = ContentValues()
            values.put(DatabaseContract.BoardsEntry.COLUMN_BOARD_ID, it.getBoardId())
            values.put(DatabaseContract.BoardsEntry.COLUMN_BOARD_NAME, it.getBoardName())
            values.put(DatabaseContract.BoardsEntry.COLUMN_BOARD_CATEGORY, it.getBoardCategory())
            database.insert(DatabaseContract.BoardsEntry.TABLE_NAME, null, values)
        }

        database.close()
    }

    fun insertSubtractedBoardsFromInputData(database: SQLiteDatabase, inputData: BoardsData) {
        val existingBoardsData = getBoardsDataFromDatabase(database)
        val resultData = inputData.getBoardList().subtract(existingBoardsData.getBoardList())

        resultData.forEach {
            insertBoard(database, it.getBoardId(), it.getBoardName(), it.getBoardCategory())
        }

        database.close()
    }

    private fun insertBoard(database: SQLiteDatabase, boardId: String, boardName: String, category: String) {
        val values = ContentValues()
        values.put(DatabaseContract.BoardsEntry.COLUMN_BOARD_ID, boardId)
        values.put(DatabaseContract.BoardsEntry.COLUMN_BOARD_NAME, boardName)
        values.put(DatabaseContract.BoardsEntry.COLUMN_BOARD_CATEGORY, category)
        database.insert(DatabaseContract.BoardsEntry.TABLE_NAME, null, values)
    }

    fun deleteOldBoards(database: SQLiteDatabase, inputData: BoardsData) {
        val existingBoardsData = getBoardsDataFromDatabase(database)
        val resultData = existingBoardsData.getBoardList().subtract(inputData.getBoardList())

        resultData.forEach { deleteBoard(database, it.getBoardId()) }

        database.close()
    }

    private fun deleteBoard(database: SQLiteDatabase, boardId: String) {
        database.delete(DatabaseContract.BoardsEntry.TABLE_NAME,
                DatabaseContract.BoardsEntry.COLUMN_BOARD_ID + " =? ", arrayOf(boardId))
    }
}