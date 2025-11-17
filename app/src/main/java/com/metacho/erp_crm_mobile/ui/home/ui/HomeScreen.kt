package com.metacho.erp_crm_mobile.ui.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.metacho.erp_crm_mobile.ui.common.ui.components.DashboardCard

@Composable
fun HomeScreen(
    onEmployeesClick: () -> Unit,
    onCustomersClick: () -> Unit,
    onReportsClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F6FA))
            .padding(16.dp)
    ) {
        Text(
            text = "Panel Principal",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                DashboardCard(
                    title = "Empleados",
                    description = "Gestión del personal",
                    icon = Icons.Default.Work,
                    onClick = onEmployeesClick
                )
            }

            item {
                DashboardCard(
                    title = "Clientes",
                    description = "Administración de clientes",
                    icon = Icons.Default.Groups,
                    onClick = onCustomersClick
                )
            }

            item {
                DashboardCard(
                    title = "Reportes",
                    description = "Ver métricas y estadísticas",
                    icon = Icons.Default.BarChart,
                    onClick = onReportsClick
                )
            }
        }
    }
}