package com.koresuniku.wishmasterV4.ui.dashboard.board_list

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import butterknife.BindView
import butterknife.ButterKnife
import com.koresuniku.wishmasterV4.R
import com.koresuniku.wishmasterV4.core.dashboard.BoardListView
import com.koresuniku.wishmasterV4.core.dashboard.DashboardPresenter
import com.koresuniku.wishmasterV4.core.data.boards.BoardListData
import com.koresuniku.wishmasterV4.core.data.boards.BoardListsObject
import com.koresuniku.wishmasterV4.core.data.boards.BoardsMapper
import com.koresuniku.wishmasterV4.ui.base.BaseWishmasterActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by koresuniku on 10.11.17.
 */

class BoardListFragment : Fragment(), BoardListView {
    private val LOG_TAG = BoardListFragment::class.java.simpleName

   @Inject lateinit var presenter: DashboardPresenter

    private lateinit var mRootView: View
    @BindView(R.id.board_list) lateinit var mBoardList: ExpandableListView

    private lateinit var mBoardListAdapter: BoardListAdapter
    private lateinit var mCompositeDisposable: CompositeDisposable

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mRootView = inflater.inflate(R.layout.fragment_board_list, container, false)
        ButterKnife.bind(this, mRootView)
        (activity as BaseWishmasterActivity)
                .getWishmasterApplication()
                .getDaggerDashboardComponent()
                .inject(this)
        presenter.bindDashboardBoardListView(this)

        mCompositeDisposable = CompositeDisposable()
        loadBoards()

        return mRootView
    }

    override fun onBoardsDataReceived(boardListData: BoardListData) {
        val boardLists = BoardsMapper.mapToBoardsDataByCategory(boardListData)
        activity?.runOnUiThread { setupBoardListAdapter(boardLists) }
    }

    private fun loadBoards() {
        mCompositeDisposable.add(presenter.getLoadBoardsObservable()
                .subscribeOn(Schedulers.newThread())
                .map(BoardsMapper::mapToBoardsDataByCategory)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setupBoardListAdapter, { e -> e.printStackTrace() }))
    }

    private fun setupBoardListAdapter(boardListsObject: BoardListsObject) {
        context?.let {
            mBoardListAdapter = BoardListAdapter(it, boardListsObject, presenter, mCompositeDisposable)
            mBoardList.setAdapter(mBoardListAdapter)
            mBoardList.setGroupIndicator(null)
            mBoardList.setOnChildClickListener { _, _, groupPosition, childPosition, _ ->
                presenter.shouldLaunchThreadListActivity(boardListsObject.boardLists[groupPosition].second[childPosition].getBoardId())
                false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unbindDashboardBoardListView()
        mCompositeDisposable.clear()
    }
}