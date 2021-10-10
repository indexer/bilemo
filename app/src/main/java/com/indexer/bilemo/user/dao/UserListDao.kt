package com.indexer.bilemo.user.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.indexer.bilemo.user.enitity.UserResponse

@Dao
interface UserListDao {
    @Query("SELECT * FROM UserResponse")
    suspend fun getAllUserData(): List<UserResponse>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserData(userList: List<UserResponse>)


    @Query("SELECT * FROM UserResponse where id = :userId")
    suspend fun getUserById(userId: Int): List<UserResponse>

}