package com.metacho.erp_crm_mobile.ui.user.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.metacho.erp_crm_mobile.ui.login.domain.LoginRepository
import com.metacho.erp_crm_mobile.ui.user.data.dto.UserMinimal
import com.metacho.erp_crm_mobile.ui.user.domain.UserRepository
import com.metacho.erp_crm_mobile.ui.user.ui.CreateUserDialog
import com.metacho.erp_crm_mobile.ui.user.ui.viewmodel.UserViewModel
import com.metacho.erp_crm_mobile.ui.user.ui.factory.UserViewModelFactory

@Composable
fun UserScreen(
    repository: UserRepository,
    loginRepo: LoginRepository,
    onUserDetails: (String) -> Unit
) {
    val viewModel: UserViewModel = viewModel(factory = UserViewModelFactory(repository, loginRepo))
    val state = viewModel.state.collectAsState().value

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        // HEADER
        Text(
            "Empleados",
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
        if (state.users.isEmpty()) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("No hay Usuarios registrados", color = Color.Gray)
            }
            return
        }

        Button(onClick = { viewModel.openCreateDialog() }) {
            Icon(Icons.Default.PersonAdd, contentDescription = null)
            Text("Nuevo empleado")
        }

        // 👉 AHORA sí pasas el viewModel correctamente
        if (viewModel.showCreateDialog) {
            CreateUserDialog(
                viewModel = viewModel,
                onDismiss = { viewModel.closeDialog() }
            )
        }

        Spacer(Modifier.height(16.dp))
        // USERS LIST
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(state.users) { user ->
                UserItem(
                    user = user,
                    onDetails = { onUserDetails(user.id) }
                )
            }
        }
    }
}

@Composable
fun UserItem(
    user: UserMinimal,
    onDetails: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF9FAFB)),
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column(Modifier.padding(16.dp)) {

            Text(user.firstName, style = MaterialTheme.typography.titleMedium)

            Spacer(Modifier.height(4.dp))

            Text(user.email, color = Color.Gray, style = MaterialTheme.typography.bodyMedium)

            Spacer(Modifier.height(8.dp))

            // Roles
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                if (user.roles.isEmpty()) {
                    Text("Sin roles", color = Color.Gray)
                } else {
                    user.roles.forEach {
                        RoleBadge(it)
                    }
                }
            }

            Spacer(Modifier.height(8.dp))

            // Estado
            StatusBadge(user.status)

            Spacer(Modifier.height(12.dp))

            // ACTIONS
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = onDetails) {
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
fun RoleBadge(role: String) {
    val color = when (role) {
        "ADMIN" -> Color(0xFF4CAF50)
        "USER" -> Color(0xFF1E88E5)
        "SUPPORT" -> Color(0xFFFFC107)
        else -> Color.Gray
    }

    Box(
        Modifier
            .background(color.copy(alpha = 0.2f), MaterialTheme.shapes.small)
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(role, color = color)
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