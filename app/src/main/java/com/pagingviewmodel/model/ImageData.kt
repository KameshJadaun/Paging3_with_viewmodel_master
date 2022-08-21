package com.pagingviewmodel.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ImageData(
    @SerializedName("id")
    @Expose
    var id: String? = null,

    @SerializedName("author")
    @Expose
    var author: String? = null,

    @SerializedName("width")
    @Expose
    var width: Long? = null,

    @SerializedName("height")
    @Expose
    var height: Long? = null,

    @SerializedName("url")
    @Expose
    var url: String? = null,

    @SerializedName("download_url")
    @Expose
    var downloadUrl: String? = null
)