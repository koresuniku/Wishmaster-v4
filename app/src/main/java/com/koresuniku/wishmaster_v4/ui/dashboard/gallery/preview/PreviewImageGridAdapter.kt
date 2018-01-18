package com.koresuniku.wishmaster_v4.ui.dashboard.gallery.preview

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.koresuniku.wishmaster_v4.R
import com.koresuniku.wishmaster_v4.core.data.threads.File
import com.koresuniku.wishmaster_v4.core.gallery.ImageLayoutConfiguration

/**
 * Created by koresuniku on 15.01.18.
 */

class PreviewImageGridAdapter(private val files: List<File>,
                              private val configuration: ImageLayoutConfiguration,
                              private val baseUrl: String) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var returnView: View? = convertView
        parent?.let {
            if (returnView == null) {
                returnView = LayoutInflater
                        .from(it.context)
                        .inflate(R.layout.image_layout, parent, false)
            }
            returnView?.let {
                val image = it.findViewById<ImageView>(R.id.image)
                image.post {
                    image.layoutParams.width = configuration.widthInPx
                    image.layoutParams.height = configuration.heightInPx
                    image.requestLayout()
                }
                it.requestLayout()
                image.setImageBitmap(null)
                image.animation?.cancel()
                image.setBackgroundColor(it.context.resources.getColor(R.color.colorBackgroundDark))

                Glide.with(it.context)
                        .load(Uri.parse(baseUrl + files[position].thumbnail))
                        .crossFade(200)
                        .placeholder(image.drawable)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true)
                        .into(image)
            }
        }
        return returnView ?: View(parent?.context)
    }

    override fun getItem(position: Int): Any = files[position]
    override fun getItemId(position: Int): Long = position.toLong()
    override fun getCount(): Int = files.count()
}