package com.metacho.erp_crm_mobile.ui.customer.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.metacho.erp_crm_mobile.ui.customer.data.dto.CustomerMinimal
import com.metacho.erp_crm_mobile.ui.customer.domain.CustomerRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class CustomerUiState(
    val isLoading: Boolean = false,
    val customers: List<CustomerMinimal> = emptyList(),
    val error: String? = null
)

class CustomerViewModel(
    private val customerRepo: CustomerRepository
): ViewModel() {

    private val _state = MutableStateFlow(CustomerUiState())
    val state: StateFlow<CustomerUiState> = _state

    init {
        loadCustomers()
    }

    fun loadCustomers() {
        viewModelScope.launch {
            _state.value = CustomerUiState(isLoading = true)

            try {
                val response = customerRepo.getAllCustomers()
                if (response.isSuccess) {
                    _state.value = CustomerUiState(customers = response.data ?: emptyList())
                } else {
                    _state.value = CustomerUiState(error = response.errorMessage)
                }
            } catch (e: Exception) {
                _state.value = CustomerUiState(error = e.message ?: "Unknown error")
            }

        }
    }


}