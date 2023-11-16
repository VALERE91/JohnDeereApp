package com.example.johndeereapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.johndeereapp.data.models.User
import com.example.johndeereapp.data.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {
    fun getUser() : User = repository.getUser()
}