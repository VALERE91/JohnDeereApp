package com.example.johndeereapp

import com.example.johndeereapp.data.repositories.UserCacheRepository
import com.example.johndeereapp.data.repositories.UserRepository
import com.example.johndeereapp.data.sources.UserOnlineDataSource
import com.example.johndeereapp.data.sources.UserRoomDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {
    @Singleton
    @Provides
    fun getUserRepository(onlineDataSource: UserOnlineDataSource,
                          roomDataSource: UserRoomDataSource
    ) : UserRepository {
        return UserCacheRepository(onlineDataSource, roomDataSource)
    }
}