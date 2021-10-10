package com.indexer.bilemo.login.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LoginUser(
    @PrimaryKey var userName: String,
    var userPassword: String,
    val userCountry: String
)