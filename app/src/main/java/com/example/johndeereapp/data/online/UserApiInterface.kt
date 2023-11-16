package com.example.johndeereapp.data.online

import com.example.johndeereapp.data.models.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApiInterface {
    @GET("users")
    fun listUsers(): List<User>
}