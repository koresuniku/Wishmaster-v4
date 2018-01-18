package com.koresuniku.wishmaster_v4.ui.thread_list

import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.text.Spanned
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.koresuniku.wishmaster_v4.R
import com.koresuniku.wishmaster_v4.core.data.threads.File
import com.koresuniku.wishmaster_v4.core.domain.Dvach
import com.koresuniku.wishmaster_v4.core.gallery.ImageLayoutConfiguration
import com.koresuniku.wishmaster_v4.core.thread_list.ThreadItemView
import com.koresuniku.wishmaster_v4.ui.dashboard.gallery.preview.PreviewImageGridAdapter
import com.koresuniku.wishmaster_v4.ui.util.UiUtils

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

    override fun setComment(comment: Spanned) { mComment.text = comment }
    override fun setResumeInfo(resume: String) { mResume.text = resume }

    override fun setSingleImage(file: File, configuration: ImageLayoutConfiguration) {
        Log.d(LOG_TAG, configuration.toString())
        val imageLayout = itemView.findViewById<ViewGroup>(R.id.image_layout)
        val image = imageLayout.findViewById<ImageView>(R.id.image)
        val imageCommentContainer = itemView.findViewById<ViewGroup>(R.id.image_comment_container)
        (imageCommentContainer.layoutParams as RelativeLayout.LayoutParams).topMargin =
               if (mSubjectString.isEmpty())
                   itemView.context.resources.getDimension(R.dimen.thread_item_image_comment_no_subject_top_margin).toInt()
               else itemView.context.resources.getDimension(R.dimen.thread_item_image_comment_no_subject_top_margin).toInt()
        image.post {
            image.layoutParams.width = configuration.widthInPx
            image.layoutParams.height = configuration.heightInPx
            image.requestLayout()
        }
        Log.d(LOG_TAG, "mBaseUrl $mBaseUrl")
        image.setImageBitmap(null)
        image.animation?.cancel()
        image.setBackgroundColor(itemView.context.resources.getColor(R.color.colorBackgroundDark))

        Glide.with(itemView.context)
                .load(Uri.parse(mBaseUrl + file.thumbnail))
                .crossFade(200)
                .placeholder(image.drawable)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(image)
    }

    override fun setMultipleImages(files: List<File>, configuration: ImageLayoutConfiguration) {
        val imageGrid = itemView.findViewById<GridView>(R.id.image_grid)
        (imageGrid.layoutParams as RelativeLayout.LayoutParams).topMargin =
                if (mSubjectString.isEmpty())
                    itemView.context.resources.getDimension(R.dimen.thread_item_image_comment_no_subject_top_margin).toInt()
                else itemView.context.resources.getDimension(R.dimen.thread_item_image_comment_no_subject_top_margin).toInt()
        imageGrid.columnWidth = configuration.widthInPx
        imageGrid.adapter = PreviewImageGridAdapter(files, configuration, mBaseUrl)
    }
}