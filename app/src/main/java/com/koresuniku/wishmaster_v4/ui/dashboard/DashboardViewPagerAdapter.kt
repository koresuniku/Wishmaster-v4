package com.koresuniku.wishmaster_v4.ui.dashboard

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

/**
* Created by koresuniku on 10.11.17.
*/

class DashboardViewPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    private val mCOUNT = 3

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return FavouriteBoardsFragment()
            1 -> return BoardListFragment()
            2 -> return FavouriteThreadsFragment()
        }
        return Fragment()
    }

    override fun getCount(): Int = mCOUNT


}