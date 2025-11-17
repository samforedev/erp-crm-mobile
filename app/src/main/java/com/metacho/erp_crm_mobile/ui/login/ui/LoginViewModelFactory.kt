package com.metacho.erp_crm_mobile.ui.login.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.metacho.erp_crm_mobile.ui.data.UserPreferences
import com.metacho.erp_crm_mobile.ui.login.domain.LoginRepository

class LoginViewModelFactory(
    private val repository: LoginRepository,
    private val userPrefs: UserPreferences
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(repository, userPrefs) as T
    }
}