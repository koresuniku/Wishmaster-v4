package com.koresuniku.wishmaster_v4.core.dashboard

import com.koresuniku.wishmaster_v4.application.WishmasterApplication
import com.koresuniku.wishmaster_v4.core.base.BasePresenter
import com.koresuniku.wishmaster_v4.core.data.boards.BoardsData
import com.koresuniku.wishmaster_v4.core.data.boards.BoardsHelper
import io.reactivex.*
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * Created by koresuniku on 03.10.17.
 */

class DashboardPresenter : BasePresenter<DashBoardView>() {

    @Inject
    private lateinit var mRetrofit: Retrofit

    private lateinit var mCompositeDisposable: CompositeDisposable

    override fun attachView(mvpView: DashBoardView) {
        super.attachView(mvpView)
        mvpView.getWishmasterApplication().getNetComponent().inject(this)
        mCompositeDisposable = CompositeDisposable()
    }

    fun loadBoards(): Observable<BoardsData> {
        return Observable.create( { e ->
            loadBoardsFromDatabase().subscribe(
                    { boardsData -> e.onNext(boardsData) },
                    { throwable -> throwable.printStackTrace() },
                    { loadBoardsFromNetwork().subscribe()} )
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

    private fun loadBoardsFromNetwork(): Single<BoardsData> {

    }

    override fun detachView() {
        super.detachView()
        mCompositeDisposable.clear()
    }
}