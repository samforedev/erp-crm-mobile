package com.metacho.erp_crm_mobile.ui.customer.data

import com.metacho.erp_crm_mobile.ui.customer.data.dto.ChangeCustomerStatusRequest
import com.metacho.erp_crm_mobile.ui.customer.data.dto.ChangeCustomerStatusResponse
import com.metacho.erp_crm_mobile.ui.customer.data.dto.CustomerDetail
import com.metacho.erp_crm_mobile.ui.customer.data.dto.CustomerMinimal
import com.metacho.erp_crm_mobile.ui.data.ApiResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ICustomerApiService {
    @GET("customers")
    suspend fun getAllCustomers(): ApiResponse<List<CustomerMinimal>>

    @GET("customers/{id}")
    suspend fun getCustomerById(@Path("id") id: String): ApiResponse<CustomerDetail>

    @POST("customers/changeCustomerStatus")
    suspend fun changeCustomerStatus(@Body request: ChangeCustomerStatusRequest):
            ApiResponse<ChangeCustomerStatusResponse>

}