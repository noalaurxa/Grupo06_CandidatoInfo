package com.example.grupo06_candidatoinfo.ui.screens.compare.tabs

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.*
import com.example.grupo06_candidatoinfo.model.Candidate
import com.example.grupo06_candidatoinfo.ui.screens.compare.components.*
import java.text.NumberFormat
import java.util.Locale

@Composable
fun SummaryTab(candidate1: Candidate, candidate2: Candidate) {
    Column {
        SectionTitle("Evaluación General", Icons.Default.Assessment)
        OverallScoreComparison(candidate1, candidate2)

        SectionTitle("Información Básica", Icons.Default.Person)
        BasicInfoComparison(candidate1, candidate2)

        SectionTitle("Patrimonio", Icons.Default.AccountBalance)
        PatrimonyComparison(candidate1, candidate2)
    }
}

@Composable
private fun BasicInfoComparison(candidate1: Candidate, candidate2: Candidate) {
    ComparisonCard {
        DataRow(
            "Edad",
            candidate1.profileDetails?.basicInfo?.age ?: "N/A",
            candidate2.profileDetails?.basicInfo?.age ?: "N/A"
        )
        androidx.compose.material3.Divider(color = com.example.grupo06_candidatoinfo.ui.theme.neutralGray.copy(alpha = 0.2f))
        DataRow(
            "Lugar de Nacimiento",
            candidate1.profileDetails?.basicInfo?.birthPlace ?: "N/A",
            candidate2.profileDetails?.basicInfo?.birthPlace ?: "N/A"
        )
        androidx.compose.material3.Divider(color = com.example.grupo06_candidatoinfo.ui.theme.neutralGray.copy(alpha = 0.2f))
        DataRow(
            "Estado Civil",
            candidate1.profileDetails?.basicInfo?.civilStatus ?: "N/A",
            candidate2.profileDetails?.basicInfo?.civilStatus ?: "N/A"
        )
    }
}

@Composable
private fun PatrimonyComparison(candidate1: Candidate, candidate2: Candidate) {
    val formatter = NumberFormat.getCurrencyInstance(Locale("es", "PE"))
    val patrimony1 = candidate1.profileDetails?.assetDeclaration?.totalPatrimony ?: 0.0
    val patrimony2 = candidate2.profileDetails?.assetDeclaration?.totalPatrimony ?: 0.0

    ComparisonCard {
        HighlightedDataRow(
            "Total Declarado",
            formatter.format(patrimony1),
            formatter.format(patrimony2),
            patrimony1 < patrimony2
        )

        androidx.compose.material3.Text(
            "💡 Menor patrimonio no implica mejor candidato, pero es importante para transparencia",
            fontSize = 11.sp,
            color = com.example.grupo06_candidatoinfo.ui.theme.neutralGray,
            modifier = Modifier.padding(vertical = 12.dp),
            lineHeight = 14.sp
        )
    }
}