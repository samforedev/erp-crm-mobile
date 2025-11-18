package com.metacho.erp_crm_mobile.ui.common.ui.components

import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun BadgeRow(label: String, value: String, color: Color) {
    Column {
        Text(label, fontWeight = FontWeight.SemiBold, color = Color.Gray)

        Box(
            modifier = Modifier
                .padding(top = 4.dp)
                .background(color, RoundedCornerShape(6.dp))
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Text(value, color = Color.White)
        }
    }
}
