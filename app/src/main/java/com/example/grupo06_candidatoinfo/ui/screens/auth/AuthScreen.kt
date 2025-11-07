package com.example.grupo06_candidatoinfo.ui.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.grupo06_candidatoinfo.R // <-- ESTA ES LA CORRECTA

@Composable
fun AuthScreen(
    navController: NavHostController,
    viewModel: AuthViewModel = viewModel()
) {
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("Iniciar Sesión", "Registrarse")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(60.dp))

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Voto Informado Logo",
            modifier = Modifier
                .height(200.dp)
        )

        Spacer(Modifier.height(20.dp))

        // Título dinámico
        Text(
            text = when (selectedTab) {
                0 -> "Bienvenid@ !"
                else -> "Crear cuenta"
            },
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            color = Color(0xFF2C2C54)
        )

        Spacer(Modifier.height(8.dp))

        // Subtítulo
        Text(
            text = "Crea una cuenta o inicia sesión para explorar Voto Informado",
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFF8E8E93),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(24.dp))

        // Tabs personalizados (sin TabRow de Material)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            tabs.forEachIndexed { index, title ->
                TextButton(
                    onClick = { selectedTab = index },
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = if (selectedTab == index)
                            Color(0xFF3F51B5)
                        else
                            Color(0xFF8E8E93)
                    )
                ) {
                    Text(
                        text = title,
                        fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }

        Spacer(Modifier.height(24.dp))

        // Contenido dinámico
        when (selectedTab) {
            0 -> LoginTab(navController)
            1 -> RegisterTab(viewModel, navController)
        }
    }
}