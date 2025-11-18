package com.metacho.erp_crm_mobile.ui.customer.ui.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.metacho.erp_crm_mobile.ui.customer.domain.CustomerRepository
import com.metacho.erp_crm_mobile.ui.customer.ui.viewmodel.CustomerDetailViewModel

class CustomerDetailViewModelFactory(
    private val customerRepo: CustomerRepository,
    private val customerId: String
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CustomerDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CustomerDetailViewModel(customerRepo, customerId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}