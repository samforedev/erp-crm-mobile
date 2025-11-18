package com.metacho.erp_crm_mobile.ui.customer.data.dto

data class ChangeCustomerStatusRequest(
    val customerId: String,
    val customerStatus: String
)

data class ChangeCustomerStatusResponse(
    val customerId: String,
    val currentCustomerStatus: String
)
