package com.metacho.erp_crm_mobile.ui.login.data

import com.metacho.erp_crm_mobile.ui.data.ApiResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ILoginApiService {
    @POST("auth/api/v1/auth/login")
    suspend fun login(@Body request: LoginRequest): ApiResponse<LoginResponse>
}