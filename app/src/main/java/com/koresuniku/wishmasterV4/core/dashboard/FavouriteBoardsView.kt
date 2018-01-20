package com.koresuniku.wishmasterV4.core.dashboard

import com.koresuniku.wishmasterV4.core.base.IMvpView
import com.koresuniku.wishmasterV4.core.data.boards.BoardModel

/**
 * Created by koresuniku on 27.11.17.
 */

interface FavouriteBoardsView : IMvpView {
    fun onFavouriteBoardListChanged(boardList: List<BoardModel>)
}