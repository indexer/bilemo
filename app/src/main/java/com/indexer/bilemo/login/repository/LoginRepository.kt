package com.indexer.bilemo.login.repository

import com.indexer.bilemo.login.dao.LoginUserDao
import com.indexer.bilemo.login.entities.LoginUser
import javax.inject.Inject


class LoginRepository @Inject constructor(private var userDao: LoginUserDao) {
    suspend fun checkLoginUser(loginUser: LoginUser): List<LoginUser> {
        return userDao.checkLoginUser(loginUser.userName, loginUser.userPassword)
    }

    fun insertUser(user: List<LoginUser>) {
        return userDao.insertAllUserInformation(user)
    }

}



