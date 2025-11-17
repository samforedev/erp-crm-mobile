package com.metacho.erp_crm_mobile.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.metacho.erp_crm_mobile.ui.home.ui.HomeScreen
import com.metacho.erp_crm_mobile.ui.login.ui.LoginScreen
import com.metacho.erp_crm_mobile.ui.login.ui.LoginViewModel

@Composable
fun AppNavigation(loginViewModel: LoginViewModel) {
    val navController: NavHostController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Login.route) {
        composable(Routes.Login.route) {
            LoginScreen(
                viewModel = loginViewModel,
                onLoginSuccess = {
                    navController.navigate(Routes.Home.route) {
                        popUpTo(Routes.Login.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Routes.Home.route) {
            HomeScreen()
        }
    }

}