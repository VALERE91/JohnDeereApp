package com.example.johndeereapp.data.sources

import com.example.johndeereapp.data.dao.UserDao
import javax.inject.Inject

class UserRoomDataSource @Inject constructor(private val userDao: UserDao) {
}