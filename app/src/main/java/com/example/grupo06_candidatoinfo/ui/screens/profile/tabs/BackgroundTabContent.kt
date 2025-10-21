package com.example.grupo06_candidatoinfo.ui.screens.profile.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.grupo06_candidatoinfo.model.BackgroundHistory
import com.example.grupo06_candidatoinfo.model.BackgroundItem
// Asegúrate de que los colores ProfileMainPurple y ProfileLightPurpleBackground estén en ui.theme
import com.example.grupo06_candidatoinfo.ui.theme.ProfileMainPurple
import com.example.grupo06_candidatoinfo.ui.theme.ProfileLightPurpleBackground

// ==================== TAB ANTECEDENTES ====================
@Composable
fun BackgroundTabContent(backgroundHistory: BackgroundHistory) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (backgroundHistory.items.isEmpty()) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Sin antecedentes registrados en esta fuente.",
                        color = Color.Gray,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        } else {
            backgroundHistory.items.forEach { item ->
                BackgroundItemCard(item = item)
            }
        }
    }
}

@Composable
fun BackgroundItemCard(item: BackgroundItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Icono
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(ProfileMainPurple),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "Antecedente",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }

            // Contenido
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.type,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = ProfileMainPurple
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = item.description,
                    fontSize = 13.sp,
                    color = Color(0xFF555555)
                )
            }

            // Estado y Fecha
            Column(horizontalAlignment = Alignment.End) {
                val statusColor = when (item.status) {
                    "Vigente", "Inhabilitación" -> Color.Red
                    "Archivado", "Concluido" -> Color(0xFF4CAF50) // Green
                    else -> Color.Gray
                }

                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = statusColor.copy(alpha = 0.1f)
                ) {
                    Text(
                        text = item.status,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 11.sp,
                        color = statusColor,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = item.date,
                    fontSize = 11.sp,
                    color = Color.Gray
                )
            }
        }
    }
}