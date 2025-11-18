package com.metacho.erp_crm_mobile.ui.common.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun StatusBadge(status: String) {
    val color = when (status) {
        "ACTIVE" -> Color(0xFF4CAF50)
        "INACTIVE" -> Color(0xFFF44336)
        else -> Color(0xFF9E9E9E)
    }
    Box(
        Modifier
            .background(color, RoundedCornerShape(6.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(status, color = Color.White)
    }
}
