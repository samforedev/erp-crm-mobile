package com.metacho.erp_crm_mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.metacho.erp_crm_mobile.ui.data.UserPreferences
import com.metacho.erp_crm_mobile.ui.login.ui.AuthViewModel
import com.metacho.erp_crm_mobile.ui.login.ui.AuthViewModelFactory
import com.metacho.erp_crm_mobile.ui.navigation.AppNavigation

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val userPrefs = UserPreferences(this)

        val loginRetrofit = AppModule.provideLoginRetrofit()
        val protectedRetrofit = AppModule.provideProtectedRetrofit(userPrefs)

        val loginApi = AppModule.provideLoginApi(loginRetrofit)
        val userApi = AppModule.provideUserApi(protectedRetrofit)

        val loginRepository = AppModule.provideLoginRepository(loginApi)
        val userRepository = AppModule.provideUserRepository(userApi)


        setContent {

            val authViewModel: AuthViewModel = viewModel(
                factory = AuthViewModelFactory(userPrefs)
            )

            val isLogged by authViewModel.isLogged.collectAsState()

            AppNavigation(
                isLogged = isLogged,
                loginRepository = loginRepository,
                userRepository = userRepository,
                userPrefs = userPrefs
            )
        }
    }
}