package com.koresuniku.wishmaster_v4.ui.view.widget

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.LinearLayoutManager

/**
 * Created by koresuniku on 07.01.18.
 */

class WrapContentLinearLayoutManager(context: Context) : LinearLayoutManager(context) {

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State) {
        try {
            super.onLayoutChildren(recycler, state)
        } catch (e: IndexOutOfBoundsException) {
            e.printStackTrace()
        }

    }
}