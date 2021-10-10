package com.indexer.bilemo.module

import android.app.Application
import com.indexer.bilemo.database.AppDatabase
import com.indexer.bilemo.login.dao.LoginUserDao
import com.indexer.bilemo.user.dao.UserListDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
open class ApplicationProviderModule {
    @Provides
    @Singleton
    open fun provideDatabase(application: Application): AppDatabase {
        return AppDatabase.getDatabase(application.applicationContext)
    }

    @Singleton
    @Provides
    fun provideLoginUserDao(
        appDatabase: AppDatabase
    ): LoginUserDao = appDatabase.userLoginUserDao

    @Singleton
    @Provides
    fun provideUserListDao(
        appDatabase: AppDatabase
    ): UserListDao = appDatabase.userListDao

}
