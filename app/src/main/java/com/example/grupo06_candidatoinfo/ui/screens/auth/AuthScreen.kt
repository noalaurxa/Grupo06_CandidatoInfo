package com.example.grupo06_candidatoinfo.ui.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
// Importamos Surface y Box para el selector de pestañas
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp // Importamos sp para el tamaño de fuente
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.grupo06_candidatoinfo.R // <-- ESTA ES LA CORRECTA

@OptIn(ExperimentalMaterial3Api::class) // Necesario para Surface onClick
@Composable
fun AuthScreen(
    navController: NavHostController,
    viewModel: AuthViewModel = viewModel()
) {
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("Iniciar Sesión", "Registrarse")

    // --- Paleta de colores actualizada ---
    val VotoPurple = Color(0xFF4F2D8F)
    val VotoDarkText = Color(0xFF2C2C54)
    val VotoGrayText = Color(0xFF8E8E93)
    val VotoTabBg = Color(0xFFF2F2F7) // Color de fondo del toggle
    // ---

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
            color = VotoDarkText // Actualizado
        )

        Spacer(Modifier.height(8.dp))

        // Subtítulo
        Text(
            text = "Crea una cuenta o inicia sesión para explorar Voto Informado",
            style = MaterialTheme.typography.bodyMedium,
            color = VotoGrayText, // Actualizado
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(24.dp))

        // --- INICIO DE ACTUALIZACIÓN (Selector de Pestañas) ---
        // Reemplazamos los TextButtons por el diseño de Surface segmentado
        Surface(
            shape = RoundedCornerShape(8.dp),
            color = VotoTabBg, // Fondo gris claro/lila
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                tabs.forEachIndexed { index, title ->
                    val isSelected = selectedTab == index
                    Surface(
                        onClick = { selectedTab = index },
                        shape = RoundedCornerShape(6.dp),
                        color = if (isSelected) Color.White else Color.Transparent,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        shadowElevation = if (isSelected) 2.dp else 0.dp
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text(
                                text = title,
                                fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                                color = if (isSelected) VotoPurple else VotoGrayText,
                                style = MaterialTheme.typography.bodyMedium,
                                fontSize = 15.sp
                            )
                        }
                    }
                }
            }
        }
        // --- FIN DE ACTUALIZACIÓN ---

        Spacer(Modifier.height(24.dp))

        // Contenido dinámico
        when (selectedTab) {
            0 -> LoginTab(navController)
            1 -> RegisterTab(viewModel, navController)
        }
    }
}