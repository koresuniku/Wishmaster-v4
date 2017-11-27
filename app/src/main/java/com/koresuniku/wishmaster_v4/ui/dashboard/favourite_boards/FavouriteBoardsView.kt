package com.koresuniku.wishmaster_v4.ui.dashboard.favourite_boards

import com.koresuniku.wishmaster_v4.core.data.boards.FavouriteBoardsQueue

/**
 * Created by koresuniku on 27.11.17.
 */

interface FavouriteBoardsView {

    fun onQueueReceived(favouriteBoardsQueue: FavouriteBoardsQueue)

    fun onNothingReceived()
}