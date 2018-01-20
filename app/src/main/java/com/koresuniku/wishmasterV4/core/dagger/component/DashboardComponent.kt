package com.koresuniku.wishmasterV4.core.dagger.component

import com.koresuniku.wishmasterV4.core.dagger.module.*
import com.koresuniku.wishmasterV4.ui.dashboard.DashboardActivity
import com.koresuniku.wishmasterV4.ui.dashboard.board_list.BoardListFragment
import com.koresuniku.wishmasterV4.ui.dashboard.favourite_boards.FavouriteBoardsFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Created by koresuniku on 05.10.17.
 */

@Singleton
@Component (modules = [(DashboardModule::class)])
interface DashboardComponent {

    fun inject(activity: DashboardActivity)
    fun inject(boardListFragment: BoardListFragment)
    fun inject(favouriteBoardsFragment: FavouriteBoardsFragment)

}