package com.indexer.bilemo.login.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.indexer.bilemo.login.entities.LoginUser

@Dao
interface LoginUserDao {
    @Query("SELECT * FROM LoginUser where userPassword " +
            "== :userPassword and userName == :userName ")
    suspend fun checkLoginUser(userName: String, userPassword: String): List<LoginUser>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllUserInformation(ownerTypeList: List<LoginUser>)

}