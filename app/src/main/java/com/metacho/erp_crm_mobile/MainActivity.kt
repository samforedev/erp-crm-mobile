package com.metacho.erp_crm_mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.metacho.erp_crm_mobile.ui.data.UserPreferences
import com.metacho.erp_crm_mobile.ui.login.data.ILoginApiService
import com.metacho.erp_crm_mobile.ui.login.data.LoginRepository
import com.metacho.erp_crm_mobile.ui.login.ui.AuthViewModel
import com.metacho.erp_crm_mobile.ui.login.ui.AuthViewModelFactory
import com.metacho.erp_crm_mobile.ui.login.ui.LoginViewModel
import com.metacho.erp_crm_mobile.ui.navigation.AppNavigation
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val api = Retrofit.Builder()
            .baseUrl("https://metacho.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ILoginApiService::class.java)

        val repository = LoginRepository(api)
        val userPrefs = UserPreferences(this)

        setContent {

            val authViewModel: AuthViewModel = viewModel(
                factory = AuthViewModelFactory(userPrefs)
            )

            val isLogged by authViewModel.isLogged.collectAsState()

            AppNavigation(
                isLogged = isLogged,
                repository = repository,
                userPrefs = userPrefs
            )
        }
    }
}