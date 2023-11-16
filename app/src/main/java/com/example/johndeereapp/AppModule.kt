package com.example.johndeereapp

import android.content.Context
import androidx.room.Room
import com.example.johndeereapp.data.AppDatabase
import com.example.johndeereapp.data.dao.UserDao
import com.example.johndeereapp.data.online.UserApiInterface
import com.example.johndeereapp.data.repositories.UserCacheRepository
import com.example.johndeereapp.data.repositories.UserRepository
import com.example.johndeereapp.data.sources.UserOnlineDataSource
import com.example.johndeereapp.data.sources.UserRoomDataSource
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
//    @Provides
//    @Named("URL")
//    fun getBaseUrl(): String {
//        return "https://654396ab01b5e279de209eeb.mockapi.io/"
//    }
//
//    @Singleton
//    @Provides
//    fun getUserRepository(onlineDataSource: UserOnlineDataSource,
//                          roomDataSource: UserRoomDataSource) : UserRepository {
//        return UserCacheRepository(onlineDataSource, roomDataSource)
//    }
//
//    @Singleton
//    @Provides
//    fun getUserApiService(
//        client: OkHttpClient,
//        @Named("URL") baseURL: String,
//        factory: GsonConverterFactory
//    ): UserApiInterface {
//        return Retrofit.Builder()
//            .baseUrl(baseURL)
//            .addConverterFactory(factory)
//            .client(client)
//            .build()
//            .create(UserApiInterface::class.java)
//    }
//
//    @Singleton
//    @Provides
//    fun getClient(): OkHttpClient {
//        return OkHttpClient().newBuilder()
//            .connectTimeout(100, TimeUnit.SECONDS)
//            .writeTimeout(100, TimeUnit.SECONDS)
//            .readTimeout(100, TimeUnit.SECONDS)
//            .build()
//    }
//
//    @Singleton
//    @Provides
//    fun getConvertorFactory(): GsonConverterFactory {
//        val gson = GsonBuilder()
//            .setLenient()
//            .create()
//        return GsonConverterFactory.create(gson)
//    }
//
//    @Provides
//    fun provideUserDao(database: AppDatabase): UserDao {
//        return database.userDao()
//    }
//
//    @Provides
//    @Singleton
//    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
//        return Room.databaseBuilder(
//            context,
//            AppDatabase::class.java,
//            "my_app.db"
//        ).build()
//    }
}