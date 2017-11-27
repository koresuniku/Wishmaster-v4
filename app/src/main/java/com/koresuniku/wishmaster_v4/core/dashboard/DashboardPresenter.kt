package com.koresuniku.wishmaster_v4.core.dashboard

import android.content.SharedPreferences
import android.util.Log
import com.koresuniku.wishmaster.domain.boards_api.BoardsApiService
import com.koresuniku.wishmaster.domain.boards_api.BoardsJsonSchemaResponse
import com.koresuniku.wishmaster_v4.core.base.BaseRxPresenter
import com.koresuniku.wishmaster_v4.core.data.database.DatabaseHelper
import com.koresuniku.wishmaster_v4.core.data.boards.BoardsData
import com.koresuniku.wishmaster_v4.core.data.boards.BoardsRepository
import com.koresuniku.wishmaster_v4.core.data.boards.BoardsMapper
import com.koresuniku.wishmaster_v4.core.data.boards.FavouriteBoardsQueue
import com.koresuniku.wishmaster_v4.ui.dashboard.board_list.BoardListView
import com.koresuniku.wishmaster_v4.ui.dashboard.favourite_boards.FavouriteBoardsView
import io.reactivex.*
import javax.inject.Inject

/**
 * Created by koresuniku on 03.10.17.
 */

class DashboardPresenter @Inject constructor(): BaseRxPresenter<DashboardView>() {
    private val LOG_TAG = DashboardPresenter::class.java.simpleName

    @Inject lateinit var boardsApiService: BoardsApiService

    @Inject lateinit var databaseHelper: DatabaseHelper

    @Inject lateinit var sharedPreferences: SharedPreferences

    private lateinit var mLoadBoardObservable: Observable<BoardsData>

    private var mDashboardBoardListView: BoardListView? = null
    private var mFavouriteBoardsView: FavouriteBoardsView? = null

    override fun bindView(mvpView: DashboardView) {
        super.bindView(mvpView)
        mvpView.getWishmasterApplication().getDashBoardComponent().inject(this)

        mLoadBoardObservable = getNewLoadBoardsObservable()
        mLoadBoardObservable = mLoadBoardObservable.cache()
    }

    fun bindDashboardBoardListView(dashboardBoardListView: BoardListView) {
        this.mDashboardBoardListView = dashboardBoardListView
    }

    fun bindFavouriteBoardsView(favouriteBoardsView: FavouriteBoardsView) {
        this.mFavouriteBoardsView = favouriteBoardsView
    }

    fun getLoadBoardsObservable(): Observable<BoardsData> = mLoadBoardObservable

    fun reloadBoards() { mLoadBoardObservable = getNewLoadBoardsObservable().cache() }

    private fun getNewLoadBoardsObservable(): Observable<BoardsData> {
        return Observable.create( { e ->
            loadBoardsFromDatabase().subscribe(
                    { boardsData: BoardsData ->
                        e.onNext(boardsData)
                        mDashboardBoardListView?.onBoardsDataReceived(boardsData)
                    },
                    { throwable -> throwable.printStackTrace() },
                    { Log.d(LOG_TAG, "loading from network"); loadBoardsFromNetwork(e) })
        })
    }

    private fun loadBoardsFromDatabase(): Maybe<BoardsData> {
        return Maybe.create { e -> run {
            if (mView != null) {
                val boardsDataFromDatabase = BoardsRepository.getBoardsDataFromDatabase(databaseHelper.readableDatabase)
                if (boardsDataFromDatabase == null) { Log.d(LOG_TAG, "on database complete"); e.onComplete() }
                else { Log.d(LOG_TAG, "on database success"); e.onSuccess(boardsDataFromDatabase) }
            } else { Log.d(LOG_TAG, "on database error"); e.onError(Throwable()) }
        }
        }
    }

    private fun loadBoardsFromNetwork(e: ObservableEmitter<BoardsData>) {
        mView?.showLoadingBoards()
        val boardsObservable = boardsApiService.getBoardsObservable("get_boards")
        compositeDisposable.add(boardsObservable.map {
            boardsJsonSchemaResponse: BoardsJsonSchemaResponse ->
            BoardsMapper.mapResponse(boardsJsonSchemaResponse)
        }.subscribe(
                { boardsData: BoardsData ->
                    BoardsRepository.insertAllBoardsIntoDatabase(databaseHelper.writableDatabase, boardsData)
                    e.onNext(boardsData)
                    mDashboardBoardListView?.onBoardsDataReceived(boardsData)
                }, { throwable: Throwable -> e.onError(throwable) }))
    }

    override fun unbindView() {
        super.unbindView()
        databaseHelper.readableDatabase.close()
    }

    fun loadFavouriteBoardsQueue(): Maybe<FavouriteBoardsQueue> {
        return Maybe.create { e -> run {
            val queue = sharedPreferences.getString(
                    FavouriteBoardsQueue.FAVOURITE_BOARDS_KEY,
                    FavouriteBoardsQueue.FAVOURITE_BOARDS_DEFAULT_VALUE)
            if (queue == FavouriteBoardsQueue.FAVOURITE_BOARDS_DEFAULT_VALUE) e.onComplete()
            else {
                val boardsQueueObject = FavouriteBoardsQueue.getQueueObjectFromPreferences(queue)
                e.onSuccess(boardsQueueObject)
            }
        }
        }
    }

    fun unbindDashboardBoardListView() {
        this.mDashboardBoardListView = null
    }

    fun unbindFavouriteBoardsView() {
        this.mFavouriteBoardsView = null
    }
}