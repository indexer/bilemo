package com.indexer.bilemo.splash.usecase

import com.indexer.bilemo.login.entities.LoginUser
import com.indexer.bilemo.login.repository.LoginRepository
import javax.inject.Inject

class InsertLoginUseCase @Inject constructor(private val loginUserRepository: LoginRepository) {
    suspend fun insertUsers(loginUsers: List<LoginUser>) {
        loginUserRepository.insertUser(loginUsers)
    }
}