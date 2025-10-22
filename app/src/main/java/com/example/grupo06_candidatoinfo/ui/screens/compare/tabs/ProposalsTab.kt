package com.example.grupo06_candidatoinfo.ui.screens.compare.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import com.example.grupo06_candidatoinfo.model.Candidate
import com.example.grupo06_candidatoinfo.ui.screens.compare.components.*
import com.example.grupo06_candidatoinfo.ui.theme.*

@Composable
fun ProposalsTab(candidate1: Candidate, candidate2: Candidate) {
    Column {
        SectionTitle("Plan de Gobierno", Icons.Default.Description)
        ProposalsComparison(candidate1, candidate2)
    }
}

@Composable
private fun ProposalsComparison(candidate1: Candidate, candidate2: Candidate) {
    val proposals1 = candidate1.profileDetails?.governmentPlan?.proposals?.size ?: 0
    val proposals2 = candidate2.profileDetails?.governmentPlan?.proposals?.size ?: 0

    ComparisonCard {
        HighlightedDataRow(
            "Propuestas Registradas",
            "$proposals1 propuestas",
            "$proposals2 propuestas",
            proposals1 > proposals2
        )

        Divider(color = neutralGray.copy(alpha = 0.2f))

        Column(modifier = Modifier.padding(vertical = 12.dp)) {
            Text("Áreas Principales", fontSize = 12.sp, color = neutralGray, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(12.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                ProposalAreasCard(
                    candidate1.profileDetails?.governmentPlan?.proposals?.map { it.title } ?: emptyList(),
                    Modifier.weight(1f)
                )
                ProposalAreasCard(
                    candidate2.profileDetails?.governmentPlan?.proposals?.map { it.title } ?: emptyList(),
                    Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
private fun ProposalAreasCard(areas: List<String>, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        color = lightPurple
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            if (areas.isEmpty()) {
                Text(
                    "Sin propuestas detalladas",
                    fontSize = 12.sp,
                    color = neutralGray,
                    fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                )
            } else {
                areas.take(3).forEach { area ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(6.dp)
                                .background(primaryPurple, CircleShape)
                        )
                        Text(
                            area,
                            fontSize = 12.sp,
                            color = darkPurple,
                            lineHeight = 16.sp
                        )
                    }
                    if (area != areas.take(3).last()) {
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
                if (areas.size > 3) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        "+${areas.size - 3} más",
                        fontSize = 11.sp,
                        color = neutralGray,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}