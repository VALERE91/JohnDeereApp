package com.example.johndeereapp.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.johndeereapp.ui.viewmodels.UserViewModel

@Composable
fun ProfileScreen(navController: NavController,
                  userViewModel: UserViewModel,
                  modifier: Modifier = Modifier){
    Text(text = userViewModel.getUser().username)
}