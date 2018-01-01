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
    private lateinit var comment: String

    @SerializedName("date")
    @Expose
    private lateinit var date: String

    @SerializedName("files")
    @Expose
    private lateinit var files: List<File>

    @SerializedName("name")
    @Expose
    private lateinit var name: String

    @SerializedName("num")
    @Expose
    private lateinit var num: String

    @SerializedName("files_count")
    @Expose
    private lateinit var filesCount: String

    @SerializedName("posts_count")
    @Expose
    private lateinit var postsCount: String

    @SerializedName("trip")
    @Expose
    private lateinit var trip: String

    @SerializedName("subject")
    @Expose
    private lateinit var subject: String

}