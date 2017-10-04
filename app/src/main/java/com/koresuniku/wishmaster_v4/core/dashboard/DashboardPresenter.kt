package com.koresuniku.wishmaster_v4.core.dashboard

import com.koresuniku.wishmaster.domain.boards_api.BoardsJsonSchemaResponse
import com.koresuniku.wishmaster_v4.core.base.BasePresenter
import io.reactivex.Maybe
import io.reactivex.Observable

/**
 * Created by koresuniku on 03.10.17.
 */

class DashboardPresenter : BasePresenter<DashBoardView>() {

    fun loadBoards(): Observable<BoardsJsonSchemaResponse> {
        return Observable.create {
            val databaseObserva = Observable.create
        }
    }

    fun loadBoardsFromDatabase(): Maybe<BoardsJsonSchemaResponse> {
        return Maybe.create { e -> {

        }
        }
    }
}