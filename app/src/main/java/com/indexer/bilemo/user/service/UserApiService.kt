package com.indexer.bilemo.user.service

import com.indexer.bilemo.base.GenericError
import com.indexer.bilemo.base.NetworkResponse
import com.indexer.bilemo.user.enitity.UserResponse
import retrofit2.http.GET

typealias GenericResponse<S> = NetworkResponse<S, GenericError>

interface UserApiService {
    @GET("/users")
    suspend fun getUserList(): GenericResponse<List<UserResponse>>
}