package com.metacho.erp_crm_mobile.ui.customer.data

import com.metacho.erp_crm_mobile.ui.customer.data.dto.CustomerMinimal
import com.metacho.erp_crm_mobile.ui.data.ApiResponse
import retrofit2.http.GET

interface ICustomerApiService {
    @GET("customers")
    suspend fun getAllCustomers(): ApiResponse<List<CustomerMinimal>>
}