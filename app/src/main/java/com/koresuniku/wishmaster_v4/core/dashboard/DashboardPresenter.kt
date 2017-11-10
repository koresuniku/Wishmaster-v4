package com.koresuniku.wishmaster_v4.core.dashboard

import com.koresuniku.wishmaster.domain.boards_api.BoardsApiService
import com.koresuniku.wishmaster.domain.boards_api.BoardsJsonSchemaResponse
import com.koresuniku.wishmaster_v4.core.base.BasePresenter
import com.koresuniku.wishmaster_v4.core.data.boards.BoardsData
import com.koresuniku.wishmaster_v4.core.data.boards.BoardsHelper
import com.koresuniku.wishmaster_v4.core.data.boards.BoardsMapper
import io.reactivex.*
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * Created by koresuniku on 03.10.17.
 */

class DashboardPresenter @Inject constructor(): BasePresenter<DashboardView>() {

    @Inject
    lateinit var mRetrofit: Retrofit

    @Inject
    lateinit var mBoardsApiService: BoardsApiService

    private lateinit var mCompositeDisposable: CompositeDisposable

    override fun bindView(mvpView: DashboardView) {
        super.bindView(mvpView)
        mvpView.getWishmasterApplication().getNetComponent().inject(this)
        mCompositeDisposable = CompositeDisposable()
    }

    fun loadBoards(): Observable<BoardsData> {
        return Observable.create( { e ->
            loadBoardsFromDatabase().subscribe(
                    { boardsData: BoardsData -> e.onNext(boardsData) },
                    { throwable -> throwable.printStackTrace() },
                    {
                        val boardsObservable = mBoardsApiService.getBoardsObservable("get_boards")
                        mCompositeDisposable.add(boardsObservable.map {
                            boardsJsonSchemaResponse: BoardsJsonSchemaResponse ->
                            BoardsMapper.map(boardsJsonSchemaResponse)
                        }.subscribe(
                                { boardsData: BoardsData ->  e.onNext(boardsData) },
                                { throwable: Throwable -> e.onError(throwable) }))
                    } )
        })
    }

    private fun loadBoardsFromDatabase(): Maybe<BoardsData> {
        return Maybe.create { e -> run {
            if (mView != null) {
                val boardsDataFromDatabase =
                        BoardsHelper.getBoardsDataFromDatabase(mView!!.getWishmasterApplication())
                if (boardsDataFromDatabase.getBoardList().isEmpty()) { e.onComplete() }
                else { e.onSuccess(boardsDataFromDatabase) }
            } else { e.onError(Throwable()) }
            }
        }
    }

    override fun unbindView() {
        super.unbindView()
        mCompositeDisposable.clear()
    }
}