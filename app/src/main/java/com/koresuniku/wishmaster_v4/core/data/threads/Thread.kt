package com.koresuniku.wishmaster_v4.core.data.threads

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.koresuniku.wishmaster_v4.core.data.threads.File

/**
 * Created by koresuniku on 01.01.18.
 */

class Thread {

    @SerializedName("comment")
    @Expose
    lateinit var comment: String

    @SerializedName("date")
    @Expose
    lateinit var date: String

    @SerializedName("files")
    @Expose
    lateinit var files: List<File>

    @SerializedName("name")
    @Expose
    lateinit var name: String

    @SerializedName("num")
    @Expose
    lateinit var num: String

    @SerializedName("files_count")
    @Expose
    lateinit var filesCount: String

    @SerializedName("posts_count")
    @Expose
    lateinit var postsCount: String

    @SerializedName("trip")
    @Expose
    lateinit var trip: String

    @SerializedName("subject")
    @Expose
    lateinit var subject: String

}