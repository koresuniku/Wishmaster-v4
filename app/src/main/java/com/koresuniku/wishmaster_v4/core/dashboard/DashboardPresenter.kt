package com.koresuniku.wishmaster_v4.core.dashboard

import android.util.Log
import com.koresuniku.wishmaster.domain.boards_api.BoardsApiService
import com.koresuniku.wishmaster.domain.boards_api.BoardsJsonSchemaResponse
import com.koresuniku.wishmaster_v4.core.base.BaseRxPresenter
import com.koresuniku.wishmaster_v4.core.data.database.DatabaseHelper
import com.koresuniku.wishmaster_v4.core.data.boards.BoardsData
import com.koresuniku.wishmaster_v4.core.data.boards.BoardsHelper
import com.koresuniku.wishmaster_v4.core.data.boards.BoardsMapper
import io.reactivex.*
import javax.inject.Inject

/**
 * Created by koresuniku on 03.10.17.
 */

class DashboardPresenter @Inject constructor(): BaseRxPresenter<DashboardView>() {
    private val LOG_TAG = DashboardPresenter::class.java.simpleName

    @Inject
    lateinit var boardsApiService: BoardsApiService

    @Inject
    lateinit var databaseHelper: DatabaseHelper

    private lateinit var mLoadBoardsSingle: Observable<BoardsData>
    private lateinit var mReloadBoardsObservable: Observable<Any>

    override fun bindView(mvpView: DashboardView) {
        super.bindView(mvpView)
        mvpView.getWishmasterApplication().getDashBoardComponent().inject(this)

        mLoadBoardsSingle = Observable.create( { e ->
            loadBoardsFromDatabase().subscribe(
                    { boardsData: BoardsData -> e.onNext(boardsData) },
                    { throwable -> throwable.printStackTrace() },
                    { Log.d(LOG_TAG, "loading from network"); loadBoardsFromNetwork(e) })
        })
        mLoadBoardsSingle = mLoadBoardsSingle.cache()
    }

    fun loadBoards(): Observable<BoardsData> = mLoadBoardsSingle

    private fun loadBoardsFromDatabase(): Maybe<BoardsData> {
        return Maybe.create { e -> run {
            if (mView != null) {
                val boardsDataFromDatabase =
                        BoardsHelper.getBoardsDataFromDatabase(databaseHelper.readableDatabase)
                if (boardsDataFromDatabase == null) { Log.d(LOG_TAG, "on database complete"); e.onComplete() }
                else { Log.d(LOG_TAG, "on database success"); e.onSuccess(boardsDataFromDatabase) }
            } else { Log.d(LOG_TAG, "on database error"); e.onError(Throwable()) }
            }
        }
    }

    private fun loadBoardsFromNetwork(e: ObservableEmitter<BoardsData>) {
        mView!!.showLoadingBoards()
        val boardsObservable = boardsApiService.getBoardsObservable("get_boards")
        compositeDisposable.add(boardsObservable.map {
            boardsJsonSchemaResponse: BoardsJsonSchemaResponse ->
            BoardsMapper.mapResponse(boardsJsonSchemaResponse)
        }.subscribe(
                { boardsData: BoardsData ->
                    BoardsHelper.insertAllBoardsIntoDatabase(databaseHelper.writableDatabase, boardsData)
                    e.onNext(boardsData)
                },
                { throwable: Throwable -> e.onError(throwable) }))
    }

    override fun unbindView() {
        super.unbindView()
        databaseHelper.readableDatabase.close()
    }
}