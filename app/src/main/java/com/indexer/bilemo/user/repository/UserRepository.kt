package com.indexer.bilemo.user.repository

import com.indexer.bilemo.base.GenericError
import com.indexer.bilemo.base.NetworkResponse
import com.indexer.bilemo.base.Resource
import com.indexer.bilemo.user.dao.UserListDao
import com.indexer.bilemo.user.enitity.UserResponse
import com.indexer.bilemo.user.service.UserApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class UserRepository @Inject constructor(
    var userApiService: UserApiService,
    var userListDao: UserListDao
) {

    private fun saveUserDataIntoDB(userList: List<UserResponse>) {
        userListDao.insertUserData(userList)
    }

    suspend fun getUserFromDatabase(userId: Int): Flow<UserResponse> {
        return flow{
            val data = userListDao.getUserById(userId)
            emit(data.first())
        }
    }

    suspend fun getUsersFromDatabase(): Flow<List<UserResponse>> {
        return flow {
            val data = userListDao.getAllUserData()
            emit(data)
        }
    }

    suspend fun getUserListFromNetwork(): Flow<Resource<*>> {
        return flow {
            emit(Resource.Loading)
            when (val data = userApiService.getUserList()) {
                is NetworkResponse.Success -> {
                    saveUserDataIntoDB(data.body)
                    emit(Resource.Success(data.body))
                }
                is NetworkResponse.ApiError -> {
                    val genericError = GenericError()
                    genericError.status = data.code
                    genericError.message = data.body.message
                    emit(Resource.Fail(genericError))
                }
                is NetworkResponse.NetworkError -> {
                    val genericError = GenericError()
                    genericError.message = data.error.toString()
                    emit(Resource.Fail(genericError))
                }
            }
        }
    }

}



