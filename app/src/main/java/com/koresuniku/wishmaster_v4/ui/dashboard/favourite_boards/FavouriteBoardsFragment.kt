package com.koresuniku.wishmaster_v4.ui.dashboard.favourite_boards

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import com.koresuniku.wishmaster_v4.R
import com.koresuniku.wishmaster_v4.core.dashboard.DashboardPresenter
import com.koresuniku.wishmaster_v4.ui.dashboard.DashboardActivity
import com.koresuniku.wishmaster_v4.ui.view.drag_and_swipe_recycler_view.OnStartDragListener
import com.koresuniku.wishmaster_v4.ui.view.drag_and_swipe_recycler_view.SimpleItemTouchItemCallback
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by koresuniku on 10.11.17.
 */

class FavouriteBoardsFragment : Fragment(), OnStartDragListener {
    private val LOG_TAG = FavouriteBoardsFragment::class.java.simpleName

    @Inject lateinit var presenter: DashboardPresenter

    private lateinit var mRootView: View
    @BindView(R.id.favourites_recycler_view) lateinit var recyclerView: RecyclerView
    @BindView(R.id.nothing_container) lateinit var nothingContainer: ViewGroup

    private lateinit var mCompositeDisposable: CompositeDisposable
    private lateinit var mItemTouchHelper: ItemTouchHelper
    private lateinit var mRecyclerViewAdapter: FavouriteBoardsRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mRootView = inflater.inflate(R.layout.fragment_favourite_boards, container, false)
        ButterKnife.bind(this, mRootView)
        (activity as DashboardActivity)
                .getWishmasterApplication()
                .getDashBoardComponent()
                .inject(this)

        mCompositeDisposable = CompositeDisposable()

        initRecyclerView()

        return mRootView
    }

    private fun initRecyclerView() {
        //TODO: receive data from presenter, otherwise set visibility gone
        nothingContainer.visibility = View.GONE

        mRecyclerViewAdapter = FavouriteBoardsRecyclerViewAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = mRecyclerViewAdapter
        recyclerView.addItemDecoration(FavouriteBoardsItemDividerDecoration(context))

        val callback = SimpleItemTouchItemCallback(mRecyclerViewAdapter)
        mItemTouchHelper = ItemTouchHelper(callback)
        mItemTouchHelper.attachToRecyclerView(recyclerView)
    }

    override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
        mItemTouchHelper.startDrag(viewHolder)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mCompositeDisposable.clear()
    }
}