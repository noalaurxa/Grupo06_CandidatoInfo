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

// ===========================================================
// FUNCIONES AUXILIARES DE TAGS
// ===========================================================
@Composable
fun getTagColors(tag: String): Pair<Color, Color> {
    return when (tag.lowercase()) {
        "archivado", "desestimado" -> TagArchivadoColor to Color(0xFFC0392B)
        "investigación", "activo", "pendiente", "juicio oral" -> TagInvestigacionColor to Color(0xFFD35400)
        "sentenciado", "inhabilitado", "corrupción", "lavado de activos" -> TagSentenciadoColor to RiskHighColor
        else -> Color.LightGray to Color.Black
    }
}

// ===========================================================
// FUNCIÓN PRINCIPAL: BACKGROUNDTABCONTENT
// ===========================================================
@Composable
fun BackgroundTabContent(
    backgroundReport: BackgroundReport?,
    onBackgroundClick: (documentId: String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (backgroundReport == null || backgroundReport.records.isEmpty()) {
            // --- Estado vacío ---
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
            // --- Lista de antecedentes ---
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 800.dp)
            ) {
                items(backgroundReport.records) { record ->
                    val documentId = record.documentId
                    BackgroundRecordCard(
                        record = record,
                        onDetailClick = {
                            if (!documentId.isNullOrEmpty()) {
                                onBackgroundClick(documentId)
                            } else {
                                println("⚠️ Error: documentId nulo o vacío para este registro.")
                            }
                        }
                    )
                }
            }
        }
    }
}

// ===========================================================
// COMPOSABLE: BACKGROUNDRECORDCARD
// ===========================================================
@Composable
fun BackgroundRecordCard(
    record: BackgroundRecord,
    onDetailClick: () -> Unit
) {
    val allTags = remember(record) { record.statusTags + record.classificationTags }
    val visibleTags = remember(allTags) { allTags.take(2) }
    val remainingTagsCount = remember(allTags) { allTags.size - visibleTags.size }

    val primaryTag = allTags.firstOrNull() ?: "Sin Estado"
    val (_, primaryAccentColor) = getTagColors(primaryTag)

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            // --- 1. Indicador de riesgo lateral ---
            Box(
                modifier = Modifier
                    .width(6.dp)
                    .fillMaxHeight()
                    .background(primaryAccentColor)
            )

            // --- 2. Contenido principal ---
            Column(modifier = Modifier.weight(1f)) {

                // --- Encabezado ---
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 10.dp),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Gavel,
                        contentDescription = "Icono de Antecedente Legal",
                        tint = ProfileMainPurple.copy(alpha = 0.7f),
                        modifier = Modifier.size(24.dp)
                    )
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = record.title,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = record.entity,
                            fontSize = 12.sp,
                            color = Color.Gray,
                            fontWeight = FontWeight.Normal,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )

                        // --- Chips de tags ---
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            contentPadding = PaddingValues(top = 4.dp)
                        ) {
                            items(visibleTags) { tag ->
                                val (container, content) = getTagColors(tag)
                                TagChip(
                                    text = tag.uppercase(),
                                    contentColor = content,
                                    containerColor = container
                                )
                            }
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

                // --- Descripción ---
                Column(
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                ) {
                    Text(
                        text = record.description,
                        fontSize = 13.sp,
                        color = Color(0xFF555555),
                        lineHeight = 19.sp,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Inicio: ${record.date}",
                        fontSize = 12.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.Normal
                    )
                }

                Divider(
                    thickness = 0.5.dp,
                    color = Color.Gray.copy(alpha = 0.15f)
                )

                // --- Botón de "Ver Detalle" ---
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp))
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null, // 🔹 Evita doble efecto ripple
                            onClick = onDetailClick
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

// ===========================================================
// COMPONENTE: TAG CHIP
// ===========================================================
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
