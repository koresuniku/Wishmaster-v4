package com.koresuniku.wishmaster.domain.boards_api.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Japanese {

    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null

}
