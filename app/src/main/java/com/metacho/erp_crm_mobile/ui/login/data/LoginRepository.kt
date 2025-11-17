package com.metacho.erp_crm_mobile.ui.login.data

import com.metacho.erp_crm_mobile.ui.data.ApiResponse

class LoginRepository(private val api: ILoginApiService) {
    suspend fun login(usernameOrEmail: String, password: String): ApiResponse<LoginResponse> {
        return api.login(LoginRequest(usernameOrEmail, password))
    }
}