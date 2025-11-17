package com.metacho.erp_crm_mobile.ui.user.domain

import com.metacho.erp_crm_mobile.ui.data.ApiResponse
import com.metacho.erp_crm_mobile.ui.user.data.IUserApiService
import com.metacho.erp_crm_mobile.ui.user.data.dto.GetAll
import com.metacho.erp_crm_mobile.ui.user.data.dto.UserDetail
import com.metacho.erp_crm_mobile.ui.user.data.dto.UserMinimal

class UserRepository(private val api: IUserApiService) {
    suspend fun getAllUsers(status: String): ApiResponse<List<UserMinimal>> {
        return api.getAllUsers(GetAll(status))
    }

    suspend fun getById(id: String): ApiResponse<UserDetail> {
        return api.getById(id)
    }
}