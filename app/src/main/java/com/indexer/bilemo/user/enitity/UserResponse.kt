package com.indexer.bilemo.user.enitity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class UserResponse(
    @Embedded
    val address: Address,
    @Embedded
    val company: Company,
    val email: String,
    @PrimaryKey
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
)