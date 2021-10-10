package com.indexer.bilemo.user.usecase

import com.indexer.bilemo.user.enitity.UserResponse
import com.indexer.bilemo.user.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserCaseById @Inject constructor(private val userRepository: UserRepository) {

    suspend fun getUserFromDatabase(userId: Int): Flow<UserResponse> {
        return userRepository.getUserFromDatabase(userId)
    }

}