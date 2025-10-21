package com.example.grupo06_candidatoinfo.ui.screens.profile.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BusinessCenter
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.grupo06_candidatoinfo.model.CareerHistory
import com.example.grupo06_candidatoinfo.model.CareerItem
// --- IMPORTS DE COLOR CORREGIDOS ---
import com.example.grupo06_candidatoinfo.ui.theme.ProfileLighterPurpleCard
import com.example.grupo06_candidatoinfo.ui.theme.ProfileMainPurple
import com.example.grupo06_candidatoinfo.ui.theme.TimelineColor

// ==================== TAB TRAYECTORIA ====================
@Composable
fun CareerTabContent(careerHistory: CareerHistory) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Título de la sección
        Text(
            text = "HISTORIAL LABORAL",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF888888),
            modifier = Modifier.padding(start = 4.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(0.dp),
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 800.dp) // Límite de altura opcional
        ) {
            items(careerHistory.items.size) { index ->
                CareerItemCard(
                    careerItem = careerHistory.items[index],
                    isLastItem = index == careerHistory.items.size - 1
                )
            }
        }
    }
}

@Composable
fun CareerItemCard(careerItem: CareerItem, isLastItem: Boolean) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Columna de Timeline (Círculo e Separador)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(40.dp) // Ancho fijo para la columna de timeline
        ) {
            // Círculo de la línea de tiempo
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    // --- COLOR CORREGIDO: Main Purple (Oscuro) ---
                    .background(ProfileMainPurple),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.BusinessCenter,
                    contentDescription = "Trabajo",
                    tint = Color.White,
                    modifier = Modifier.size(14.dp)
                )
            }
            // Separador (Línea)
            if (!isLastItem) {
                Divider(
                    // --- COLOR CORREGIDO: TimelineColor (Gris/Morado claro) ---
                    color = TimelineColor,
                    modifier = Modifier
                        .width(2.dp)
                        .height(IntrinsicSize.Max)
                        .weight(1f)
                        .defaultMinSize(minHeight = 40.dp) // Altura mínima de la línea
                )
            }
        }

        // Tarjeta de Contenido
        Card(
            modifier = Modifier
                .weight(1f)
                .offset(y = (-8).dp) // Pequeño ajuste para centrar la tarjeta con el círculo
                .padding(bottom = 20.dp), // Espacio inferior para separación con el siguiente elemento
            shape = RoundedCornerShape(12.dp),
            // --- COLOR CORREGIDO: Lighter Purple Card (Fondo claro) ---
            colors = CardDefaults.cardColors(containerColor = ProfileLighterPurpleCard),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = careerItem.position,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    // --- COLOR CORREGIDO: Main Purple (Para el tag de periodo) ---
                    color = ProfileMainPurple.copy(alpha = 0.9f)
                ) {
                    Text(
                        text = careerItem.period,
                        color = Color.White,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                    )
                }
                Text(
                    text = careerItem.description,
                    fontSize = 13.sp,
                    color = Color(0xFF555555),
                    lineHeight = 17.sp
                )
            }
        }
    }
}
