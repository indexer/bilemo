package com.indexer.bilemo.module

import android.content.Context
import com.indexer.bilemo.base.DataStoreRepository
import com.indexer.bilemo.base.DataStoreRepositoryImpl
import com.indexer.bilemo.login.dao.LoginUserDao
import com.indexer.bilemo.login.repository.LoginRepository
import com.indexer.bilemo.user.dao.UserListDao
import com.indexer.bilemo.user.repository.UserRepository
import com.indexer.bilemo.user.service.UserApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(apiService: UserApiService, userListDao: UserListDao) =
        UserRepository(apiService, userListDao)


    @Provides
    @Singleton
    fun provideLoginUserRepository(loginUserDao: LoginUserDao) = LoginRepository(loginUserDao)


    @Singleton
    @Provides
    fun provideDataStoreRepository(
        @ApplicationContext app: Context
    ): DataStoreRepository = DataStoreRepositoryImpl(app)

}
