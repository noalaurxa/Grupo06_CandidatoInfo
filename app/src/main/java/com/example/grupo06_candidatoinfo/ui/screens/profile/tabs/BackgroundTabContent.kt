package com.example.grupo06_candidatoinfo.ui.screens.profile.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BusinessCenter // Icono de maletín para antecedentes
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
import com.example.grupo06_candidatoinfo.model.BackgroundRecord
import com.example.grupo06_candidatoinfo.model.BackgroundReport
// --- IMPORTS DE LOS NUEVOS COLORES DEL TEMA ---
import com.example.grupo06_candidatoinfo.ui.theme.lightPurpleCard
import com.example.grupo06_candidatoinfo.ui.theme.mainPurple
import com.example.grupo06_candidatoinfo.ui.theme.riskHighColor
import com.example.grupo06_candidatoinfo.ui.theme.tagArchivadoColor
import com.example.grupo06_candidatoinfo.ui.theme.tagInvestigacionColor
import com.example.grupo06_candidatoinfo.ui.theme.tagSentenciadoColor

@Composable
fun BackgroundTabContent(backgroundReport: BackgroundReport) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp) // Espacio entre tarjetas
    ) {
        // Título de la sección
        Text(
            text = "ANTECEDENTES",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF888888),
            modifier = Modifier.padding(start = 4.dp) // Quitamos padding superior extra
        )

        // Lista de Antecedentes
        if (backgroundReport.records.isEmpty()) {
            Text(
                text = "No se encontraron antecedentes.",
                fontSize = 14.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(vertical = 24.dp)
            )
        } else {
            backgroundReport.records.forEach { record ->
                BackgroundRecordCard(record = record)
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
                        .background(mainPurple.copy(alpha = 0.1f)), // Fondo morado muy claro
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.BusinessCenter, // Icono de maletín
                        contentDescription = "Antecedente",
                        tint = mainPurple, // Icono morado oscuro
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
                modifier = Modifier.padding(start = 60.dp) // Indentación para alinear con el título
            )

            // --- Tags de Estado ---
            Row(
                modifier = Modifier.padding(start = 60.dp), // Indentación
                horizontalArrangement = Arrangement.spacedBy(8.dp) // Espacio entre tags
            ) {
                record.tags.forEach { tag ->
                    val (backgroundColor, textColor) = when (tag.lowercase()) { // Usar lowercase para comparar
                        "archivado" -> tagArchivadoColor to Color(0xFFC0392B) // Texto rojo oscuro
                        "investigación" -> tagInvestigacionColor to Color(0xFFD35400) // Texto naranja oscuro
                        "juicio oral" -> tagInvestigacionColor to Color(0xFFD35400) // Mismo color
                        "sentenciado" -> tagSentenciadoColor to riskHighColor // Texto rojo
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
                colors = CardDefaults.cardColors(containerColor = lightPurpleCard.copy(alpha = 0.7f))
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
                            color = mainPurple.copy(alpha = 0.8f)
                        )
                        Text(
                            text = record.entity,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = mainPurple,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    // Columna para Fecha y Ver más
                    Column(horizontalAlignment = Alignment.End) {
                        Text(
                            text = "Inicio: ${record.date}",
                            fontSize = 11.sp,
                            color = mainPurple.copy(alpha = 0.8f)
                        )
                        Text(
                            text = "Ver más",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = mainPurple,
                            modifier = Modifier.clickable {}
                        )
                    }
                }
            }
        }
    }
}

