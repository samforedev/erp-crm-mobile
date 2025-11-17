package com.metacho.erp_crm_mobile.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.metacho.erp_crm_mobile.ui.common.ui.components.MainWithDrawer
import com.metacho.erp_crm_mobile.ui.data.UserPreferences
import com.metacho.erp_crm_mobile.ui.home.ui.HomeScreen
import com.metacho.erp_crm_mobile.ui.login.domain.LoginRepository
import com.metacho.erp_crm_mobile.ui.login.ui.LoginScreen
import com.metacho.erp_crm_mobile.ui.login.ui.LoginViewModel
import com.metacho.erp_crm_mobile.ui.login.ui.LoginViewModelFactory
import com.metacho.erp_crm_mobile.ui.user.domain.UserRepository
import com.metacho.erp_crm_mobile.ui.user.ui.UserScreen

@Composable
fun AppNavigation(
    isLogged: Boolean,
    loginRepository: LoginRepository,
    userRepository: UserRepository,
    userPrefs: UserPreferences
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Login.route
    ) {

        composable(Routes.Login.route) {
            val viewModel: LoginViewModel = viewModel(
                factory = LoginViewModelFactory(loginRepository, userPrefs)
            )

            LoginScreen(
                viewModel = viewModel,
                onLoginSuccess = {
                    navController.navigate(Routes.Home.route) {
                        popUpTo(Routes.Login.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.Home.route) {
            MainWithDrawer(navController) {
                HomeScreen(
                    onEmployeesClick = { navController.navigate(Routes.User.route) },
                    onCustomersClick = { navController.navigate(Routes.User.route) },
                    onReportsClick = { navController.navigate(Routes.User.route) }
                )
            }
        }

        composable(Routes.User.route) {
            MainWithDrawer(navController) {
                UserScreen(
                    repository = userRepository
                )
            }
        }

    }
}