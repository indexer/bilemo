package com.indexer.bilemo.user.usecase

import com.indexer.bilemo.base.DataStoreRepository
import com.indexer.bilemo.base.Resource
import com.indexer.bilemo.user.enitity.UserResponse
import com.indexer.bilemo.user.repository.UserRepository
import com.indexer.bilemo.utils.IS_REMEMBER
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllLUserUseCase @Inject constructor(private val userRepository: UserRepository,private val
dataStoreRepository: DataStoreRepository) {

    suspend fun saveRememberState(boolean: Boolean) {
            dataStoreRepository.putBoolean(IS_REMEMBER, boolean)
    }
    suspend fun getUserFromDatabase(): Flow<List<UserResponse>> {
        return userRepository.getUsersFromDatabase()
    }

    suspend fun getAllUserFromApi(): Flow<Resource<*>> {
        return userRepository.getUserListFromNetwork()
    }
}