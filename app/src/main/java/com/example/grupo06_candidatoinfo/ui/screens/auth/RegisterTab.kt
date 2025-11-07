package com.example.grupo06_candidatoinfo.ui.screens.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun RegisterTab(viewModel: AuthViewModel, navController: NavHostController) {
    val state by viewModel.registroState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 30.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Registro de Usuario",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // --- DNI ---
        OutlinedTextField(
            value = state.dni,
            onValueChange = { viewModel.onDniChange(it) },
            label = { Text("DNI") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        Button(
            onClick = { viewModel.buscarDni() },
            modifier = Modifier.fillMaxWidth(),
            enabled = !state.isLoading
        ) {
            if (state.isLoading)
                CircularProgressIndicator(
                    modifier = Modifier.size(18.dp),
                    strokeWidth = 2.dp
                )
            else
                Text("Buscar DNI")
        }

        if (state.error != null) {
            Text(
                text = state.error ?: "",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Spacer(Modifier.height(16.dp))

        // --- Datos obtenidos ---
        OutlinedTextField(
            value = state.nombres,
            onValueChange = {},
            label = { Text("Nombres") },
            readOnly = true,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = state.apellidoPaterno,
            onValueChange = {},
            label = { Text("Apellido Paterno") },
            readOnly = true,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = state.apellidoMaterno,
            onValueChange = {},
            label = { Text("Apellido Materno") },
            readOnly = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        // --- Email ---
        OutlinedTextField(
            value = state.email,
            onValueChange = { viewModel.onEmailChange(it) },
            label = { Text("Correo electrónico") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = {
                // TODO: Continuar con registro
                navController.navigate("home") // ejemplo de navegación a Home
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Continuar registro")
        }
    }
}
