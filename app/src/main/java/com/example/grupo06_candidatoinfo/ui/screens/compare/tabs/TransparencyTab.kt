package com.example.grupo06_candidatoinfo.ui.screens.compare.tabs

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import com.example.grupo06_candidatoinfo.model.BackgroundRecord
import com.example.grupo06_candidatoinfo.model.Candidate
import com.example.grupo06_candidatoinfo.ui.screens.compare.calculateRiskScore
import com.example.grupo06_candidatoinfo.ui.screens.compare.components.*
import com.example.grupo06_candidatoinfo.ui.theme.*

@Composable
fun TransparencyTab(candidate1: Candidate, candidate2: Candidate) {
    Column {
        SectionTitle("Antecedentes", Icons.Default.Gavel)
        BackgroundComparison(candidate1, candidate2)

        SectionTitle("Análisis de Riesgo", Icons.Default.Warning)
        RiskAnalysisComparison(candidate1, candidate2)
    }
}

@Composable
private fun BackgroundComparison(candidate1: Candidate, candidate2: Candidate) {
    val records1 = candidate1.profileDetails?.backgroundReport?.records?.size ?: 0
    val records2 = candidate2.profileDetails?.backgroundReport?.records?.size ?: 0

    val active1 = candidate1.profileDetails?.backgroundReport?.records?.count {
        it.statusTags.any { tag -> tag.equals("Activo", ignoreCase = true) }
    } ?: 0
    val active2 = candidate2.profileDetails?.backgroundReport?.records?.count {
        it.statusTags.any { tag -> tag.equals("Activo", ignoreCase = true) }
    } ?: 0

    ComparisonCard {
        HighlightedDataRow(
            "Total de Antecedentes",
            "$records1 registros",
            "$records2 registros",
            records1 < records2,
            invertColors = true
        )

        Divider(color = neutralGray.copy(alpha = 0.2f))

        HighlightedDataRow(
            "Casos Activos",
            "$active1 activos",
            "$active2 activos",
            active1 < active2,
            invertColors = true
        )

        Divider(color = neutralGray.copy(alpha = 0.2f))

        Column(modifier = Modifier.padding(vertical = 12.dp)) {
            Text("Detalles", fontSize = 12.sp, color = neutralGray, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(12.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                BackgroundDetailCard(
                    candidate1.profileDetails?.backgroundReport?.records ?: emptyList(),
                    Modifier.weight(1f)
                )
                BackgroundDetailCard(
                    candidate2.profileDetails?.backgroundReport?.records ?: emptyList(),
                    Modifier.weight(1f)
                )
            }
        }

        Text(
            "⚠️ Los antecedentes deben evaluarse en contexto. No todos implican culpabilidad.",
            fontSize = 11.sp,
            color = accentYellow,
            modifier = Modifier.padding(top = 12.dp),
            lineHeight = 14.sp
        )
    }
}

@Composable
private fun BackgroundDetailCard(records: List<BackgroundRecord>, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        color = accentRed.copy(alpha = 0.05f),
        border = BorderStroke(1.dp, accentRed.copy(alpha = 0.2f))
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            if (records.isEmpty()) {
                Text(
                    "✓ Sin antecedentes",
                    fontSize = 12.sp,
                    color = accentGreen,
                    fontWeight = FontWeight.Medium
                )
            } else {
                records.take(2).forEach { record ->
                    Column {
                        Text(
                            record.title,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = darkPurple,
                            lineHeight = 14.sp
                        )
                        Spacer(modifier = Modifier.height(4.dp))

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            modifier = Modifier.padding(vertical = 4.dp)
                        ) {
                            record.statusTags.take(2).forEach { tag ->
                                StatusChip(tag)
                            }
                        }
                    }
                    if (record != records.take(2).last()) {
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
                if (records.size > 2) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        "+${records.size - 2} más",
                        fontSize = 10.sp,
                        color = neutralGray
                    )
                }
            }
        }
    }
}

@Composable
private fun StatusChip(tag: String) {
    val (bgColor, textColor) = when {
        tag.equals("Activo", ignoreCase = true) -> accentRed.copy(alpha = 0.15f) to accentRed
        tag.equals("Archivado", ignoreCase = true) -> neutralGray.copy(alpha = 0.15f) to neutralGray
        tag.equals("Sentenciado", ignoreCase = true) -> accentRed.copy(alpha = 0.2f) to accentRed
        else -> lightPurple to primaryPurple
    }

    Surface(
        shape = RoundedCornerShape(4.dp),
        color = bgColor
    ) {
        Text(
            tag,
            fontSize = 9.sp,
            color = textColor,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
        )
    }
}

@Composable
private fun RiskAnalysisComparison(candidate1: Candidate, candidate2: Candidate) {
    val risk1 = calculateRiskScore(candidate1)
    val risk2 = calculateRiskScore(candidate2)

    ComparisonCard {
        Column(modifier = Modifier.padding(vertical = 8.dp)) {
            Text("Nivel de Riesgo Evaluado", fontSize = 12.sp, color = neutralGray, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                RiskIndicator(risk1, Modifier.weight(1f))
                RiskIndicator(risk2, Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                "📊 El nivel de riesgo se calcula en base a:",
                fontSize = 11.sp,
                color = neutralGray,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(6.dp))

            val factors = listOf(
                "• Número de casos activos",
                "• Gravedad de las acusaciones",
                "• Estado de los procesos",
                "• Transparencia patrimonial"
            )

            factors.forEach { factor ->
                Text(
                    factor,
                    fontSize = 10.sp,
                    color = neutralGray,
                    lineHeight = 14.sp
                )
            }
        }
    }
}

@Composable
private fun RiskIndicator(riskScore: Int, modifier: Modifier = Modifier) {
    val (riskLevel, color) = when {
        riskScore >= 70 -> "Alto" to accentRed
        riskScore >= 40 -> "Medio" to accentYellow
        else -> "Bajo" to accentGreen
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .background(color.copy(alpha = 0.1f), CircleShape)
                .border(4.dp, color, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    "$riskScore",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = color
                )
                Text(
                    "/100",
                    fontSize = 10.sp,
                    color = neutralGray
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Surface(
            shape = RoundedCornerShape(12.dp),
            color = color.copy(alpha = 0.15f)
        ) {
            Text(
                "Riesgo $riskLevel",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = color,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
            )
        }
    }
}