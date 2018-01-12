package com.koresuniku.wishmaster_v4.ui.thread_list

import android.support.v7.widget.RecyclerView
import android.text.Spannable
import android.text.Spanned
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.koresuniku.wishmaster_v4.R
import com.koresuniku.wishmaster_v4.core.thread_list.ThreadItemView

/**
 * Created by koresuniku on 07.01.18.
 */

class ThreadItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), ThreadItemView {

    @BindView(R.id.subject) lateinit var mSubject: TextView
    @BindView(R.id.comment) lateinit var mComment: TextView

    init { ButterKnife.bind(this, itemView) }

    override fun setSubject(subject: Spanned) {
        if (subject.isEmpty()) {
            mSubject.visibility = View.GONE
        } else {
            mSubject.visibility = View.VISIBLE
            mSubject.text = subject
        }
    }

    override fun setComment(comment: Spanned) {
        mComment.text = comment
    }
}