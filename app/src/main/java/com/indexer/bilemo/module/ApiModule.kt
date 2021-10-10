package com.indexer.bilemo.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.indexer.bilemo.api.ApiUrlConfig
import com.indexer.bilemo.base.NetworkResponseAdapterFactory
import com.indexer.bilemo.user.service.UserApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
open class ApiModule {

    @Provides
    @Singleton
    open fun okHttpClientInstance(
        loggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    open fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    open fun loggingInterceptor(): HttpLoggingInterceptor {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY
        return logger
    }

    @Provides
    @Singleton
    open fun retrofitInstance(client: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder().client(client)
            .baseUrl(ApiUrlConfig.domainUrl)
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    open fun userApiService(retrofit: Retrofit): UserApiService =
        retrofit.create(UserApiService::class.java)

}
