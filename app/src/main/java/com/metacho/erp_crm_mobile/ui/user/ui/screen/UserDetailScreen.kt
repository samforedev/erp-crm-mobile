package com.metacho.erp_crm_mobile.ui.user.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.metacho.erp_crm_mobile.ui.common.ui.components.BadgeRow
import com.metacho.erp_crm_mobile.ui.common.ui.components.InfoRow
import com.metacho.erp_crm_mobile.ui.user.domain.UserRepository
import com.metacho.erp_crm_mobile.ui.user.ui.factory.UserDetailsViewModelFactory
import com.metacho.erp_crm_mobile.ui.user.ui.viewmodel.UserDetailsViewModel

@Composable
fun UserDetailsScreen(
    userId: String,
    repository: UserRepository,
    onBack: () -> Unit
) {
    val viewModel: UserDetailsViewModel = viewModel(
        factory = UserDetailsViewModelFactory(repository, userId)
    )

    val state = viewModel.state.collectAsState().value

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

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

        val user = state.user ?: return

        // ⭐ CARD PRINCIPAL
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column {

                // HEADER
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF354F7D)) // igual a web
                        .padding(16.dp)
                ) {
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "Detalle del empleado",
                            color = Color.White,
                            style = MaterialTheme.typography.titleMedium
                        )

                        Button(
                            onClick = onBack,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White
                            ),
                            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text("Volver", color = Color(0xFF354F7D))
                        }
                    }
                }

                // CONTENIDO
                Column(
                    Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    // -------------------------
                    // INFORMACIÓN PERSONAL
                    // -------------------------
                    Text("Información Personal", style = MaterialTheme.typography.titleSmall)

                    InfoRow("Nombre completo", "${user.people.firstName} ${user.people.lastName}")
                    InfoRow("Tipo Documento", user.people.documentType)
                    InfoRow("Número Documento", user.people.documentNumber)

                    InfoRow("Fecha de nacimiento", user.people.birthDate.toString())
                    InfoRow("Correo electrónico", user.email)
                    InfoRow("Teléfono", user.phoneNumber)

                    Divider()

                    // -------------------------
                    // INFORMACIÓN TRABAJADOR
                    // -------------------------
                    Text("Información del Trabajador", style = MaterialTheme.typography.titleSmall)

                    BadgeRow("Usuario", user.username, Color.Gray)
                    BadgeRow("Cargo", user.jobTitle, Color(0xFF90CAF9))

                    // ROLES
                    Text("Roles:")
                    FlowRow {
                        if (user.roles.isEmpty()) {
                            Text("Sin roles", color = Color.Gray)
                        } else {
                            user.roles.forEach { role ->
                                RoleBadge(role)
                            }
                        }
                    }

                    // ESTADO
                    Row {
                        Text("Estado: ")
                        StatusBadge(user.status)
                    }

                    InfoRow("Fecha creación", user.createdAt.toString())
                }
            }
        }
    }
}
