package com.koresuniku.wishmaster_v4.core.dashboard

import android.util.Log
import com.koresuniku.wishmaster.domain.boards_api.BoardsApiService
import com.koresuniku.wishmaster.domain.boards_api.BoardsJsonSchemaResponse
import com.koresuniku.wishmaster_v4.core.base.BasePresenter
import com.koresuniku.wishmaster_v4.core.data.DatabaseHelper
import com.koresuniku.wishmaster_v4.core.data.boards.BoardsData
import com.koresuniku.wishmaster_v4.core.data.boards.BoardsHelper
import com.koresuniku.wishmaster_v4.core.data.boards.BoardsMapper
import io.reactivex.*
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by koresuniku on 03.10.17.
 */

class DashboardPresenter @Inject constructor(): BasePresenter<DashboardView>() {
    private val LOG_TAG = DashboardPresenter::class.java.simpleName

    @Inject
    lateinit var boardsApiService: BoardsApiService

    @Inject
    lateinit var databaseHelper: DatabaseHelper

    private lateinit var mCompositeDisposable: CompositeDisposable

    override fun bindView(mvpView: DashboardView) {
        super.bindView(mvpView)
        mvpView.getWishmasterApplication().getDashBoardComponent().inject(this)
        mCompositeDisposable = CompositeDisposable()
    }

    fun loadBoards(): Single<BoardsData> {
        return Single.create( { e ->
            loadBoardsFromDatabase().subscribe(
                    { boardsData: BoardsData -> e.onSuccess(boardsData) },
                    { throwable -> throwable.printStackTrace() },
                    { loadBoardsFromNetwork(e) })
        })
    }

    private fun loadBoardsFromDatabase(): Maybe<BoardsData> {
        return Maybe.create { e -> run {
            if (mView != null) {
                val boardsDataFromDatabase =
                        BoardsHelper.getBoardsDataFromDatabase(databaseHelper.readableDatabase)
                if (boardsDataFromDatabase == null) { e.onComplete() }
                else { Log.d(LOG_TAG, "on database success"); e.onSuccess(boardsDataFromDatabase) }
            } else { Log.d(LOG_TAG, "on database error"); e.onError(Throwable()) }
            }
        }
    }

    private fun loadBoardsFromNetwork(e: SingleEmitter<BoardsData>) {
        mView!!.showLoadingBoards()
        val boardsObservable = boardsApiService.getBoardsObservable("get_boards")
        mCompositeDisposable.add(boardsObservable.map {
            boardsJsonSchemaResponse: BoardsJsonSchemaResponse ->
            BoardsMapper.map(boardsJsonSchemaResponse)
        }.subscribe(
                { boardsData: BoardsData ->
                    BoardsHelper.insertAllBoardsIntoDatabase(databaseHelper.writableDatabase, boardsData)
                    e.onSuccess(boardsData)
                },
                { throwable: Throwable -> e.onError(throwable) }))
    }

    override fun unbindView() {
        super.unbindView()
        mCompositeDisposable.clear()
    }
}