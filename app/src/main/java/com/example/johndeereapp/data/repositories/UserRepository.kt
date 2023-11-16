package com.example.johndeereapp.data.repositories

import com.example.johndeereapp.data.models.User

interface UserRepository {
    fun getUser() : User
}