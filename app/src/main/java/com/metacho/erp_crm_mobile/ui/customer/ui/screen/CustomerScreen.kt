package com.metacho.erp_crm_mobile.ui.customer.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.metacho.erp_crm_mobile.ui.customer.data.dto.CustomerMinimal
import com.metacho.erp_crm_mobile.ui.customer.domain.CustomerRepository
import com.metacho.erp_crm_mobile.ui.customer.ui.factory.CustomerViewModelFactory
import com.metacho.erp_crm_mobile.ui.customer.ui.viewmodel.CustomerViewModel

@Composable
fun CustomerScreen(
    repository: CustomerRepository
) {
    val viewModel: CustomerViewModel = viewModel(factory = CustomerViewModelFactory(repository))
    val state = viewModel.state.collectAsState().value

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        // HEADER
        Text(
            "Clientes",
            style = MaterialTheme.typography.headlineMedium,
            color = Color(0xFF354F7D)
        )

        Spacer(Modifier.height(16.dp))

        // LOADING
        if (state.isLoading) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
            return
        }

        // ERROR
        state.error?.let {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Error: $it", color = Color.Red)
            }
            return
        }

        // EMPTY LIST
        if (state.customers.isEmpty()) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("No hay Clientes registrados", color = Color.Gray)
            }
            return
        }

        /*Button(onClick = { viewModel.openCreateDialog() }) {
            Icon(Icons.Default.PersonAdd, contentDescription = null)
            Text("Nuevo Cliente")
        }

        if (viewModel.showCreateDialog) {
            CreateUserDialog(
                viewModel = viewModel,
                onDismiss = { viewModel.closeDialog() }
            )
        }*/

        Spacer(Modifier.height(16.dp))
        // USERS LIST
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(state.customers) { customer ->
                CustomerItem(customer = customer)
            }
        }
    }
}

@Composable
fun CustomerItem(
    customer: CustomerMinimal
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF9FAFB)),
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column(Modifier.padding(16.dp)) {

            Text(customer.id, style = MaterialTheme.typography.titleMedium)

            Spacer(Modifier.height(4.dp))

            Text(customer.email, color = Color.Gray, style = MaterialTheme.typography.bodyMedium)

            Spacer(Modifier.height(8.dp))

            // Estado
            StatusBadge(customer.customerStatus)

            Spacer(Modifier.height(12.dp))

            // ACTIONS
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = { /* detalles */ }) {
                    Icon(Icons.Default.Search, contentDescription = "Ver")
                }
                IconButton(onClick = { /* editar */ }) {
                    Icon(Icons.Default.Edit, contentDescription = "Editar")
                }
                IconButton(onClick = { /* borrar */ }) {
                    Icon(Icons.Default.Delete, contentDescription = "Eliminar", tint = Color.Red)
                }
            }
        }
    }
}

@Composable
fun StatusBadge(status: String) {
    val color = if (status == "ACTIVE") Color(0xFF4CAF50) else Color(0xFFE53935)

    Box(
        Modifier
            .background(color.copy(alpha = 0.2f), MaterialTheme.shapes.small)
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(status, color = color)
    }
}