package com.metacho.erp_crm_mobile.ui.common.ui.components

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AppDrawer(
    onHomeClick: () -> Unit,
    onEmployeesClick: () -> Unit,
    onCustomersClick: () -> Unit,
    onReportsClick: () -> Unit
) {
    ModalDrawerSheet(
        drawerContainerColor = Color(0xFF354F7D),
        modifier = Modifier.fillMaxHeight()
    ) {
        Text(
            "ERP / CRM",
            modifier = Modifier
                .padding(24.dp),
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        DrawerItem("Inicio", Icons.Default.Home, onHomeClick)
        DrawerItem("Empleados", Icons.Default.Work, onEmployeesClick)
        DrawerItem("Clientes", Icons.Default.Groups, onCustomersClick)
        DrawerItem("Reportes", Icons.Default.BarChart, onReportsClick)
    }
}

@Composable
fun DrawerItem(
    label: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    NavigationDrawerItem(
        label = { Text(label, color = Color.White) },
        icon = { Icon(icon, contentDescription = null, tint = Color.White) },
        selected = false,
        onClick = onClick,
        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
    )
}
