package com.indexer.bilemo.base

import com.google.gson.annotations.SerializedName

class GenericError {
    @SerializedName("status")
    var status: Int? = null

    @SerializedName("type")
    var type: String? = null

    @SerializedName("message")
    var message: String? = null
}
