package com.example.hr_app.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hr_app.presentation.screens.Authorization
import com.example.hr_app.presentation.screens.PostLoginLoading
import com.example.hr_app.presentation.screens.Registration
import com.example.hr_app.presentation.screens.VacanciesMain
import com.example.hr_app.presentation.viewModel.AuthViewModel
import com.example.hr_app.presentation.viewModel.RegisterViewModel

// Назначение: Навигация между экранами
// Автор: Дерябина В.Н.
// Дата создания 20-05-2026
@Composable
fun NavigationScreen(modifier: Modifier = Modifier, padding: PaddingValues) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Authorization) {
        composable<Authorization> {
            val authViewModel: AuthViewModel = hiltViewModel()
            Authorization(
                authViewModel = authViewModel,
                navController = navController,
                modifier = Modifier.padding(padding)
            )
        }
        composable<PostLoginLoading> {
            PostLoginLoading(modifier = Modifier.padding(padding), navController = navController)
        }
        composable<Registration> {
            val registerViewModel: RegisterViewModel = hiltViewModel()
            Registration(
                viewModel = registerViewModel,
                navController = navController,
                modifier = Modifier.padding(padding)
            )
        }//VacanciesMain
        composable<VacanciesMain> {
            VacanciesMain(
                navController = navController,
                modifier = Modifier.padding(padding)
            )
        }
    }
}