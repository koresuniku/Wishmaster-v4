package com.koresuniku.wishmaster_v4.core.dashboard

import com.koresuniku.wishmaster.domain.boards_api.BoardsJsonSchema
import com.koresuniku.wishmaster_v4.core.base.BasePresenter
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.disposables.Disposable

/**
 * Created by koresuniku on 03.10.17.
 */

class DashboardPresenter : BasePresenter<DashBoardView>() {

    fun loadBoards(): Observable<BoardsJsonSchema> {
        return Observable.create {
            val databaseObserva = Observable.create
        }
    }

    fun loadBoardsFromDatabase(): Maybe<BoardsJsonSchema> {
        return Maybe.create { e -> {

        }
        }
    }
}