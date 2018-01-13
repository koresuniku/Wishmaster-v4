package com.koresuniku.wishmaster_v4.core.dagger.component

import com.koresuniku.wishmaster_v4.core.dagger.module.*
import com.koresuniku.wishmaster_v4.core.dashboard.DashboardPresenter
import com.koresuniku.wishmaster_v4.ui.dashboard.DashboardActivity
import com.koresuniku.wishmaster_v4.ui.dashboard.board_list.BoardListFragment
import com.koresuniku.wishmaster_v4.ui.dashboard.favourite_boards.FavouriteBoardsFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Created by koresuniku on 05.10.17.
 */

@Singleton
@Component (modules = [(AppModule::class), (DashboardModule::class), (DatabaseModule::class), (NetModule::class)])
interface DashboardComponent {

    fun inject(activity: DashboardActivity)

    fun inject(dashboardPresenter: DashboardPresenter)

    fun inject(boardListFragment: BoardListFragment)

    fun inject(favouriteBoardsFragment: FavouriteBoardsFragment)
}