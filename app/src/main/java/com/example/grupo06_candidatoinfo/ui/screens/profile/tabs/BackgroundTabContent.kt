package com.example.grupo06_candidatoinfo.ui.screens.profile.tabs

import androidx.compose.foundation.BorderStroke // <-- IMPORTADO
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
//COLORES IMPORTADOS
import com.example.grupo06_candidatoinfo.ui.theme.ProfileMainPurple
import com.example.grupo06_candidatoinfo.ui.theme.ProfileLightPurpleBackground
import com.example.grupo06_candidatoinfo.ui.theme.ProfileStatusPink


// ==================== TAB ANTECEDENTES ====================
@Composable
fun BackgroundTabContent(backgroundHistory: BackgroundHistory?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (backgroundHistory == null || backgroundHistory.items.isEmpty()) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                shape = RoundedCornerShape(12.dp),
                // *** COLOR ACTUALIZADO ***
                colors = CardDefaults.cardColors(containerColor = ProfileLightPurpleBackground),
                // *** ELEVACIÓN ACTUALIZADA ***
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                border = BorderStroke(1.dp, Color(0xFFEBEAF1)) // Borde sutil
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
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 800.dp)
            ) {
                items(backgroundHistory.items) { item ->
                    BackgroundItemCard(item = item)
                }
            }
        }
    }
}

@Composable
fun BackgroundItemCard(item: BackgroundItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        // *** COLOR ACTUALIZADO ***
        colors = CardDefaults.cardColors(containerColor = ProfileLightPurpleBackground),
        // *** ELEVACIÓN ACTUALIZADA ***
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        border = BorderStroke(1.dp, Color(0xFFEBEAF1)) // Borde sutil
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.Top,
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
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                // Fila de Etiquetas (Categoría y Estado)
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    TagChip(
                        text = item.category,
                        contentColor = ProfileMainPurple,
                        containerColor = ProfileMainPurple.copy(alpha = 0.15f) // Color más sutil
                    )
                    TagChip(
                        text = item.status,
                        contentColor = ProfileStatusPink,
                        containerColor = ProfileStatusPink.copy(alpha = 0.1f)
                    )
                }

                // Título
                Text(
                    text = item.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black
                )

                // Entidad (Ej: Fiscalía)
                Text(
                    text = item.entity,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 13.sp,
                    color = ProfileMainPurple
                )

                // Descripción
                Text(
                    text = item.description,
                    fontSize = 13.sp,
                    color = Color(0xFF555555),
                    lineHeight = 18.sp
                )

                // Fecha
                Text(
                    text = item.date,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun TagChip(text: String, contentColor: Color, containerColor: Color) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = containerColor
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.SemiBold,
            fontSize = 11.sp,
            color = contentColor,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}