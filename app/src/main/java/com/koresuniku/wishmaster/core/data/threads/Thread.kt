package com.koresuniku.wishmaster.core.data.threads

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.koresuniku.wishmaster.core.data.single_thread.Post

/**
 * Created by koresuniku on 01.01.18.
 */

class Thread {

    @SerializedName("comment")
    @Expose
    var comment: String? = null

    @SerializedName("date")
    @Expose
    lateinit var date: String

    @SerializedName("files")
    @Expose
    var files: List<File>? = null

    @SerializedName("name")
    @Expose
    lateinit var name: String

    @SerializedName("num")
    @Expose
    lateinit var num: String

    @SerializedName("trip")
    @Expose
    lateinit var trip: String

    @SerializedName("subject")
    @Expose
    var subject: String? = null

    @SerializedName("posts")
    @Expose
    var posts: MutableList<Post>? = null

    @SerializedName("posts_count")
    @Expose
    var postsCount: Int = 0

    @SerializedName("files_count")
    @Expose
    var filesCount: Int = 0
}