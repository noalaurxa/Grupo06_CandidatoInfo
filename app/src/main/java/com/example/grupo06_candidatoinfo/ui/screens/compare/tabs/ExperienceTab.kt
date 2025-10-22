package com.example.grupo06_candidatoinfo.ui.screens.compare.tabs

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import com.example.grupo06_candidatoinfo.model.Candidate
import com.example.grupo06_candidatoinfo.ui.screens.compare.components.*
import com.example.grupo06_candidatoinfo.ui.theme.*

@Composable
fun ExperienceTab(candidate1: Candidate, candidate2: Candidate) {
    Column {
        SectionTitle("Trayectoria Política", Icons.Default.Work)
        ExperienceComparison(candidate1, candidate2)

        SectionTitle("Formación Académica", Icons.Default.School)
        EducationComparison(candidate1, candidate2)
    }
}

@Composable
private fun ExperienceComparison(candidate1: Candidate, candidate2: Candidate) {
    val positions1 = candidate1.profileDetails?.careerHistory?.items?.size ?: 0
    val positions2 = candidate2.profileDetails?.careerHistory?.items?.size ?: 0

    ComparisonCard {
        HighlightedDataRow(
            "Cargos Públicos",
            "$positions1 cargos",
            "$positions2 cargos",
            positions1 > positions2
        )

        Divider(color = neutralGray.copy(alpha = 0.2f))

        Column(modifier = Modifier.padding(vertical = 12.dp)) {
            Text("Experiencia más Reciente", fontSize = 12.sp, color = neutralGray, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                ExperienceCard(
                    candidate1.profileDetails?.careerHistory?.items?.firstOrNull()?.position ?: "Sin datos",
                    candidate1.profileDetails?.careerHistory?.items?.firstOrNull()?.period ?: "",
                    Modifier.weight(1f)
                )
                ExperienceCard(
                    candidate2.profileDetails?.careerHistory?.items?.firstOrNull()?.position ?: "Sin datos",
                    candidate2.profileDetails?.careerHistory?.items?.firstOrNull()?.period ?: "",
                    Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
private fun ExperienceCard(position: String, period: String, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        color = lightPurple
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                position,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = darkPurple,
                lineHeight = 16.sp
            )
            if (period.isNotEmpty()) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    period,
                    fontSize = 11.sp,
                    color = neutralGray
                )
            }
        }
    }
}

@Composable
private fun EducationComparison(candidate1: Candidate, candidate2: Candidate) {
    ComparisonCard {
        val degree1 = candidate1.profileDetails?.academicFormation?.degrees?.firstOrNull()
        val degree2 = candidate2.profileDetails?.academicFormation?.degrees?.firstOrNull()

        Column(modifier = Modifier.padding(vertical = 8.dp)) {
            Text("Máximo Grado Académico", fontSize = 12.sp, color = neutralGray, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(12.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                DegreeCard(
                    degree1?.title ?: "No especificado",
                    degree1?.institutionAndPeriod ?: "",
                    Modifier.weight(1f)
                )
                DegreeCard(
                    degree2?.title ?: "No especificado",
                    degree2?.institutionAndPeriod ?: "",
                    Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
private fun DegreeCard(degree: String, institution: String, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        color = accentGreen.copy(alpha = 0.1f),
        border = BorderStroke(1.dp, accentGreen.copy(alpha = 0.3f))
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                degree,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = darkPurple,
                lineHeight = 16.sp
            )
            if (institution.isNotEmpty()) {
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    institution,
                    fontSize = 11.sp,
                    color = neutralGray,
                    lineHeight = 13.sp
                )
            }
        }
    }
}