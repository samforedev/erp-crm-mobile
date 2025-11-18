package com.metacho.erp_crm_mobile.ui.navigation

sealed class Routes(val route: String) {
    object Login: Routes("login")
    object Home: Routes("home")
    object User: Routes("user")
    object UserDetail : Routes("user_detail/{userId}") {
        fun createRoute(userId: String) = "user_detail/$userId"
    }
    object Customer: Routes("customer")

    object CustomerDetail : Routes("customer_detail/{customerId}") {
        fun createRoute(customerId: String) = "customer_detail/$customerId"
    }

}