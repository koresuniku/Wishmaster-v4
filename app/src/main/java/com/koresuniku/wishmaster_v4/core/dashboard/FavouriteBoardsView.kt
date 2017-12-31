package com.koresuniku.wishmaster_v4.core.dashboard

import com.koresuniku.wishmaster_v4.core.base.IMvpView
import com.koresuniku.wishmaster_v4.core.data.boards.BoardModel
import com.koresuniku.wishmaster_v4.core.data.boards.FavouriteBoardsQueue

/**
 * Created by koresuniku on 27.11.17.
 */

interface FavouriteBoardsView : IMvpView {
    fun onFavouriteBoardListChanged(boardList: List<BoardModel>)
}