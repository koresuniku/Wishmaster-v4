package com.koresuniku.wishmaster_v4.ui.dashboard.favourite_boards

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.koresuniku.wishmaster_v4.R
import com.koresuniku.wishmaster_v4.ui.view.drag_and_swipe_recycler_view.ItemTouchHelperAdapter
import com.koresuniku.wishmaster_v4.ui.view.drag_and_swipe_recycler_view.OnStartDragListener
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by koresuniku on 13.11.17.
 */

class FavouriteBoardsRecyclerViewAdapter(private val mOnStartDragListener: OnStartDragListener) :
        RecyclerView.Adapter<FavouriteBoardsRecyclerViewViewHolder>(), ItemTouchHelperAdapter {

    private lateinit var mFavouriteBoards: ArrayList<Pair<String, String>>

    override fun getItemCount(): Int = mFavouriteBoards.size

    override fun onBindViewHolder(holder: FavouriteBoardsRecyclerViewViewHolder?, position: Int) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteBoardsRecyclerViewViewHolder {
        return FavouriteBoardsRecyclerViewViewHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.fragment_favourite_boards_board_item, parent, false))
    }

    override fun onItemMoved(fromPosition: Int, toPosition: Int) {
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(mFavouriteBoards, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(mFavouriteBoards, i, i - 1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemRemoved(position: Int) {
    }

    override fun onSelectedChanged(actionState: Int) {
    }
}