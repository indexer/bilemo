package com.indexer.bilemo.user.enitity

import com.google.gson.annotations.SerializedName

data class Company(
    val bs: String,
    val catchPhrase: String,
    @SerializedName("name")
    val companyName: String
)