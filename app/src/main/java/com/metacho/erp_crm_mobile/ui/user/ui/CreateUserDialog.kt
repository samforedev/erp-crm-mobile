package com.metacho.erp_crm_mobile.ui.user.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.metacho.erp_crm_mobile.ui.common.ui.components.DropdownMenuBox

@Composable
fun CreateUserDialog(viewModel: UserViewModel, onDismiss: () -> Unit) {

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(onClick = { viewModel.registerUser() }) {
                Text("Registrar")
            }
        },
        dismissButton = {
            OutlinedButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        },
        title = { Text("Registrar nuevo empleado") },
        text = {
            // CONTENEDOR QUE LIMITA EL ALTO DEL DIALOG
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 600.dp)   // ⭐ Asegura un espacio vertical amplio
            ) {
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(end = 8.dp), // evita que el scroll tape los campos
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    OutlinedTextField(
                        value = viewModel.firstName,
                        onValueChange = { viewModel.firstName = it },
                        label = { Text("Nombre") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = viewModel.lastName,
                        onValueChange = { viewModel.lastName = it },
                        label = { Text("Apellido") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    DropdownMenuBox(
                        label = "Tipo de documento",
                        options = listOf("CC", "CE", "NIT"),
                        selected = viewModel.documentType,
                        onSelected = { viewModel.documentType = it }
                    )

                    OutlinedTextField(
                        value = viewModel.documentNumber,
                        onValueChange = { viewModel.documentNumber = it },
                        label = { Text("Número") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = viewModel.birthDate,
                        onValueChange = { viewModel.birthDate = it },
                        label = { Text("Fecha nacimiento (YYYY-MM-DD)") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = viewModel.phoneNumber,
                        onValueChange = { viewModel.phoneNumber = it },
                        label = { Text("Teléfono") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = viewModel.email,
                        onValueChange = { viewModel.email = it },
                        label = { Text("Correo electrónico") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = viewModel.username,
                        onValueChange = { viewModel.username = it },
                        label = { Text("Usuario") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = viewModel.password,
                        onValueChange = { viewModel.password = it },
                        label = { Text("Contraseña") },
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = viewModel.jobTitle,
                        onValueChange = { viewModel.jobTitle = it },
                        label = { Text("Cargo") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    DropdownMenuBox(
                        label = "Rol inicial",
                        options = listOf("USER", "ADMIN", "SUPPORT"),
                        selected = viewModel.initialRole,
                        onSelected = { viewModel.initialRole = it }
                    )

                    if (viewModel.errorMessage != null) {
                        Text(viewModel.errorMessage!!, color = Color.Red)
                    }
                }
            }
        }
    )
}
