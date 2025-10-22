package com.example.grupo06_candidatoinfo.ui.screens.profile.tabs

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Gavel
import androidx.compose.material.ripple.rememberRipple // La importación de rememberRipple está aquí
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
import com.example.grupo06_candidatoinfo.ui.theme.ProfileLighterPurpleCard
import com.example.grupo06_candidatoinfo.ui.theme.ProfileMainPurple
import com.example.grupo06_candidatoinfo.ui.theme.RiskHighColor
import com.example.grupo06_candidatoinfo.ui.theme.TagArchivadoColor
import com.example.grupo06_candidatoinfo.ui.theme.TagInvestigacionColor
import com.example.grupo06_candidatoinfo.ui.theme.TagSentenciadoColor

// Función de utilidad para mapear el tag a sus colores
@Composable
fun getTagColors(tag: String): Pair<Color, Color> {
    // Returns: Pair<ContainerColor, ContentColor/AccentColor>
    return when (tag.lowercase()) {
        "archivado", "desestimado" -> TagArchivadoColor to Color(0xFFC0392B) // Light background, Dark Red accent
        "investigación", "activo", "pendiente", "juicio oral" -> TagInvestigacionColor to Color(0xFFD35400) // Light orange background, Dark Orange accent
        "sentenciado", "inhabilitado", "corrupción", "lavado de activos" -> TagSentenciadoColor to RiskHighColor // Light red background, High Risk Red accent (Usamos estos para alertar más)
        else -> Color.LightGray to Color.Black // Default para tags como 'Administrativo', 'Civil', etc.
    }
}

@Composable
fun BackgroundTabContent(backgroundReport: BackgroundReport) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        // Lista de Antecedentes
        if (backgroundReport.records.isEmpty()) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = ProfileLighterPurpleCard),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                border = BorderStroke(1.dp, Color(0xFFEBEAF1))
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
                items(backgroundReport.records) { record ->
                    BackgroundRecordCard(record = record)
                }
            }
        }
    }
}

@Composable
fun BackgroundRecordCard(record: BackgroundRecord) {
    // Combinamos statusTags y classificationTags en una sola lista para el display
    val allTags = remember(record) { record.statusTags + record.classificationTags }

    // Lógica para mostrar solo 2 tags + un chip de conteo si hay más
    val visibleTags = remember(allTags) { allTags.take(2) }
    val remainingTagsCount = remember(allTags) { allTags.size - visibleTags.size }

    // Tomamos el color principal de la barra vertical del PRIMER tag (el AccentColor)
    val primaryTag = allTags.firstOrNull() ?: "Sin Estado"
    val (_, primaryAccentColor) = getTagColors(primaryTag)

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            // --- 1. Indicador de riesgo vertical (Barra lateral) ---
            Box(
                modifier = Modifier
                    .width(6.dp)
                    .fillMaxHeight()
                    .background(primaryAccentColor)
            )

            // --- 2. Contenido de la tarjeta ---
            Column(modifier = Modifier.weight(1f)) {

                // Fila Superior: Icono, Título, Entidad y Tags
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 10.dp),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    // Icono (Mazo de juez)
                    Icon(
                        imageVector = Icons.Default.Gavel,
                        contentDescription = "Icono de Antecedente Legal",
                        tint = ProfileMainPurple.copy(alpha = 0.7f),
                        modifier = Modifier.size(24.dp)
                    )

                    // Título, Entidad y Tags
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        // 1. Título
                        Text(
                            text = record.title,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )

                        // 2. Entidad
                        Text(
                            text = record.entity,
                            fontSize = 12.sp,
                            color = Color.Gray,
                            fontWeight = FontWeight.Normal,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )

                        // 3. Tags (Solo mostramos los primeros dos)
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            contentPadding = PaddingValues(top = 4.dp)
                        ) {
                            items(visibleTags) { tag ->
                                val (container, content) = getTagColors(tag)
                                TagChip(text = tag.uppercase(), contentColor = content, containerColor = container)
                            }
                            // Chip para el conteo restante
                            if (remainingTagsCount > 0) {
                                item {
                                    TagChip(
                                        text = "+$remainingTagsCount MÁS",
                                        contentColor = Color.Black.copy(alpha = 0.7f),
                                        containerColor = Color.LightGray.copy(alpha = 0.5f)
                                    )
                                }
                            }
                        }
                    }
                }

                // Contenido Inferior (Descripción y Fecha)
                Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 0.dp, bottom = 16.dp)) {
                    // Descripción
                    Text(
                        text = record.description,
                        fontSize = 13.sp,
                        color = Color(0xFF555555),
                        lineHeight = 19.sp,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    // Fecha de inicio
                    Text(
                        text = "Inicio: ${record.date}",
                        fontSize = 12.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.Normal
                    )
                }

                // Separador
                Divider(
                    modifier = Modifier.padding(horizontal = 0.dp),
                    thickness = 0.5.dp,
                    color = Color.Gray.copy(alpha = 0.15f)
                )

                // Botón de "Ver Detalle"
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp)) // Clip the ripple to the card's bottom corners
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            onClick = { /* Acción para ver el detalle */ }
                        )
                        .padding(horizontal = 16.dp, vertical = 14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Ver Detalle del Registro",
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = ProfileMainPurple
                    )
                    Icon(
                        imageVector = Icons.Default.ChevronRight,
                        contentDescription = "Ver Detalle",
                        tint = ProfileMainPurple,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}

// Componente TagChip
@Composable
fun TagChip(text: String, contentColor: Color, containerColor: Color) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = containerColor.copy(alpha = 0.15f)
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 11.sp,
            color = contentColor,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
        )
    }
}
