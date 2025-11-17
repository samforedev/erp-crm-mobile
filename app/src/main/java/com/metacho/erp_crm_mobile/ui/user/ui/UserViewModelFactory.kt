package com.metacho.erp_crm_mobile.ui.user.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.metacho.erp_crm_mobile.ui.login.domain.LoginRepository
import com.metacho.erp_crm_mobile.ui.user.domain.UserRepository

class UserViewModelFactory(
    private val repository: UserRepository,
    private val logRepo: LoginRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserViewModel(repository, logRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}