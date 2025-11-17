package com.metacho.erp_crm_mobile.ui.user.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.metacho.erp_crm_mobile.ui.user.data.dto.UserMinimal
import com.metacho.erp_crm_mobile.ui.user.domain.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class UsersUiState(
    val isLoading: Boolean = false,
    val users: List<UserMinimal> = emptyList(),
    val error: String? = null
)

class UserViewModel(
    private val repository: UserRepository
) : ViewModel() {

    private val _state = MutableStateFlow(UsersUiState())
    val state: StateFlow<UsersUiState> = _state

    init {
        loadUsers()
    }

    fun loadUsers() {
        viewModelScope.launch {
            _state.value = UsersUiState(isLoading = true)

            try {
                val response = repository.getAllUsers("ACTIVE")

                if (response.isSuccess) {
                    _state.value = UsersUiState(users = response.data ?: emptyList())
                } else {
                    _state.value = UsersUiState(error = response.message)
                }

            } catch (e: Exception) {
                _state.value = UsersUiState(error = e.message ?: "Unknown error")
            }
        }
    }
}