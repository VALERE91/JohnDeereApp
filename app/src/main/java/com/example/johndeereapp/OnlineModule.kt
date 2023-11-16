package com.example.johndeereapp

import com.example.johndeereapp.data.online.UserApiInterface
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OnlineModule {
    @Provides
    @Named("BASE_URL")
    fun getBaseUrl() : String {
        return "https://654396ab01b5e279de209eeb.mockapi.io/"
    }

    @Singleton
    @Provides
    fun getClient(): OkHttpClient {
        return OkHttpClient().newBuilder()
            .connectTimeout(100, TimeUnit.SECONDS)
            .writeTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun getConvertorFactory(): GsonConverterFactory {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        return GsonConverterFactory.create(gson)
    }

    @Singleton
    @Provides
    fun getUserApiService(@Named("BASE_URL") baseUrl: String,
        client: OkHttpClient,
        factory: GsonConverterFactory) : UserApiInterface{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(factory)
            .build()
            .create(UserApiInterface::class.java)
    }
}