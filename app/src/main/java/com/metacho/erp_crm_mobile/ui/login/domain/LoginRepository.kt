package com.metacho.erp_crm_mobile.ui.login.domain

import com.metacho.erp_crm_mobile.ui.data.ApiResponse
import com.metacho.erp_crm_mobile.ui.login.data.CreateUserRequest
import com.metacho.erp_crm_mobile.ui.login.data.ILoginApiService
import com.metacho.erp_crm_mobile.ui.login.data.LoginRequest
import com.metacho.erp_crm_mobile.ui.login.data.LoginResponse

class LoginRepository(private val api: ILoginApiService) {
    suspend fun login(usernameOrEmail: String, password: String): ApiResponse<LoginResponse> {
        return api.login(LoginRequest(usernameOrEmail, password))
    }

    suspend fun register(request: CreateUserRequest): ApiResponse<String> {
        return api.login(request)
    }

}