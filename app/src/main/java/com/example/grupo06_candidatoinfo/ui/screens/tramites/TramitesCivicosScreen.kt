package com.example.grupo06_candidatoinfo.ui.screens.tramites

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material3.* // Importación clave de Material 3
import androidx.compose.ui.draw.clip
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

// Define las URLs de consulta
private const val ONPE_CONSULTA_URL = "https://consultaelectoral.onpe.gob.pe/inicio"
private const val RENIEC_DOMICILIO_URL = "https://www.reniec.gob.pe/portal/serviciosenlinea.htm"
private const val JNE_MULTAS_URL = "https://multas.jne.gob.pe/login"

// Usamos @OptIn para asegurar que CenterAlignedTopAppBar se pueda usar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TramitesCivicosScreen(navController: NavController) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            // 🛑 SOLUCIÓN: Usamos CenterAlignedTopAppBar (el componente Top Bar estándar de M3)
            CenterAlignedTopAppBar(
                title = { Text("Consulta Cívica Electoral", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver", tint = Color.White)
                    }
                },
                // 🛑 SOLUCIÓN: Usamos TopAppBarDefaults.centerAlignedTopAppBarColors
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color(0xFF3C3472))
            )
        },
        // M3 usa containerColor para el Scaffold
        containerColor = Color(0xFFF7F7F7)
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                "Portal de Consultas y Trámites",
                style = MaterialTheme.typography.headlineSmall, // Tipografía M3
                fontWeight = FontWeight.Bold
            )
            Text(
                "Presiona el enlace que desees consultar. Serás redirigido a la página oficial de la entidad.",
                style = MaterialTheme.typography.bodyMedium, // Tipografía M3
                color = Color.Gray
            )

            ConsultaCard(
                title = "Local de Votación y Miembro de Mesa",
                description = "Consulta tu centro de votación, número de mesa y si fuiste elegido miembro.",
                icon = { Icon(Icons.Default.LocationOn, contentDescription = null, tint = Color.White) },
                onClick = { openWebUrl(context, ONPE_CONSULTA_URL) }
            )

            ConsultaCard(
                title = "Consulta de Multas Electorales",
                description = "Verifica si tienes multas pendientes y consulta el proceso de justificación.",
                icon = { Icon(Icons.Default.MenuBook, contentDescription = null, tint = Color.White) },
                onClick = { openWebUrl(context, JNE_MULTAS_URL) }
            )

            ConsultaCard(
                title = "Actualizar Domicilio en DNI",
                description = "Accede a los servicios en línea de RENIEC para cambiar tu dirección.",
                icon = { Icon(Icons.Default.ExitToApp, contentDescription = null, tint = Color.White) },
                onClick = { openWebUrl(context, RENIEC_DOMICILIO_URL) }
            )
        }
    }
}

@Composable
fun ConsultaCard(
    title: String,
    description: String,
    icon: @Composable () -> Unit,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        // M3 usa CardDefaults.cardColors
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFF3C3472)),
                contentAlignment = Alignment.Center
            ) {
                icon()
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    title,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = Color(0xFF3C3472)
                )
                Text(
                    description,
                    color = Color.Gray,
                    fontSize = 12.sp
                )
            }
        }
    }
}

fun openWebUrl(context: android.content.Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse(url)
    context.startActivity(intent)
}