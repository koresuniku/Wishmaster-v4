package com.koresuniku.wishmaster_v4.ui.dashboard.favourite_boards

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import com.koresuniku.wishmaster_v4.R
import com.koresuniku.wishmaster_v4.core.dashboard.DashboardPresenter
import com.koresuniku.wishmaster_v4.ui.dashboard.DashboardActivity
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by koresuniku on 10.11.17.
 */

class FavouriteBoardsFragment : Fragment() {
    private val LOG_TAG = FavouriteBoardsFragment::class.java.simpleName

    @Inject lateinit var presenter: DashboardPresenter

    private lateinit var mRootView: View
    @BindView(R.id.favourites_recycler_view) lateinit var favouriteRecyclerView: RecyclerView
    @BindView(R.id.nothing_container) lateinit var nothingContainer: ViewGroup

    private lateinit var mCompositeDisposable: CompositeDisposable

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mRootView = inflater.inflate(R.layout.fragment_favourite_boards, container, false)
        ButterKnife.bind(this, mRootView)
        (activity as DashboardActivity)
                .getWishmasterApplication()
                .getDashBoardComponent()
                .inject(this)

        mCompositeDisposable = CompositeDisposable()

        return mRootView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mCompositeDisposable.clear()
    }
}