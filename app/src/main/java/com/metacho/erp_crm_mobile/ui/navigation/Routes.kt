package com.metacho.erp_crm_mobile.ui.navigation

sealed class Routes(val route: String) {
    object Login: Routes("login")
    object Home: Routes("home")
    object User: Routes("user")
}