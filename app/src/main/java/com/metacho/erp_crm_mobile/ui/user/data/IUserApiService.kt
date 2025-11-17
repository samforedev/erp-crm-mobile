package com.metacho.erp_crm_mobile.ui.user.data

import com.metacho.erp_crm_mobile.ui.data.ApiResponse
import com.metacho.erp_crm_mobile.ui.user.data.dto.GetAll
import com.metacho.erp_crm_mobile.ui.user.data.dto.UserDetail
import com.metacho.erp_crm_mobile.ui.user.data.dto.UserMinimal
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface IUserApiService {
    @POST("users")
    suspend fun getAllUsers(@Body request: GetAll): ApiResponse<List<UserMinimal>>

    @GET("{id}")
    suspend fun getById(@Path("id") id: String): ApiResponse<UserDetail>

}