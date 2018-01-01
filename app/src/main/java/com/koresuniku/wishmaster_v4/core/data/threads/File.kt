package com.koresuniku.wishmaster_v4.core.data.threads

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by koresuniku on 01.01.18.
 */

class File {

    @SerializedName("height")
    @Expose
    private lateinit var height: String

    @SerializedName("width")
    @Expose
    private lateinit var width: String

    @SerializedName("path")
    @Expose
    private lateinit var path: String

    @SerializedName("thumbnail")
    @Expose
    private lateinit var thumbnail: String

    @SerializedName("size")
    @Expose
    private lateinit var size: String

    @SerializedName("displayname")
    @Expose
    private lateinit var displayName: String

    @SerializedName("duration")
    @Expose
    private lateinit var duration: String
}