package com.koresuniku.wishmaster_v4.core.database.boards

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.util.Log
import com.koresuniku.wishmaster.domain.boards_api.BoardsJsonSchema
import com.koresuniku.wishmaster.domain.boards_api.model.*
import com.koresuniku.wishmaster_v4.core.database.DatabaseContract
import java.util.ArrayList

/**
 * Created by koresuniku on 03.10.17.
 */

object BoardsHelper {

    private val mBoardsProjection = arrayOf(
            DatabaseContract.BoardsEntry.COLUMN_BOARD_ID,
            DatabaseContract.BoardsEntry.COLUMN_BOARD_NAME,
            DatabaseContract.BoardsEntry.COLUMN_BOARD_PREFERRED)

    fun getBoardsProjection() = mBoardsProjection

    interface BoardsWrittenToDatabaseCallBack {
        fun onBoardsWritten()
    }

    fun getSchema(context: Context): BoardsJsonSchema {
        val mSchema = BoardsJsonSchema()

        var cursor: Cursor = context.contentResolver.query(
                DatabaseContract.BoardsEntry.CONTENT_URI,
                mBoardsProjection, null, null, null)

        val columnBoardId = cursor.getColumnIndex(
                DatabaseContract.BoardsEntry.COLUMN_BOARD_ID)
        val columnBoardName = cursor.getColumnIndex(
                DatabaseContract.BoardsEntry.COLUMN_BOARD_NAME)
        val columnBoardPreferredPosition = cursor.getColumnIndex(
                DatabaseContract.BoardsEntry.COLUMN_BOARD_PREFERRED)

        var boardId: String
        var boardName: String
        var boardPreferredPosition: Int

        val adultsList = ArrayList<Adults>()
        var adults: Adults
        cursor = context.contentResolver.query(
                DatabaseContract.BoardsEntry.CONTENT_URI,
                mBoardsProjection,
                DatabaseContract.BoardsEntry.COLUMN_BOARD_CATEGORY + " =? ",
                arrayOf(DatabaseContract.BoardsEntry.CATEGORY_ADULTS), null, null)
        cursor.moveToFirst()
        for (i in 0 until cursor.count) {
            boardId = cursor.getString(columnBoardId)
            boardName = cursor.getString(columnBoardName)
            adults = Adults()
            adults.id = boardId
            adults.name = boardName
            adultsList.add(adults)
            cursor.moveToNext()
        }
        mSchema.adults = adultsList

        val creativityList = ArrayList<Creativity>()
        var creativity: Creativity
        cursor = context.contentResolver.query(
                DatabaseContract.BoardsEntry.CONTENT_URI,
                mBoardsProjection,
                DatabaseContract.BoardsEntry.COLUMN_BOARD_CATEGORY + " =? ",
                arrayOf(DatabaseContract.BoardsEntry.CATEGORY_CREATIVITY), null, null)
        cursor.moveToFirst()
        for (i in 0 until cursor.count) {
            boardId = cursor.getString(columnBoardId)
            boardName = cursor.getString(columnBoardName)
            creativity = Creativity()
            creativity.id = boardId
            creativity.name = boardName
            creativityList.add(creativity)

            cursor.moveToNext()
        }
        mSchema.creativity = creativityList

        val gamesList = ArrayList<Games>()
        var games: Games
        cursor = context.contentResolver.query(
                DatabaseContract.BoardsEntry.CONTENT_URI,
                mBoardsProjection,
                DatabaseContract.BoardsEntry.COLUMN_BOARD_CATEGORY + " =? ",
                arrayOf(DatabaseContract.BoardsEntry.CATEGORY_GAMES), null, null)
        cursor.moveToFirst()
        for (i in 0 until cursor.count) {
            boardId = cursor.getString(columnBoardId)
            boardName = cursor.getString(columnBoardName)
            games = Games()
            games.id = boardId
            games.name = boardName
            gamesList.add(games)

            cursor.moveToNext()
        }
        mSchema.games = gamesList

        val japaneseList = ArrayList<Japanese>()
        var japanese: Japanese
        cursor = context.contentResolver.query(
                DatabaseContract.BoardsEntry.CONTENT_URI,
                mBoardsProjection,
                DatabaseContract.BoardsEntry.COLUMN_BOARD_CATEGORY + " =? ",
                arrayOf(DatabaseContract.BoardsEntry.CATEGORY_JAPANESE), null, null)
        cursor.moveToFirst()
        for (i in 0 until cursor.count) {
            boardId = cursor.getString(columnBoardId)
            boardName = cursor.getString(columnBoardName)
            japanese = Japanese()
            japanese.id = boardId
            japanese.name = boardName
            japaneseList.add(japanese)

            cursor.moveToNext()
        }
        mSchema.japanese = japaneseList

        val otherList = ArrayList<Other>()
        var other: Other
        cursor = context.contentResolver.query(
                DatabaseContract.BoardsEntry.CONTENT_URI,
                mBoardsProjection,
                DatabaseContract.BoardsEntry.COLUMN_BOARD_CATEGORY + " =? ",
                arrayOf(DatabaseContract.BoardsEntry.CATEGORY_OTHER), null, null)
        cursor.moveToFirst()
        for (i in 0 until cursor.count) {
            boardId = cursor.getString(columnBoardId)
            boardName = cursor.getString(columnBoardName)
            other = Other()
            other.id = boardId
            other.name = boardName
            otherList.add(other)

            cursor.moveToNext()
        }
        mSchema.other = otherList


        val politicsList = ArrayList<Politics>()
        var politics: Politics
        cursor = context.contentResolver.query(
                DatabaseContract.BoardsEntry.CONTENT_URI,
                mBoardsProjection,
                DatabaseContract.BoardsEntry.COLUMN_BOARD_CATEGORY + " =? ",
                arrayOf(DatabaseContract.BoardsEntry.CATEGORY_POLITICS), null, null)
        cursor.moveToFirst()
        for (i in 0 until cursor.count) {
            boardId = cursor.getString(columnBoardId)
            boardName = cursor.getString(columnBoardName)
            politics = Politics()
            politics.id = boardId
            politics.name = boardName
            politicsList.add(politics)

            cursor.moveToNext()
        }
        mSchema.politics = politicsList


        val subjectsList = ArrayList<Subjects>()
        var subjects: Subjects
        cursor = context.contentResolver.query(
                DatabaseContract.BoardsEntry.CONTENT_URI,
                mBoardsProjection,
                DatabaseContract.BoardsEntry.COLUMN_BOARD_CATEGORY + " =? ",
                arrayOf(DatabaseContract.BoardsEntry.CATEGORY_SUBJECTS), null, null)
        cursor.moveToFirst()
        for (i in 0..cursor.count - 1) {
            boardId = cursor.getString(columnBoardId)
            boardName = cursor.getString(columnBoardName)
            subjects = Subjects()
            subjects.id = boardId
            subjects.name = boardName
            subjectsList.add(subjects)

            cursor.moveToNext()
        }
        mSchema.subject = subjectsList

        val techList = ArrayList<Tech>()
        var tech: Tech
        cursor = context.contentResolver.query(
                DatabaseContract.BoardsEntry.CONTENT_URI,
                mBoardsProjection,
                DatabaseContract.BoardsEntry.COLUMN_BOARD_CATEGORY + " =? ",
                arrayOf(DatabaseContract.BoardsEntry.CATEGORY_TECH), null, null)
        cursor.moveToFirst()
        for (i in 0 until cursor.count) {
            boardId = cursor.getString(columnBoardId)
            boardName = cursor.getString(columnBoardName)
            tech = Tech()
            tech.id = boardId
            tech.name = boardName
            techList.add(tech)

            cursor.moveToNext()
        }
        mSchema.tech = techList

        val usersList = ArrayList<Users>()
        var users: Users
        cursor = context.contentResolver.query(
                DatabaseContract.BoardsEntry.CONTENT_URI,
                mBoardsProjection,
                DatabaseContract.BoardsEntry.COLUMN_BOARD_CATEGORY + " =? ",
                arrayOf(DatabaseContract.BoardsEntry.CATEGORY_USERS), null, null)
        cursor.moveToFirst()
        for (i in 0 until cursor.count) {
            boardId = cursor.getString(columnBoardId)
            boardName = cursor.getString(columnBoardName)
            users = Users()
            users.id = boardId
            users.name = boardName
            usersList.add(users)

            cursor.moveToNext()
        }
        mSchema.users = usersList


        cursor.close()

        return mSchema
    }

    fun queryABoard(context: Context, boardId: String?): Cursor {
        val cursor = context.contentResolver.query(DatabaseContract.BoardsEntry.CONTENT_URI,
                mBoardsProjection, DatabaseContract.BoardsEntry.COLUMN_BOARD_ID + " =? ",
                arrayOf(boardId), null, null)
        cursor.close()
        return cursor
    }

    fun writeInAllTheBoardsIntoDatabase(mSchema: BoardsJsonSchema,
                                        context: Context) {
        var values = ContentValues()

        for (board in mSchema.adults) {
            values.put(DatabaseContract.BoardsEntry.COLUMN_BOARD_ID, board.id)
            values.put(DatabaseContract.BoardsEntry.COLUMN_BOARD_NAME, board.name)
            values.put(DatabaseContract.BoardsEntry.COLUMN_BOARD_CATEGORY, DatabaseContract.BoardsEntry.CATEGORY_ADULTS)
            context.contentResolver.insert(DatabaseContract.BoardsEntry.CONTENT_URI, values)
            values = ContentValues()
        }

        for (board in mSchema.creativity!!) {
            values.put(DatabaseContract.BoardsEntry.COLUMN_BOARD_ID, board.id)
            values.put(DatabaseContract.BoardsEntry.COLUMN_BOARD_NAME, board.name)
            values.put(DatabaseContract.BoardsEntry.COLUMN_BOARD_CATEGORY, DatabaseContract.BoardsEntry.CATEGORY_CREATIVITY)
            context.contentResolver.insert(DatabaseContract.BoardsEntry.CONTENT_URI, values)
            values = ContentValues()
        }

        for (board in mSchema.games!!) {
            values.put(DatabaseContract.BoardsEntry.COLUMN_BOARD_ID, board.id)
            values.put(DatabaseContract.BoardsEntry.COLUMN_BOARD_NAME, board.name)
            values.put(DatabaseContract.BoardsEntry.COLUMN_BOARD_CATEGORY, DatabaseContract.BoardsEntry.CATEGORY_GAMES)
            context.contentResolver.insert(DatabaseContract.BoardsEntry.CONTENT_URI, values)
            values = ContentValues()
        }

        for (board in mSchema.japanese!!) {
            values.put(DatabaseContract.BoardsEntry.COLUMN_BOARD_ID, board.id)
            values.put(DatabaseContract.BoardsEntry.COLUMN_BOARD_NAME, board.name)
            values.put(DatabaseContract.BoardsEntry.COLUMN_BOARD_CATEGORY, DatabaseContract.BoardsEntry.CATEGORY_JAPANESE)
            context.contentResolver.insert(DatabaseContract.BoardsEntry.CONTENT_URI, values)
            values = ContentValues()
        }

        for (board in mSchema.other!!) {
            values.put(DatabaseContract.BoardsEntry.COLUMN_BOARD_ID, board.id)
            values.put(DatabaseContract.BoardsEntry.COLUMN_BOARD_NAME, board.name)
            values.put(DatabaseContract.BoardsEntry.COLUMN_BOARD_CATEGORY, DatabaseContract.BoardsEntry.CATEGORY_OTHER)
            context.contentResolver.insert(DatabaseContract.BoardsEntry.CONTENT_URI, values)
            values = ContentValues()
        }

        for (board in mSchema.politics!!) {
            values.put(DatabaseContract.BoardsEntry.COLUMN_BOARD_ID, board.id)
            values.put(DatabaseContract.BoardsEntry.COLUMN_BOARD_NAME, board.name)
            values.put(DatabaseContract.BoardsEntry.COLUMN_BOARD_CATEGORY, DatabaseContract.BoardsEntry.CATEGORY_POLITICS)
            context.contentResolver.insert(DatabaseContract.BoardsEntry.CONTENT_URI, values)
            values = ContentValues()
        }

        for (board in mSchema.subject!!) {
            values.put(DatabaseContract.BoardsEntry.COLUMN_BOARD_ID, board.id)
            values.put(DatabaseContract.BoardsEntry.COLUMN_BOARD_NAME, board.name)
            values.put(DatabaseContract.BoardsEntry.COLUMN_BOARD_CATEGORY, DatabaseContract.BoardsEntry.CATEGORY_SUBJECTS)
            context.contentResolver.insert(DatabaseContract.BoardsEntry.CONTENT_URI, values)
            values = ContentValues()
        }

        for (board in mSchema.tech!!) {
            values.put(DatabaseContract.BoardsEntry.COLUMN_BOARD_ID, board.id)
            values.put(DatabaseContract.BoardsEntry.COLUMN_BOARD_NAME, board.name)
            values.put(DatabaseContract.BoardsEntry.COLUMN_BOARD_CATEGORY, DatabaseContract.BoardsEntry.CATEGORY_TECH)
            context.contentResolver.insert(DatabaseContract.BoardsEntry.CONTENT_URI, values)
            values = ContentValues()
        }

        for (board in mSchema.users!!) {
            values.put(DatabaseContract.BoardsEntry.COLUMN_BOARD_ID, board.id)
            values.put(DatabaseContract.BoardsEntry.COLUMN_BOARD_NAME, board.name)
            values.put(DatabaseContract.BoardsEntry.COLUMN_BOARD_CATEGORY, DatabaseContract.BoardsEntry.CATEGORY_USERS)
            context.contentResolver.insert(DatabaseContract.BoardsEntry.CONTENT_URI, values)
            values = ContentValues()
        }
    }

    fun insertNewBoards(context: Context, inputSchema: BoardsJsonSchema) {
        val boardsNewIdsSet: HashSet<String?> = HashSet()

        inputSchema.adults.mapTo(boardsNewIdsSet) { it.id }
        inputSchema.creativity.mapTo(boardsNewIdsSet) { it.id }
        inputSchema.games.mapTo(boardsNewIdsSet) { it.id }
        inputSchema.japanese.mapTo(boardsNewIdsSet) { it.id }
        inputSchema.other.mapTo(boardsNewIdsSet) { it.id }
        inputSchema.politics.mapTo(boardsNewIdsSet) { it.id }
        inputSchema.subject.mapTo(boardsNewIdsSet) { it.id }
        inputSchema.tech.mapTo(boardsNewIdsSet) { it.id }
        inputSchema.users.mapTo(boardsNewIdsSet) { it.id }

        val cursor: Cursor = context.contentResolver.query(
                DatabaseContract.BoardsEntry.CONTENT_URI, mBoardsProjection, null, null, null, null)
        val columnIndex = cursor.getColumnIndex(DatabaseContract.BoardsEntry.COLUMN_BOARD_ID)
        var boardId: String
        val boardsDatabaseIdsSet: HashSet<String> = HashSet()
        while (cursor.moveToNext()) {
            boardId = cursor.getString(columnIndex)
            boardsDatabaseIdsSet.add(boardId)
        }
        cursor.close()

        val resultSet: Set<String?> = boardsNewIdsSet.subtract(boardsDatabaseIdsSet)

        for (newBoardId: String? in resultSet) {
            inputSchema.adults.filter { it.id!! == newBoardId }.forEach {
                insertNewBoard(context, arrayOf(it.id, it.name, DatabaseContract.BoardsEntry.CATEGORY_ADULTS))
            }
            inputSchema.creativity.filter { it.id!! == newBoardId }.forEach {
                insertNewBoard(context, arrayOf(it.id, it.name, DatabaseContract.BoardsEntry.CATEGORY_CREATIVITY))
            }
            inputSchema.games.filter { it.id!! == newBoardId }.forEach {
                insertNewBoard(context, arrayOf(it.id, it.name, DatabaseContract.BoardsEntry.CATEGORY_GAMES))
            }
            inputSchema.japanese.filter { it.id!! == newBoardId }.forEach {
                insertNewBoard(context, arrayOf(it.id, it.name, DatabaseContract.BoardsEntry.CATEGORY_JAPANESE))
            }
            inputSchema.other.filter { it.id!! == newBoardId }.forEach {
                insertNewBoard(context, arrayOf(it.id, it.name, DatabaseContract.BoardsEntry.CATEGORY_OTHER))
            }
            inputSchema.politics.filter { it.id!! == newBoardId }.forEach {
                insertNewBoard(context, arrayOf(it.id, it.name, DatabaseContract.BoardsEntry.CATEGORY_POLITICS))
            }
            inputSchema.subject.filter { it.id!! == newBoardId }.forEach {
                insertNewBoard(context, arrayOf(it.id, it.name, DatabaseContract.BoardsEntry.CATEGORY_SUBJECTS))
            }
            inputSchema.tech.filter { it.id!! == newBoardId }.forEach {
                insertNewBoard(context, arrayOf(it.id, it.name, DatabaseContract.BoardsEntry.CATEGORY_TECH))
            }
            inputSchema.users.filter { it.id!! == newBoardId }.forEach {
                insertNewBoard(context, arrayOf(it.id, it.name, DatabaseContract.BoardsEntry.CATEGORY_USERS))
            }
        }
    }

    fun insertNewBoard(context: Context, boardData: Array<String?>) {
        val values = ContentValues()
        values.put(DatabaseContract.BoardsEntry.COLUMN_BOARD_ID, boardData[0])
        values.put(DatabaseContract.BoardsEntry.COLUMN_BOARD_NAME, boardData[1])
        values.put(DatabaseContract.BoardsEntry.COLUMN_BOARD_CATEGORY, boardData[2])
        context.contentResolver.insert(DatabaseContract.BoardsEntry.CONTENT_URI, values)

    }

    fun deleteOldBoards(context: Context, schema: BoardsJsonSchema) {
        val boardsNewIdsSet: HashSet<String?> = HashSet()

        for (adults: Adults in schema!!.adults!!) {
            boardsNewIdsSet.add(adults.id)
        }
        for (creativity: Creativity in schema.creativity!!) {
            boardsNewIdsSet.add(creativity.id)
        }
        for (games: Games in schema.games!!) {
            boardsNewIdsSet.add(games.id)
        }
        schema.japanese!!.mapTo(boardsNewIdsSet) { it.id }
        for (other: Other in schema.other!!) {
            boardsNewIdsSet.add(other.id)
        }
        for (politics: Politics in schema.politics!!) {
            boardsNewIdsSet.add(politics.id)
        }
        for (subjects: Subjects in schema.subject!!) {
            boardsNewIdsSet.add(subjects.id)
        }
        for (tech: Tech in schema.tech!!) {
            boardsNewIdsSet.add(tech.id)
        }
        for (users: Users in schema.users!!) {
            boardsNewIdsSet.add(users.id)
        }

        val cursor: Cursor = context.contentResolver.query(DatabaseContract.BoardsEntry.CONTENT_URI,
                mBoardsProjection, null, null, null, null)
        val columnIndex = cursor.getColumnIndex(DatabaseContract.BoardsEntry.COLUMN_BOARD_ID)
        var boardId: String
        val boardsDatabaseIdsSet: HashSet<String> = HashSet()
        while (cursor.moveToNext()) {
            boardId = cursor.getString(columnIndex)
            boardsDatabaseIdsSet.add(boardId)
        }
        cursor.close()

        val resultSet: Set<String?> = boardsDatabaseIdsSet.subtract(boardsNewIdsSet)

        Log.d("BoardsUtils", "resultSet: " + resultSet)

        for (boardId: String? in resultSet) {
            deleteABoard(boardId!!, context)
        }
    }

    fun deleteABoard(boardId: String, activity: Activity) {
        Log.d("BoardsUtils", "deleting board: " + boardId)
        activity.contentResolver.delete(DatabaseContract.BoardsEntry.CONTENT_URI,
                DatabaseContract.BoardsEntry.COLUMN_BOARD_ID + " =? ", arrayOf(boardId))
    }
}