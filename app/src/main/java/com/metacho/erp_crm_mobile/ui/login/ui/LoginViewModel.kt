package com.metacho.erp_crm_mobile.ui.login.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.metacho.erp_crm_mobile.ui.login.data.LoginRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class LoginUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val success: Boolean = false
)

class LoginViewModel(
    private val repository: LoginRepository
) : ViewModel() {

    private val _state = MutableStateFlow(LoginUiState())
    val state: StateFlow<LoginUiState> = _state

    fun login(usernameOrEmail: String, password: String) {
        viewModelScope.launch {
            _state.value = LoginUiState(isLoading = true)
            try {
                val response = repository.login(usernameOrEmail, password)
                if (response.isSuccess) {
                    _state.value = LoginUiState(success = true)
                } else {
                    _state.value = LoginUiState(error = response.errorCode)
                }
            } catch (e: Exception) {
                _state.value = LoginUiState(error = e.message ?: "Exception Error")
            }
        }
    }

}