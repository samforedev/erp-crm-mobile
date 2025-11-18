package com.metacho.erp_crm_mobile.ui.customer.ui.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.metacho.erp_crm_mobile.ui.customer.domain.CustomerRepository
import com.metacho.erp_crm_mobile.ui.customer.ui.viewmodel.CustomerViewModel

class CustomerViewModelFactory(
    private val repository: CustomerRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CustomerViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CustomerViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}