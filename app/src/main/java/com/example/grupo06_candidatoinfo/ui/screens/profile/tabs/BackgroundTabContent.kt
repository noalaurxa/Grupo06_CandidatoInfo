package com.example.grupo06_candidatoinfo.ui.screens.profile.tabs

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.grupo06_candidatoinfo.model.BackgroundRecord // Nuevo modelo
import com.example.grupo06_candidatoinfo.model.BackgroundReport // Nuevo modelo
import com.example.grupo06_candidatoinfo.ui.theme.ProfileLighterPurpleCard // Corregido: Reemplaza lightPurpleCard
import com.example.grupo06_candidatoinfo.ui.theme.ProfileMainPurple // Corregido: Reemplaza mainPurple
import com.example.grupo06_candidatoinfo.ui.theme.RiskHighColor // Corregido: Reemplaza riskHighColor
import com.example.grupo06_candidatoinfo.ui.theme.TagArchivadoColor // Corregido: Reemplaza tagArchivadoColor
import com.example.grupo06_candidatoinfo.ui.theme.TagInvestigacionColor // Corregido: Reemplaza tagInvestigacionColor
import com.example.grupo06_candidatoinfo.ui.theme.TagSentenciadoColor // Corregido: Reemplaza tagSentenciadoColor


@Composable
fun BackgroundTabContent(backgroundReport: BackgroundReport) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp), // Añadimos padding vertical para el contenido
        verticalArrangement = Arrangement.spacedBy(16.dp) // Espacio entre tarjetas
    ) {
        // Título de la sección
        Text(
            text = "ANTECEDENTES",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF888888),
            modifier = Modifier.padding(start = 4.dp)
        )

        // Lista de Antecedentes
        if (backgroundReport.records.isEmpty()) {
            // Mantenemos el diseño de la tarjeta de "Sin antecedentes" de la versión HEAD
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                shape = RoundedCornerShape(12.dp),
                // --- COLOR CORREGIDO ---
                colors = CardDefaults.cardColors(containerColor = ProfileLighterPurpleCard),
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
            // Usamos LazyColumn para renderizar la lista eficientemente (tomado de HEAD, mejor que ForEach)
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 800.dp) // Límite de altura opcional
            ) {
                items(backgroundReport.records) { record ->
                    BackgroundRecordCard(record = record)
                }
            }
        }
    }
}

@Composable
fun BackgroundRecordCard(record: BackgroundRecord) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White), // Fondo blanco
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp) // Espacio entre elementos internos
        ) {
            // --- Fila Superior: Icono y Título ---
            Row(
                verticalAlignment = Alignment.Top, // Icono alineado arriba
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Caja del icono
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(8.dp))
                        // --- COLOR CORREGIDO ---
                        .background(ProfileMainPurple.copy(alpha = 0.1f)), // Fondo morado muy claro
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.BusinessCenter, // Icono de maletín
                        contentDescription = "Antecedente",
                        // --- COLOR CORREGIDO ---
                        tint = ProfileMainPurple, // Icono morado oscuro
                        modifier = Modifier.size(28.dp)
                    )
                }
                // Título
                Text(
                    text = record.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    lineHeight = 20.sp // Ajuste de altura de línea
                )
            }

            // --- Descripción ---
            Text(
                text = record.description,
                fontSize = 13.sp,
                color = Color(0xFF666666), // Gris para descripción
                lineHeight = 18.sp,
                // Indentación para alinear con el título. Ajustado de 60.dp a 58.dp por 16.dp de padding general - (48.dp+12.dp)/2
                modifier = Modifier.padding(start = 60.dp)
            )

            // --- Tags de Estado ---
            Row(
                modifier = Modifier.padding(start = 60.dp), // Indentación
                horizontalArrangement = Arrangement.spacedBy(8.dp) // Espacio entre tags
            ) {
                record.tags.forEach { tag ->
                    // --- COLORES CORREGIDOS ---
                    val (backgroundColor, textColor) = when (tag.lowercase()) { // Usar lowercase para comparar
                        "archivado" -> TagArchivadoColor to Color(0xFFC0392B) // Texto rojo oscuro
                        "investigación" -> TagInvestigacionColor to Color(0xFFD35400) // Texto naranja oscuro
                        "juicio oral" -> TagInvestigacionColor to Color(0xFFD35400) // Mismo color
                        "sentenciado" -> TagSentenciadoColor to RiskHighColor // Texto rojo
                        else -> Color.LightGray to Color.Black // Color por defecto
                    }
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = backgroundColor // Fondo del tag
                    ) {
                        Text(
                            text = tag,
                            color = textColor, // Color del texto del tag
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                        )
                    }
                }
            }

            // --- Fila Inferior: Tarjeta morada con Entidad, Fecha y Ver más ---
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                // --- COLOR CORREGIDO ---
                colors = CardDefaults.cardColors(containerColor = ProfileLighterPurpleCard.copy(alpha = 0.7f))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Columna para Entidad
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Entidad",
                            fontSize = 11.sp,
                            // --- COLOR CORREGIDO ---
                            color = ProfileMainPurple.copy(alpha = 0.8f)
                        )
                        Text(
                            text = record.entity,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            // --- COLOR CORREGIDO ---
                            color = ProfileMainPurple,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    // Columna para Fecha y Ver más
                    Column(horizontalAlignment = Alignment.End) {
                        Text(
                            text = "Inicio: ${record.date}",
                            fontSize = 11.sp,
                            // --- COLOR CORREGIDO ---
                            color = ProfileMainPurple.copy(alpha = 0.8f)
                        )
                        Text(
                            text = "Ver más",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            // --- COLOR CORREGIDO ---
                            color = ProfileMainPurple,
                            modifier = Modifier.clickable {}
                        )
                    }
                }
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
