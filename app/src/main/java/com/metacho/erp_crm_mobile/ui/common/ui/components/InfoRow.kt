package com.metacho.erp_crm_mobile.ui.common.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

@Composable
fun InfoRow(label: String, value: String) {
    Column {
        Text(label, fontWeight = FontWeight.SemiBold, color = Color.Gray)
        Text(value)
    }
}
