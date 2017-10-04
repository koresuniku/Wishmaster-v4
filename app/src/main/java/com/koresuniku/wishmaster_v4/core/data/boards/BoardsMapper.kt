package com.koresuniku.wishmaster_v4.core.data.boards

import com.koresuniku.wishmaster.domain.boards_api.BoardsJsonSchemaResponse
import com.koresuniku.wishmaster_v4.core.data.DatabaseContract

/**
 * Created by koresuniku on 04.10.17.
 */

object BoardsMapper {

    fun map(boardsJsonSchemaResponse: BoardsJsonSchemaResponse): BoardsData {
        val boardsDataResult = BoardsData()
        val boardListResult = ArrayList<WishmasterBoard>()
        var wishmasterBoard: WishmasterBoard

        for (adults in boardsJsonSchemaResponse.adults) {
            wishmasterBoard = WishmasterBoard()
            wishmasterBoard.setBoardId(adults.id)
            wishmasterBoard.setBoardName(adults.name)
            wishmasterBoard.setBoardCategory(DatabaseContract.BoardsEntry.CATEGORY_ADULTS)
            boardListResult.add(wishmasterBoard)
        }
        for (creativity in boardsJsonSchemaResponse.creativity) {
            wishmasterBoard = WishmasterBoard()
            wishmasterBoard.setBoardId(creativity.id)
            wishmasterBoard.setBoardName(creativity.name)
            wishmasterBoard.setBoardCategory(DatabaseContract.BoardsEntry.CATEGORY_CREATIVITY)
            boardListResult.add(wishmasterBoard)
        }
        for (games in boardsJsonSchemaResponse.games) {
            wishmasterBoard = WishmasterBoard()
            wishmasterBoard.setBoardId(games.id)
            wishmasterBoard.setBoardName(games.name)
            wishmasterBoard.setBoardCategory(DatabaseContract.BoardsEntry.CATEGORY_GAMES)
            boardListResult.add(wishmasterBoard)
        }
        for (japanese in boardsJsonSchemaResponse.japanese) {
            wishmasterBoard = WishmasterBoard()
            wishmasterBoard.setBoardId(japanese.id)
            wishmasterBoard.setBoardName(japanese.name)
            wishmasterBoard.setBoardCategory(DatabaseContract.BoardsEntry.CATEGORY_JAPANESE)
            boardListResult.add(wishmasterBoard)
        }
        for (other in boardsJsonSchemaResponse.other) {
            wishmasterBoard = WishmasterBoard()
            wishmasterBoard.setBoardId(other.id)
            wishmasterBoard.setBoardName(other.name)
            wishmasterBoard.setBoardCategory(DatabaseContract.BoardsEntry.CATEGORY_OTHER)
            boardListResult.add(wishmasterBoard)
        }
        for (politics in boardsJsonSchemaResponse.politics) {
            wishmasterBoard = WishmasterBoard()
            wishmasterBoard.setBoardId(politics.id)
            wishmasterBoard.setBoardName(politics.name)
            wishmasterBoard.setBoardCategory(DatabaseContract.BoardsEntry.CATEGORY_POLITICS)
            boardListResult.add(wishmasterBoard)
        }
        for (subjects in boardsJsonSchemaResponse.subject) {
            wishmasterBoard = WishmasterBoard()
            wishmasterBoard.setBoardId(subjects.id)
            wishmasterBoard.setBoardName(subjects.name)
            wishmasterBoard.setBoardCategory(DatabaseContract.BoardsEntry.CATEGORY_SUBJECTS)
            boardListResult.add(wishmasterBoard)
        }
        for (tech in boardsJsonSchemaResponse.tech) {
            wishmasterBoard = WishmasterBoard()
            wishmasterBoard.setBoardId(tech.id)
            wishmasterBoard.setBoardName(tech.name)
            wishmasterBoard.setBoardCategory(DatabaseContract.BoardsEntry.CATEGORY_TECH)
            boardListResult.add(wishmasterBoard)
        }
        for (users in boardsJsonSchemaResponse.users) {
            wishmasterBoard = WishmasterBoard()
            wishmasterBoard.setBoardId(users.id)
            wishmasterBoard.setBoardName(users.name)
            wishmasterBoard.setBoardCategory(DatabaseContract.BoardsEntry.CATEGORY_USERS)
            boardListResult.add(wishmasterBoard)
        }

        boardsDataResult.setBoardList(boardListResult)
        return boardsDataResult
    }
}