package com.example.grupo06_candidatoinfo.ui.screens.compare

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.grupo06_candidatoinfo.data.repository.MockDataRepository
import com.example.grupo06_candidatoinfo.model.Candidate
// import com.example.grupo06_candidatoinfo.model.RiskIndicator // <-- Eliminado
import java.text.NumberFormat
import java.util.Locale

// --- Colores ---
private val mainPurple = Color(0xFF3C3472)
private val lightGrayBackground = Color(0xFFF7F7F7)
// Colores de riesgo ya no son necesarios aquí
// private val riskHighColor = Color(0xFFD32F2F)
// private val riskMediumColor = Color(0xFFFFA000)
// private val riskLowColor = Color(0xFF388E3C)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompareScreen(
    navController: NavController,
    candidateIds: String?
) {
    // 1. Parsear los IDs y obtener los candidatos
    val candidates = remember(candidateIds) {
        val ids = candidateIds?.split(',')?.mapNotNull { it.toIntOrNull() } ?: emptyList()
        if (ids.size == 2) {
            val candidate1 = MockDataRepository.getCandidates().find { it.id == ids[0] }
            val candidate2 = MockDataRepository.getCandidates().find { it.id == ids[1] }
            if (candidate1 != null && candidate2 != null) {
                listOf(candidate1, candidate2)
            } else {
                emptyList()
            }
        } else {
            emptyList()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Comparar Candidatos") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = mainPurple,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        },
        containerColor = lightGrayBackground
    ) { paddingValues ->
        // 2. Mostrar la comparación o un mensaje de error
        if (candidates.size == 2) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Cabeceras con foto, nombre y partido
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        CandidateHeaderCompare(candidate = candidates[0], modifier = Modifier.weight(1f))
                        CandidateHeaderCompare(candidate = candidates[1], modifier = Modifier.weight(1f))
                    }
                }

                // Separador
                item {
                    Divider(modifier = Modifier.padding(vertical = 8.dp))
                }

                // --- SECCIÓN DE INFORMACIÓN GENERAL ---
                item {
                    ComparisonSection("Información General") {
                        ComparisonRow("Edad", candidates[0].profileDetails?.basicInfo?.age, candidates[1].profileDetails?.basicInfo?.age)
                        ComparisonRow("Lugar de Nacimiento", candidates[0].profileDetails?.basicInfo?.birthPlace, candidates[1].profileDetails?.basicInfo?.birthPlace)
                    }
                }

                // --- SECCIÓN DE PATRIMONIO ---
                item {
                    ComparisonSection("Patrimonio Declarado") {
                        val formatter = remember { NumberFormat.getCurrencyInstance(Locale("es", "PE")) }
                        ComparisonRow("Total",
                            candidates[0].profileDetails?.assetDeclaration?.totalPatrimony?.let { formatter.format(it) },
                            candidates[1].profileDetails?.assetDeclaration?.totalPatrimony?.let { formatter.format(it) }
                        )
                    }
                }

                // --- SECCIÓN DE FORMACIÓN ---
                item {
                    ComparisonSection("Máximo Grado Académico") {
                        ComparisonRow("Grado",
                            candidates[0].profileDetails?.academicFormation?.degrees?.firstOrNull()?.title,
                            candidates[1].profileDetails?.academicFormation?.degrees?.firstOrNull()?.title
                        )
                    }
                }

                // --- SECCIÓN DE TRAYECTORIA ---
                item {
                    ComparisonSection("Trayectoria Reciente") {
                        ComparisonRow("Último Cargo",
                            candidates[0].profileDetails?.careerHistory?.items?.firstOrNull()?.position,
                            candidates[1].profileDetails?.careerHistory?.items?.firstOrNull()?.position
                        )
                    }
                }

                // --- SECCIÓN DE ANTECEDENTES ELIMINADA ---
                // Se quita la sección del Indicador de Riesgo porque el modelo ya no lo contiene.
            }
        } else {
            // Mensaje si no se seleccionaron 2 candidatos
            Box(
                modifier = Modifier.fillMaxSize().padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Error: No se seleccionaron dos candidatos para comparar.",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(32.dp)
                )
            }
        }
    }
}

// --- COMPONENTES AUXILIARES PARA LA PANTALLA DE COMPARACIÓN ---

@Composable
fun CandidateHeaderCompare(candidate: Candidate, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AsyncImage(
            model = candidate.photoUrl,
            contentDescription = candidate.name,
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Text(
            text = candidate.name,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            minLines = 2
        )
        Text(
            text = candidate.party,
            fontSize = 12.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            minLines = 2
        )
    }
}

@Composable
fun ComparisonSection(title: String, content: @Composable ColumnScope.() -> Unit) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(
            text = title.uppercase(),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
        ) {
            Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                content()
            }
        }
    }
}

@Composable
fun ComparisonRow(label: String, value1: String?, value2: String?) {
    Column {
        Text(
            text = label,
            fontSize = 11.sp,
            color = mainPurple,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .background(lightGrayBackground, RoundedCornerShape(4.dp))
                .padding(horizontal = 8.dp, vertical = 4.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(value1 ?: "N/A", modifier = Modifier.weight(1f), fontSize = 13.sp, lineHeight = 16.sp)
            Text(value2 ?: "N/A", modifier = Modifier.weight(1f), fontSize = 13.sp, lineHeight = 16.sp)
        }
    }
}

// --- FUNCIÓN ComparisonRiskRow ELIMINADA ---
// Ya no es necesaria porque el modelo RiskIndicator fue eliminado.

