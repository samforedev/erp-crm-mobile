package com.metacho.erp_crm_mobile.ui.data

data class ApiResponse<T>(
    val data: T?,
    val message: String?,
    val isSuccess: Boolean,
    val errorMessage: String?,
    val errorCode: String?
)
