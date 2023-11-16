package com.example.johndeereapp.data.sources

import com.example.johndeereapp.data.models.User
import com.example.johndeereapp.data.online.UserApiInterface
import javax.inject.Inject

class UserOnlineDataSource @Inject constructor(
    private val userApi: UserApiInterface) {
    fun getUsers() : List<User> = userApi.listUsers()
}