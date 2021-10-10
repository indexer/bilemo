package com.indexer.bilemo.module

import com.indexer.bilemo.base.DataStoreRepository
import com.indexer.bilemo.login.repository.LoginRepository
import com.indexer.bilemo.login.usecase.GetAllLoginUseCase
import com.indexer.bilemo.splash.usecase.InsertLoginUseCase
import com.indexer.bilemo.user.repository.UserRepository
import com.indexer.bilemo.user.usecase.GetAllLUserUseCase
import com.indexer.bilemo.user.usecase.GetUserCaseById
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Singleton
    fun provideLoginUserInsertCase(loginRepository: LoginRepository) =
        InsertLoginUseCase(loginRepository)


    @Singleton
    @Provides
    fun provideGetAllLoginUseCase(
        loginRepository: LoginRepository
    ): GetAllLoginUseCase = GetAllLoginUseCase(loginRepository)

    @Singleton
    @Provides
    fun provideGetAllUserResponeUseCase(
        userRepository: UserRepository, dataStoreRepository: DataStoreRepository
    ): GetAllLUserUseCase = GetAllLUserUseCase(userRepository, dataStoreRepository)


    @Singleton
    @Provides
    fun provideGetUserByIdUseCase(
        userRepository: UserRepository
    ): GetUserCaseById = GetUserCaseById(userRepository)

}
