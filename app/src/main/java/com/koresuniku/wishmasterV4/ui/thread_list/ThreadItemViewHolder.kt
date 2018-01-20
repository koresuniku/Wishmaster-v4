package com.koresuniku.wishmasterV4.ui.thread_list

import android.support.v7.widget.RecyclerView
import android.text.Spannable
import android.text.Spanned
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.koresuniku.wishmasterV4.R
import com.koresuniku.wishmasterV4.core.gallery.ImageItemData
import com.koresuniku.wishmasterV4.core.gallery.WishmasterImageUtils
import com.koresuniku.wishmasterV4.core.thread_list.ThreadItemView
import com.koresuniku.wishmasterV4.ui.gallery.preview.PreviewImageGridAdapter
import com.koresuniku.wishmasterV4.ui.util.ViewUtils
import com.koresuniku.wishmasterV4.ui.view.widget.ExpandableHeightGridView

/**
 * Created by koresuniku on 07.01.18.
 */

class ThreadItemViewHolder(itemView: View, private val mBaseUrl: String) :
        RecyclerView.ViewHolder(itemView), ThreadItemView {
    private val LOG_TAG = ThreadItemViewHolder::class.java.simpleName

    @BindView(R.id.subject) lateinit var mSubject: TextView
    @BindView(R.id.comment) lateinit var mComment: TextView
    @BindView(R.id.resume) lateinit var mResume: TextView

    private lateinit var mSubjectString: Spanned

    init { ButterKnife.bind(this, itemView) }

    override fun setSubject(subject: Spanned, hasImages: Boolean) {
        mSubjectString = subject
        if (subject.isEmpty()) {
            mSubject.visibility = View.GONE
        } else {
            mSubject.visibility = View.VISIBLE
            mSubject.text = subject
        }
    }

    override fun setComment(comment: Spannable) {
        mComment.text = comment
       // mComment.text = WishmasterTextUtils.cutComment(comment, mComment)
    }
    override fun setResumeInfo(resume: String) { mResume.text = resume }

    override fun setSingleImage(imageItemData: ImageItemData) {
        val imageLayout = itemView.findViewById<ViewGroup>(R.id.image_layout)
        val image = imageLayout.findViewById<ImageView>(R.id.image)
        val imageCommentContainer = itemView.findViewById<ViewGroup>(R.id.image_comment_container)
        val imageSummary = itemView.findViewById<TextView>(R.id.summary)

        (imageCommentContainer.layoutParams as RelativeLayout.LayoutParams).topMargin =
               if (mSubjectString.isEmpty())
                   itemView.context.resources.getDimension(R.dimen.thread_item_image_comment_no_subject_top_margin).toInt()
               else itemView.context.resources.getDimension(R.dimen.thread_item_image_comment_no_subject_top_margin).toInt()

        imageSummary.text = imageItemData.summary
        WishmasterImageUtils.loadImageThumbnail(imageItemData, image, mBaseUrl)
    }

    override fun setMultipleImages(imageItemDataList: List<ImageItemData>) {
        val imageGrid = itemView.findViewById<ExpandableHeightGridView>(R.id.image_grid)
        (imageGrid.layoutParams as RelativeLayout.LayoutParams).topMargin =
                if (mSubjectString.isEmpty())
                    itemView.context.resources.getDimension(R.dimen.thread_item_image_comment_no_subject_top_margin).toInt()
                else itemView.context.resources.getDimension(R.dimen.thread_item_image_comment_no_subject_top_margin).toInt()
        imageGrid.columnWidth = imageItemDataList[0].configuration.widthInPx
        imageGrid.adapter = PreviewImageGridAdapter(imageItemDataList, mBaseUrl)

        val summaryTextView = imageGrid.adapter.getView(0, null, imageGrid).findViewById<TextView>(R.id.summary)
        ViewUtils.measureView(summaryTextView)
        ViewUtils.setGridViewHeight(
                imageGrid, imageItemDataList, imageItemDataList[0].configuration.widthInPx, summaryTextView.measuredHeight)
    }
}