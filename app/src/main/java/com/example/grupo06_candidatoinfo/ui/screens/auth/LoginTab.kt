package com.example.grupo06_candidatoinfo.ui.screens.auth

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
// Importamos FontWeight para el texto de "Olvidaste..."
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.grupo06_candidatoinfo.navigation.Screen

@Composable
fun LoginTab(navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var rememberMe by remember { mutableStateOf(false) }

    // --- Paleta de colores actualizada ---
    val VotoPurple = Color(0xFF4F2D8F)
    val VotoGrayText = Color(0xFF8E8E93)
    val VotoDarkText = Color(0xFF2C2C54)
    val VotoBorderGray = Color(0xFFE0E0E0)
    // ---

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Top
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo Electrónico", color = VotoGrayText) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true,
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = VotoPurple, // Actualizado
                unfocusedBorderColor = VotoBorderGray,
                focusedLabelColor = VotoPurple, // Actualizado
                cursorColor = VotoPurple // Actualizado
            )
        )

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña", color = VotoGrayText) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val image = if (passwordVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility
                val description = if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña"
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, description, tint = VotoGrayText)
                }
            },
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = VotoPurple, // Actualizado
                unfocusedBorderColor = VotoBorderGray,
                focusedLabelColor = VotoPurple, // Actualizado
                cursorColor = VotoPurple // Actualizado
            )
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = rememberMe,
                    onCheckedChange = { rememberMe = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = VotoPurple, // Actualizado
                        uncheckedColor = VotoGrayText
                    )
                )
                Text(
                    text = "Recordar",
                    style = MaterialTheme.typography.bodyMedium,
                    color = VotoDarkText
                )
            }
            TextButton(onClick = { /* TODO */ }) {
                Text(
                    "Olvidaste tu contraseña?",
                    style = MaterialTheme.typography.bodyMedium,
                    // --- Actualizado según el diseño (negrita y oscuro) ---
                    fontWeight = FontWeight.Bold,
                    color = VotoDarkText
                )
            }
        }

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = {
                // TODO: Implementar lógica de login
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Auth.route) { inclusive = true }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = VotoPurple // Actualizado
            )
        ) {
            Text(
                "INICIAR SESIÓN",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )
        }
    }
}