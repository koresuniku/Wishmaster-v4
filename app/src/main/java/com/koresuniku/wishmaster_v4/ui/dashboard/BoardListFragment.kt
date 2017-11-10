package com.koresuniku.wishmaster_v4.ui.dashboard

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.koresuniku.wishmaster_v4.R

/**
 * Created by koresuniku on 10.11.17.
 */

class BoardListFragment : Fragment() {
    private val LOG_TAG = BoardListFragment::class.java.simpleName

    private lateinit var mRootView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mRootView = inflater.inflate(R.layout.fragment_board_list, container, false)

        return mRootView
    }
}