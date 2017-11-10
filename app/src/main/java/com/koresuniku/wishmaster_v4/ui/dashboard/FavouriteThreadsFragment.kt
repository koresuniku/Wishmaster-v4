package com.koresuniku.wishmaster_v4.ui.dashboard

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import com.koresuniku.wishmaster_v4.R

/**
 * Created by koresuniku on 10.11.17.
 */

class FavouriteThreadsFragment : Fragment() {
    private val LOG_TAG = FavouriteThreadsFragment::class.java.simpleName

    private lateinit var mRootView: View
    @BindView(R.id.)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mRootView = inflater.inflate(R.layout.fragment_favourite_threads, container, false)

        return mRootView
    }
}