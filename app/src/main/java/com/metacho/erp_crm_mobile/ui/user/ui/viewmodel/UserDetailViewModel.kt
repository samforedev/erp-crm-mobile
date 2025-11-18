package com.metacho.erp_crm_mobile.ui.user.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.metacho.erp_crm_mobile.ui.user.data.dto.UserDetail
import com.metacho.erp_crm_mobile.ui.user.domain.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class UserDetailsUiState(
    val isLoading: Boolean = false,
    val user: UserDetail? = null,
    val error: String? = null
)

class UserDetailsViewModel(
    private val repository: UserRepository,
    private val userId: String
) : ViewModel() {

    private val _state = MutableStateFlow(UserDetailsUiState())
    val state = _state.asStateFlow()

    init {
        loadUser()
    }

    fun loadUser() {
        viewModelScope.launch {
            _state.value = UserDetailsUiState(isLoading = true)

            try {
                val result = repository.getById(userId)
                _state.value = UserDetailsUiState(user = result.data)
            } catch (e: Exception) {
                _state.value = UserDetailsUiState(error = e.message)
            }
        }
    }
}