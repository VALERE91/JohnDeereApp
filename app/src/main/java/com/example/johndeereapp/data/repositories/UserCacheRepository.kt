package com.example.johndeereapp.data.repositories

import com.example.johndeereapp.data.models.User
import com.example.johndeereapp.data.sources.UserOnlineDataSource
import com.example.johndeereapp.data.sources.UserRoomDataSource
import javax.inject.Inject

class UserCacheRepository @Inject constructor(private val onlineDataSource: UserOnlineDataSource,
                                              private val roomUserDataSource: UserRoomDataSource) : UserRepository {
    override fun getUser(): User = onlineDataSource.getUsers()[0]
}