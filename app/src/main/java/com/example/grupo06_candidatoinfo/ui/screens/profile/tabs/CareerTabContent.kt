package com.example.grupo06_candidatoinfo.ui.screens.profile.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
// --- IMPORTS DE COLOR ---
import com.example.grupo06_candidatoinfo.ui.theme.ProfileLighterPurpleCard
import com.example.grupo06_candidatoinfo.ui.theme.ProfileMainPurple

// ==================== TAB TRAYECTORIA (ESTÉTICA MEJORADA) ====================
@Composable
fun CareerTabContent(careerHistory: CareerHistory) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 800.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(careerHistory.items.size) { index ->
            CareerItemCard(
                careerItem = careerHistory.items[index],
                esUltimoItem = index == careerHistory.items.size - 1
            )
        }
    }
}

// ==================== ITEM DE TRAYECTORIA====================
@Composable
fun CareerItemCard(careerItem: CareerItem, esUltimoItem: Boolean) {
    Row(
        // Aplicamos el padding horizontal aquí para que la línea quede visible en el borde
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.Top
    ) {
        // Columna de Timeline (Icono y Línea)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            // Ancho ajustado para centrar bien la línea
            modifier = Modifier.width(28.dp)
        ) {
            // Contenedor del Icono (Cuadrado Púrpura Redondeado)
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(ProfileMainPurple),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.BusinessCenter,
                    contentDescription = "Trabajo",
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
            }

            if (!esUltimoItem) {
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .weight(1f)
                        .background(ProfileMainPurple.copy(alpha = 0.6f))
                )
            } else {
                Spacer(modifier = Modifier.height(4.dp))
            }
        }

        // Tarjeta de Contenido
        Card(
            modifier = Modifier
                .weight(1f)
                .padding(top = 0.dp, bottom = 12.dp), // Pequeño padding inferior
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = ProfileLighterPurpleCard),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                // Posición
                Text(
                    text = careerItem.position,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                // Tag de Periodo
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = ProfileMainPurple
                ) {
                    Text(
                        text = careerItem.period,
                        color = Color.White,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                // Descripción
                Text(
                    text = careerItem.description,
                    fontSize = 14.sp,
                    color = Color(0xFF4A4A4A),
                    lineHeight = 18.sp
                )
            }
        }
    }
}