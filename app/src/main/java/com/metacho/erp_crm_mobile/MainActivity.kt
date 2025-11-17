package com.metacho.erp_crm_mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.metacho.erp_crm_mobile.ui.login.data.ILoginApiService
import com.metacho.erp_crm_mobile.ui.login.data.LoginRepository
import com.metacho.erp_crm_mobile.ui.login.ui.LoginViewModel
import com.metacho.erp_crm_mobile.ui.navigation.AppNavigation
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {

    private val loginViewModel by lazy {
        val api = Retrofit.Builder()
            .baseUrl("https://metacho.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ILoginApiService::class.java)

        val repository = LoginRepository(api)

        LoginViewModel(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppNavigation(loginViewModel)
        }
    }
}