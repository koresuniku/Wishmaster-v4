package com.koresuniku.wishmaster_v4.ui.dashboard.board_list

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import butterknife.BindView
import butterknife.ButterKnife
import com.koresuniku.wishmaster_v4.R
import com.koresuniku.wishmaster_v4.core.dashboard.DashboardPresenter
import com.koresuniku.wishmaster_v4.core.data.boards.BoardsMapper
import com.koresuniku.wishmaster_v4.ui.dashboard.DashboardActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by koresuniku on 10.11.17.
 */

class BoardListFragment : Fragment() {
    private val LOG_TAG = BoardListFragment::class.java.simpleName

    @Inject lateinit var presenter: DashboardPresenter

    private lateinit var mRootView: View
    @BindView(R.id.board_list) lateinit var mBoardList: ExpandableListView

    private lateinit var mBoardListAdapter: BoardListAdapter

    private lateinit var mCompositeDisposable: CompositeDisposable

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mRootView = inflater.inflate(R.layout.fragment_board_list, container, false)

        (activity as DashboardActivity)
                .getWishmasterApplication()
                .getDashBoardComponent()
                .inject(this)

        ButterKnife.bind(this, mRootView)

        mCompositeDisposable = CompositeDisposable()
        loadBoards()

        return mRootView
    }

    private fun loadBoards() {
        mCompositeDisposable.add(presenter.loadBoards()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(BoardsMapper::mapToArrayLists)
                .subscribe(this::setupBoardListAdapter, { e -> e.printStackTrace() }))
    }

    private fun setupBoardListAdapter(boardsList: ArrayList<Pair<String, ArrayList<Pair<String, String>>>>) {
        Log.d(LOG_TAG, "board list received")
        mBoardListAdapter = BoardListAdapter(context, boardsList)
        mBoardList.setAdapter(mBoardListAdapter)
        mBoardList.setGroupIndicator(null)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mCompositeDisposable.clear()
    }
}