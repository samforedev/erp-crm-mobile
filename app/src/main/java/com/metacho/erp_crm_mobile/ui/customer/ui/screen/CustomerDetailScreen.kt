package com.metacho.erp_crm_mobile.ui.customer.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.metacho.erp_crm_mobile.ui.common.ui.components.InfoRow
import com.metacho.erp_crm_mobile.ui.customer.domain.CustomerRepository
import com.metacho.erp_crm_mobile.ui.customer.ui.factory.CustomerDetailViewModelFactory
import com.metacho.erp_crm_mobile.ui.customer.ui.viewmodel.CustomerDetailViewModel

@Composable
fun CustomerDetailScreen(
    customerId: String,
    repository: CustomerRepository,
    onBack: () -> Unit
) {

    val viewModel: CustomerDetailViewModel = viewModel(
        factory = CustomerDetailViewModelFactory(repository, customerId)
    )

    val state = viewModel.state.collectAsState().value

    var showStatusDialog by remember { mutableStateOf(false) }
    var selectedStatus by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

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

        val customer = state.customer ?: return

        // ⭐ CARD PRINCIPAL
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {

                // ⭐ HEADER
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF354F7D))
                        .padding(16.dp)
                ) {
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "Detalle del Cliente",
                            color = Color.White,
                            style = MaterialTheme.typography.titleMedium
                        )

                        Button(
                            onClick = onBack,
                            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text("Volver", color = Color(0xFF354F7D))
                        }
                    }
                }

                // ⭐ CONTENIDO PRINCIPAL
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFF5F6FA))
                        .padding(16.dp)
                ) {

                    // Título
                    Text(
                        "Información Personal",
                        style = MaterialTheme.typography.titleSmall,
                        color = Color.Black
                    )

                    Spacer(Modifier.height(12.dp))

                    // Primera fila
                    InfoRow("Nombre completo", "${customer.people.firstName} ${customer.people.lastName}")
                    InfoRow("Tipo Documento", customer.people.documentType)
                    InfoRow("Número Documento", customer.people.documentNumber)

                    Spacer(Modifier.height(16.dp))

                    InfoRow("Fecha de nacimiento", customer.people.birthDate.toString())
                    InfoRow("Correo electrónico", customer.email)
                    InfoRow("Teléfono", customer.phoneNumber)

                    Divider(Modifier.padding(vertical = 16.dp))

                    // Título info cliente
                    Text(
                        "Información del Cliente",
                        style = MaterialTheme.typography.titleSmall,
                        color = Color.Black
                    )

                    Spacer(Modifier.height(12.dp))

                    InfoRow("Estado del cliente", customer.customerStatus)
                    InfoRow("Estado de registro", customer.status)
                    InfoRow("Agente asignado", customer.assignedAgentId.ifEmpty { "N/A" })
                    InfoRow("Creado el", customer.createdAt.toString())

                    Spacer(Modifier.height(24.dp))

                    // BOTÓN CAMBIAR ESTADO
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                        Button(
                            onClick = {
                                selectedStatus = customer.customerStatus
                                showStatusDialog = true
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF354F7D))
                        ) {
                            Text("Cambiar estado")
                        }
                    }
                }
            }
        }
    }

    // ⭐ MODAL PARA CAMBIAR ESTADO
    if (showStatusDialog) {
        AlertDialog(
            onDismissRequest = { showStatusDialog = false },
            title = { Text("Cambiar estado del cliente") },
            text = {
                Column {
                    val estados = listOf("ACTIVE", "POTENTIAL", "INACTIVE", "LOST", "NEW")

                    estados.forEach { status ->
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = selectedStatus == status,
                                onClick = { selectedStatus = status }
                            )
                            Text(status)
                        }
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        selectedStatus?.let { viewModel.updateStatus(it) }
                        showStatusDialog = false
                    },
                    enabled = selectedStatus != null
                ) {
                    Text("Guardar")
                }
            },
            dismissButton = {
                Button(onClick = { showStatusDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }
}