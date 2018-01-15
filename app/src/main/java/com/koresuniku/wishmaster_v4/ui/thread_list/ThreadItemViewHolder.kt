package com.koresuniku.wishmaster_v4.ui.thread_list

import android.support.v7.widget.RecyclerView
import android.text.Spanned
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.koresuniku.wishmaster_v4.R
import com.koresuniku.wishmaster_v4.core.data.threads.File
import com.koresuniku.wishmaster_v4.core.gallery.ImageLayoutConfiguration
import com.koresuniku.wishmaster_v4.core.thread_list.ThreadItemView
import com.koresuniku.wishmaster_v4.ui.dashboard.gallery.preview.PreviewImageGridAdapter

/**
 * Created by koresuniku on 07.01.18.
 */

class ThreadItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), ThreadItemView {
    private val LOG_TAG = ThreadItemViewHolder::class.java.simpleName

    @BindView(R.id.subject) lateinit var mSubject: TextView
    @BindView(R.id.comment) lateinit var mComment: TextView
    @BindView(R.id.resume) lateinit var mResume: TextView

    init { ButterKnife.bind(this, itemView) }

    override fun setSubject(subject: Spanned) {
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
        image.post {
            image.layoutParams.width = configuration.widthInPx
            image.layoutParams.height = configuration.heightInPx
            image.requestLayout()
        }
    }

    override fun setMultipleImages(files: List<File>, configuration: ImageLayoutConfiguration) {
        val imageGrid = itemView.findViewById<GridView>(R.id.image_grid)
        imageGrid.columnWidth = configuration.widthInPx
        //imageGrid.stretchMode = GridView.NO_STRETCH
        imageGrid.adapter = PreviewImageGridAdapter(files, configuration)
    }
}