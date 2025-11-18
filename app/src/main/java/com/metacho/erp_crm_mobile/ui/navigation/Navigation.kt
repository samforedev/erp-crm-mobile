package com.metacho.erp_crm_mobile.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.metacho.erp_crm_mobile.ui.common.ui.components.MainWithDrawer
import com.metacho.erp_crm_mobile.ui.customer.domain.CustomerRepository
import com.metacho.erp_crm_mobile.ui.customer.ui.screen.CustomerDetailScreen
import com.metacho.erp_crm_mobile.ui.customer.ui.screen.CustomerScreen
import com.metacho.erp_crm_mobile.ui.data.UserPreferences
import com.metacho.erp_crm_mobile.ui.home.ui.HomeScreen
import com.metacho.erp_crm_mobile.ui.login.domain.LoginRepository
import com.metacho.erp_crm_mobile.ui.login.ui.LoginScreen
import com.metacho.erp_crm_mobile.ui.login.ui.LoginViewModel
import com.metacho.erp_crm_mobile.ui.login.ui.LoginViewModelFactory
import com.metacho.erp_crm_mobile.ui.user.domain.UserRepository
import com.metacho.erp_crm_mobile.ui.user.ui.screen.UserDetailsScreen
import com.metacho.erp_crm_mobile.ui.user.ui.screen.UserScreen

@Composable
fun AppNavigation(
    isLogged: Boolean,
    loginRepository: LoginRepository,
    userRepository: UserRepository,
    customerRepository: CustomerRepository,
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
                    onCustomersClick = { navController.navigate(Routes.Customer.route) },
                    onReportsClick = { navController.navigate(Routes.User.route) }
                )
            }
        }

        composable(Routes.User.route) {
            MainWithDrawer(navController) {
                UserScreen(
                    repository = userRepository,
                    loginRepo = loginRepository,
                    onUserDetails = { userId ->
                        navController.navigate( Routes.UserDetail.createRoute(userId) )
                    }
                )
            }
        }

        composable(
            route = Routes.UserDetail.route,
            arguments = listOf(navArgument("userId") {
                type = NavType.StringType
            })
        ) { entry ->

            val userId = entry.arguments?.getString("userId") ?: ""

            MainWithDrawer(navController) {
                UserDetailsScreen(
                    repository = userRepository,
                    userId = userId,
                    onBack = { navController.navigate(Routes.User.route) }
                )
            }
        }

        composable(Routes.Customer.route) {
            MainWithDrawer(navController) {
                CustomerScreen(
                    repository = customerRepository,
                    onCustomerDetails = { customerId ->
                        navController.navigate( Routes.CustomerDetail.createRoute(customerId))
                    }
                )
            }
        }

        composable(
            route = Routes.CustomerDetail.route,
            arguments = listOf(navArgument("customerId") {
                type = NavType.StringType
            })
        ) { entry ->

            val customerId = entry.arguments?.getString("customerId") ?: ""

            MainWithDrawer(navController) {
                CustomerDetailScreen (
                    repository = customerRepository,
                    customerId = customerId,
                    onBack = { navController.navigate(Routes.Customer.route) }
                )
            }
        }

    }
}