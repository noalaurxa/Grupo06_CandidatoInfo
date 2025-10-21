package com.example.grupo06_candidatoinfo.ui.screens.profile.tabs

<<<<<<< HEAD
import androidx.compose.foundation.BorderStroke // <-- IMPORTADO
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
=======
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BusinessCenter // Icono de maletín para antecedentes
>>>>>>> f0cdad359463fc8d12d70855d733c2060391744a
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
<<<<<<< HEAD
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.grupo06_candidatoinfo.model.BackgroundHistory
import com.example.grupo06_candidatoinfo.model.BackgroundItem
//COLORES IMPORTADOS
import com.example.grupo06_candidatoinfo.ui.theme.ProfileMainPurple
import com.example.grupo06_candidatoinfo.ui.theme.ProfileLightPurpleBackground
import com.example.grupo06_candidatoinfo.ui.theme.ProfileStatusPink

=======
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
>>>>>>> f0cdad359463fc8d12d70855d733c2060391744a

@Composable
<<<<<<< HEAD
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
=======
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
>>>>>>> f0cdad359463fc8d12d70855d733c2060391744a
            }
        }
    }
}

@Composable
<<<<<<< HEAD
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
=======
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
>>>>>>> f0cdad359463fc8d12d70855d733c2060391744a
            }
        }
    }
}

<<<<<<< HEAD
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
=======
>>>>>>> f0cdad359463fc8d12d70855d733c2060391744a
