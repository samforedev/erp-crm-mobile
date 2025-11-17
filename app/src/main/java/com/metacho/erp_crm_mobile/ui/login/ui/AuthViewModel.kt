package com.metacho.erp_crm_mobile.ui.login.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.metacho.erp_crm_mobile.ui.data.UserPreferences
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class AuthViewModel(private val userPrefs: UserPreferences) : ViewModel() {

    val isLogged = userPrefs.token.map { true }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        false
    )

}