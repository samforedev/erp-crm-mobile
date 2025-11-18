package com.metacho.erp_crm_mobile.ui.customer.domain

import com.metacho.erp_crm_mobile.ui.customer.data.ICustomerApiService
import com.metacho.erp_crm_mobile.ui.customer.data.dto.ChangeCustomerStatusRequest
import com.metacho.erp_crm_mobile.ui.customer.data.dto.ChangeCustomerStatusResponse
import com.metacho.erp_crm_mobile.ui.customer.data.dto.CustomerDetail
import com.metacho.erp_crm_mobile.ui.customer.data.dto.CustomerMinimal
import com.metacho.erp_crm_mobile.ui.data.ApiResponse

class CustomerRepository(private val api: ICustomerApiService) {
    suspend fun getAllCustomers(): ApiResponse<List<CustomerMinimal>> {
        return api.getAllCustomers()
    }

    suspend fun getCustomerById(id: String): ApiResponse<CustomerDetail> {
        return api.getCustomerById(id)
    }

    suspend fun changeCustomerStatus(request: ChangeCustomerStatusRequest):
            ApiResponse<ChangeCustomerStatusResponse> {
        return api.changeCustomerStatus(request)
    }

}