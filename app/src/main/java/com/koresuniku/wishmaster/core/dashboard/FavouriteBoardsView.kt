package com.koresuniku.wishmaster.core.dashboard

import com.koresuniku.wishmaster.core.base.IMvpView
import com.koresuniku.wishmaster.core.data.boards.BoardModel

/**
 * Created by koresuniku on 27.11.17.
 */

interface FavouriteBoardsView : IMvpView {
    fun onFavouriteBoardListChanged(boardList: List<BoardModel>)
}