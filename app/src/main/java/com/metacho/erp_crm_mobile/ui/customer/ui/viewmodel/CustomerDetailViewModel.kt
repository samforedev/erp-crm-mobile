package com.metacho.erp_crm_mobile.ui.customer.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.metacho.erp_crm_mobile.ui.customer.data.dto.ChangeCustomerStatusRequest
import com.metacho.erp_crm_mobile.ui.customer.data.dto.CustomerDetail
import com.metacho.erp_crm_mobile.ui.customer.domain.CustomerRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class CustomerDetailUiState(
    val isLoading: Boolean = false,
    val customer: CustomerDetail? = null,
    val error: String? = null
)

class CustomerDetailViewModel(
    private val repository: CustomerRepository,
    private val customerId: String
): ViewModel() {
    private val _state = MutableStateFlow(CustomerDetailUiState())
    val state = _state.asStateFlow()

    init {
        loadCustomer()
    }

    fun loadCustomer() {
        viewModelScope.launch {
            _state.value = CustomerDetailUiState(isLoading = true)

            try {
                val result = repository.getCustomerById(customerId)
                _state.value = CustomerDetailUiState(customer = result.data)
            } catch (e: Exception) {
                _state.value = CustomerDetailUiState(error = e.message)
            }
        }
    }

    fun updateStatus(newStatus: String) {
        val customer = _state.value.customer ?: return

        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)

            try {
                val updated = repository.changeCustomerStatus(
                    ChangeCustomerStatusRequest(customerId, newStatus))

                val customer = repository.getCustomerById(customerId)

                _state.value = _state.value.copy(
                    isLoading = false,
                    customer = customer.data,
                    error = null
                )
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.message ?: "Error actualizando estado"
                )
            }
        }
    }

}