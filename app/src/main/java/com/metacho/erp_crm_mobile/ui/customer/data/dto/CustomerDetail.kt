package com.metacho.erp_crm_mobile.ui.customer.data.dto

import com.metacho.erp_crm_mobile.ui.common.data.People
import java.util.Date

data class CustomerDetail(
    val id: String,
    val people: People,
    val email: String,
    val phoneNumber: String,
    val assignedAgentId: String,
    val customerStatus: String,
    val status: String,
    val createdAt: Date,
    val isDeleted: Boolean
)
