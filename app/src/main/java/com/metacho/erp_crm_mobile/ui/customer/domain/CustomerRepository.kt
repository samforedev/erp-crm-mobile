package com.metacho.erp_crm_mobile.ui.customer.domain

import com.metacho.erp_crm_mobile.ui.customer.data.ICustomerApiService
import com.metacho.erp_crm_mobile.ui.customer.data.dto.CustomerMinimal
import com.metacho.erp_crm_mobile.ui.data.ApiResponse

class CustomerRepository(private val api: ICustomerApiService) {
    suspend fun getAllCustomers(): ApiResponse<List<CustomerMinimal>> {
        return api.getAllCustomers()
    }
}