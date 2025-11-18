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
fun RoleBadge(role: String) {
    val color = when (role) {
        "ADMIN" -> Color(0xFF4CAF50)
        "USER" -> Color(0xFF2196F3)
        else -> Color(0xFF9E9E9E)
    }

    Box(
        Modifier
            .padding(end = 6.dp)
            .background(color, RoundedCornerShape(6.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(role, color = Color.White)
    }
}
