package com.metacho.erp_crm_mobile.ui.common.data

import java.util.Date

data class People(
    val firstName: String,
    val lastName: String,
    val documentType: String,
    val documentNumber: String,
    val birthDate: Date
)