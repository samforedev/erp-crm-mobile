package com.metacho.erp_crm_mobile.ui.login.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.metacho.erp_crm_mobile.ui.data.UserPreferences

class AuthViewModelFactory(
    private val userPrefs: UserPreferences
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AuthViewModel(userPrefs) as T
    }
}