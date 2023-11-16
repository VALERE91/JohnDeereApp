package com.example.johndeereapp.data

import androidx.room.Database
import com.example.johndeereapp.data.dao.UserDao
import com.example.johndeereapp.data.models.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : androidx.room.RoomDatabase() {
    abstract fun userDao(): UserDao
}