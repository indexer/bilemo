package com.indexer.bilemo.login.usecase

import com.indexer.bilemo.login.entities.LoginUser
import com.indexer.bilemo.login.repository.LoginRepository
import javax.inject.Inject

class GetAllLoginUseCase @Inject constructor(private val loginUserRepository: LoginRepository) {
    suspend fun getAllLoginUser(loginUser: LoginUser): List<LoginUser> {
        return loginUserRepository.checkLoginUser(loginUser)
    }
}