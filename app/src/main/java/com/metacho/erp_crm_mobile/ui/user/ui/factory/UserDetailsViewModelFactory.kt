package com.metacho.erp_crm_mobile.ui.user.ui.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.metacho.erp_crm_mobile.ui.user.domain.UserRepository
import com.metacho.erp_crm_mobile.ui.user.ui.viewmodel.UserDetailsViewModel

class UserDetailsViewModelFactory(
    private val userRepo: UserRepository,
    private val userId: String
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserDetailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserDetailsViewModel(userRepo, userId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}