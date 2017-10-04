package com.koresuniku.wishmaster_v4.core.data.boards

import com.koresuniku.wishmaster.domain.boards_api.BoardsJsonSchemaResponse
import com.koresuniku.wishmaster_v4.core.data.DatabaseContract

/**
 * Created by koresuniku on 04.10.17.
 */

object BoardsMapper {

    fun map(boardsJsonSchemaResponse: BoardsJsonSchemaResponse): BoardsData {
        val boardsDataResult = BoardsData()
        val boardListResult = ArrayList<BoardModel>()
        var boardModel: BoardModel

        for (adults in boardsJsonSchemaResponse.adults) {
            boardModel = BoardModel()
            boardModel.setBoardId(adults.id)
            boardModel.setBoardName(adults.name)
            boardModel.setBoardCategory(DatabaseContract.BoardsEntry.CATEGORY_ADULTS)
            boardListResult.add(boardModel)
        }
        for (creativity in boardsJsonSchemaResponse.creativity) {
            boardModel = BoardModel()
            boardModel.setBoardId(creativity.id)
            boardModel.setBoardName(creativity.name)
            boardModel.setBoardCategory(DatabaseContract.BoardsEntry.CATEGORY_CREATIVITY)
            boardListResult.add(boardModel)
        }
        for (games in boardsJsonSchemaResponse.games) {
            boardModel = BoardModel()
            boardModel.setBoardId(games.id)
            boardModel.setBoardName(games.name)
            boardModel.setBoardCategory(DatabaseContract.BoardsEntry.CATEGORY_GAMES)
            boardListResult.add(boardModel)
        }
        for (japanese in boardsJsonSchemaResponse.japanese) {
            boardModel = BoardModel()
            boardModel.setBoardId(japanese.id)
            boardModel.setBoardName(japanese.name)
            boardModel.setBoardCategory(DatabaseContract.BoardsEntry.CATEGORY_JAPANESE)
            boardListResult.add(boardModel)
        }
        for (other in boardsJsonSchemaResponse.other) {
            boardModel = BoardModel()
            boardModel.setBoardId(other.id)
            boardModel.setBoardName(other.name)
            boardModel.setBoardCategory(DatabaseContract.BoardsEntry.CATEGORY_OTHER)
            boardListResult.add(boardModel)
        }
        for (politics in boardsJsonSchemaResponse.politics) {
            boardModel = BoardModel()
            boardModel.setBoardId(politics.id)
            boardModel.setBoardName(politics.name)
            boardModel.setBoardCategory(DatabaseContract.BoardsEntry.CATEGORY_POLITICS)
            boardListResult.add(boardModel)
        }
        for (subjects in boardsJsonSchemaResponse.subject) {
            boardModel = BoardModel()
            boardModel.setBoardId(subjects.id)
            boardModel.setBoardName(subjects.name)
            boardModel.setBoardCategory(DatabaseContract.BoardsEntry.CATEGORY_SUBJECTS)
            boardListResult.add(boardModel)
        }
        for (tech in boardsJsonSchemaResponse.tech) {
            boardModel = BoardModel()
            boardModel.setBoardId(tech.id)
            boardModel.setBoardName(tech.name)
            boardModel.setBoardCategory(DatabaseContract.BoardsEntry.CATEGORY_TECH)
            boardListResult.add(boardModel)
        }
        for (users in boardsJsonSchemaResponse.users) {
            boardModel = BoardModel()
            boardModel.setBoardId(users.id)
            boardModel.setBoardName(users.name)
            boardModel.setBoardCategory(DatabaseContract.BoardsEntry.CATEGORY_USERS)
            boardListResult.add(boardModel)
        }

        boardsDataResult.setBoardList(boardListResult)
        return boardsDataResult
    }
}