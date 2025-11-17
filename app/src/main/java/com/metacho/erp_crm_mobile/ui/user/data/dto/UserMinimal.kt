package com.metacho.erp_crm_mobile.ui.user.data.dto

data class UserMinimal(
    val id: String,
    val firstName: String,
    val email: String,
    val roles: List<String>,
    val jobTitle: String,
    val status: String
)
