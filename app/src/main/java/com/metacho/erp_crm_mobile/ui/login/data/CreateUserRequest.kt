package com.metacho.erp_crm_mobile.ui.login.data

data class CreateUserRequest(
    val firstName: String,
    val lastName: String,
    val documentType: String,
    val documentNumber: String,
    val birthDate: String,
    val phoneNumber: String?,
    val email: String,
    val username: String,
    val password: String,
    val jobTitle: String?,
    val initialRoleName: String
)