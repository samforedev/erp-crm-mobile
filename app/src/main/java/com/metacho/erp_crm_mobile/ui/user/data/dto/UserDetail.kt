package com.metacho.erp_crm_mobile.ui.user.data.dto

import com.metacho.erp_crm_mobile.ui.common.data.People
import java.util.Date

data class UserDetail(
    val id: String,
    val people: People,
    val phoneNumber: String,
    val jobTitle: String,
    val username: String,
    val email: String,
    val roles: List<String>,
    val status: String,
    val createdAt: Date,
    val deleted: Boolean
)
