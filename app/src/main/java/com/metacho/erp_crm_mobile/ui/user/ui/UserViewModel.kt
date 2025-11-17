package com.metacho.erp_crm_mobile.ui.user.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.metacho.erp_crm_mobile.ui.login.data.CreateUserRequest
import com.metacho.erp_crm_mobile.ui.login.domain.LoginRepository
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
    private val repository: UserRepository,
    private val loginRepo: LoginRepository
) : ViewModel() {

    var showCreateDialog by mutableStateOf(false)

    var firstName by mutableStateOf("")
    var lastName by mutableStateOf("")
    var documentType by mutableStateOf("CC")
    var documentNumber by mutableStateOf("")
    var birthDate by mutableStateOf("")
    var phoneNumber by mutableStateOf("")
    var email by mutableStateOf("")
    var username by mutableStateOf("")
    var password by mutableStateOf("")
    var jobTitle by mutableStateOf("")
    var initialRole by mutableStateOf("USER")

    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)

    private val _state = MutableStateFlow(UsersUiState())
    val state: StateFlow<UsersUiState> = _state

    init {
        loadUsers()
    }

    fun openCreateDialog() {
        showCreateDialog = true
    }

    fun closeDialog() {
        showCreateDialog = false
        resetForm()
    }

    private fun resetForm() {
        firstName = ""
        lastName = ""
        documentType = "CC"
        documentNumber = ""
        birthDate = ""
        phoneNumber = ""
        email = ""
        username = ""
        password = ""
        jobTitle = ""
        initialRole = "USER"
    }

    fun registerUser() {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null

            val request = CreateUserRequest(
                firstName, lastName, documentType, documentNumber,
                birthDate, phoneNumber, email, username, password,
                jobTitle, initialRole
            )

            val response = loginRepo.register(request)

            if (response.isSuccess) {
                closeDialog()
            } else {
                errorMessage = response.message
            }

            isLoading = false
        }
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