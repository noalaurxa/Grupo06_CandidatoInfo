package com.example.grupo06_candidatoinfo.ui.screens.profile

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.PlayCircleFilled
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.grupo06_candidatoinfo.data.repository.MockDataRepository
import com.example.grupo06_candidatoinfo.model.Candidate
import com.example.grupo06_candidatoinfo.navigation.Screen
import java.text.NumberFormat
import java.util.Locale
import com.example.grupo06_candidatoinfo.model.AcademicFormation
import com.example.grupo06_candidatoinfo.model.AssetDeclaration
import com.example.grupo06_candidatoinfo.model.BasicInfo
import com.example.grupo06_candidatoinfo.model.CandidateProfile
import com.example.grupo06_candidatoinfo.model.GovernmentPlan

// --- Definición de colores del diseño ---
private val mainPurple = Color(0xFF3C3472)
private val lightPurpleBackground = Color(0xFFECEBFA)
private val lighterPurpleCard = Color(0xFF5D559C)
private val lightGrayBackground = Color(0xFFF7F7F7)
private val progressBarTrackColor = Color(0xFFD9D9D9)

// Función auxiliar para convertir Hex a Color
fun Color.Companion.fromHex(hexString: String): Color {
    return Color(android.graphics.Color.parseColor(hexString))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavController,
    candidateId: String?
) {
    val candidate = remember(candidateId) {
        MockDataRepository.getCandidates().find {
            it.id == candidateId?.toIntOrNull()
        }
    }

    // Obtenemos los detalles del perfil
    val profileDetails = candidate?.profileDetails

    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("General", "Trayectoria", "Antecedentes", "Actualidad")

    Scaffold(
        containerColor = lightGrayBackground
    ) { paddingValues ->

        if (candidate == null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Candidato no encontrado")
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { navController.popBackStack() }) {
                    Text("Volver")
                }
            }
        } else {
            // Contenido principal de la pantalla
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues.calculateBottomPadding()) // Ignoramos el padding superior
            ) {
                // 1. Header con foto y datos
                item {
                    ProfileHeader(candidate = candidate, onBackClick = {
                        navController.popBackStack()
                    })
                }

                // 2. Tabs de navegación
                item {
                    TabRow(
                        selectedTabIndex = selectedTabIndex,
                        containerColor = Color.White,
                        contentColor = mainPurple,
                        indicator = { tabPositions ->
                            TabRowDefaults.Indicator(
                                Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                                height = 3.dp,
                                color = mainPurple
                            )
                        }
                    ) {
                        tabs.forEachIndexed { index, title ->
                            Tab(
                                selected = selectedTabIndex == index,
                                onClick = { selectedTabIndex = index },
                                text = {
                                    Text(
                                        text = title,
                                        fontWeight = if (selectedTabIndex == index) FontWeight.Bold else FontWeight.Normal
                                    )
                                },
                                selectedContentColor = mainPurple,
                                unselectedContentColor = Color.Gray
                            )
                        }
                    }
                }

                // 3. Contenido dinámico según el Tab
                item {
                    Box(modifier = Modifier.padding(16.dp)) {
                        when (selectedTabIndex) {
                            0 -> {
                                // Verificamos si hay detalles de perfil
                                if (profileDetails != null) {
                                    GeneralTabContent(
                                        navController = navController,
                                        profile = profileDetails
                                    )
                                } else {
                                    // Mostramos un placeholder si no hay data
                                    PlaceholderTabContent(title = "Información no disponible")
                                }
                            }
                            1 -> PlaceholderTabContent(title = "Trayectoria")
                            2 -> PlaceholderTabContent(title = "Antecedentes")
                            3 -> PlaceholderTabContent(title = "Actualidad")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileHeader(candidate: Candidate, onBackClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(mainPurple)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp),
            colors = CardDefaults.cardColors(containerColor = mainPurple)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .padding(top = 24.dp, bottom = 24.dp), // Padding extra
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = candidate.photoUrl,
                    contentDescription = candidate.name,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .background(Color.Gray, CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = candidate.name,
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(16.dp))
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = lighterPurpleCard)
                ) {
                    Column(
                        modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Organización Política",
                            style = MaterialTheme.typography.labelMedium,
                            color = Color(0xFFD0CDE1) // Color de texto suave
                        )
                        Text(
                            text = candidate.party,
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.White,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
        IconButton(
            onClick = onBackClick,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Volver",
                tint = Color.White
            )
        }
    }
}

// --- Contenido del Tab "General" ---

@Composable
fun GeneralTabContent(navController: NavController, profile: CandidateProfile) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Datos Personales
        BasicInfoCard(info = profile.basicInfo)

        // Declaración Jurada
        ExpandableInfoCard(
            title = "DECLARACIÓN JURADA DE BIENES"
        ) {
            DeclaracionBienesContent(declaration = profile.assetDeclaration)
        }

        // Plan de Gobierno
        ExpandableInfoCard(
            title = "PLAN DE GOBIERNO"
        ) {
            PlanGobiernoContent(navController, plan = profile.governmentPlan)
        }

        // Formación Académica
        ExpandableInfoCard(
            title = "FORMACIÓN ACADÉMICA"
        ) {
            FormacionAcademicaContent(navController, formation = profile.academicFormation)
        }
    }
}

@Composable
fun BasicInfoCard(info: BasicInfo) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Columna Izquierda
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                InfoRow(label = "Fecha de Nacimiento:", value = info.birthDate)
                InfoRow(label = "Lugar de Nacimiento:", value = info.birthPlace)
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Columna Derecha
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                InfoRow(label = "Edad:", value = info.age)
                InfoRow(label = "Estado Civil:", value = info.civilStatus)
                InfoRow(label = "Lugar de Residencia:", value = info.residencePlace)
            }
        }
    }
}

@Composable
private fun InfoRow(label: String, value: String) {
    Column {
        Text(
            text = label,
            fontSize = 12.sp,
            color = Color.Gray
        )
        Text(
            text = value,
            fontSize = 14.sp,
            color = Color.Black,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun DeclaracionBienesContent(declaration: AssetDeclaration) {
    // Formateador de moneda
    val format = remember {
        NumberFormat.getCurrencyInstance(Locale("es", "PE")).apply {
            minimumFractionDigits = 2
        }
    }

    val progress = if (declaration.totalPatrimony > 0 && declaration.assets.isNotEmpty()) {
        (declaration.assets[0].value / declaration.totalPatrimony).toFloat()
    } else {
        0f
    }

    val progressBarColor = if (declaration.assets.isNotEmpty()) {
        Color.fromHex(declaration.assets[0].colorHex)
    } else {
        Color.Red
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = lightPurpleBackground)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Total
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Patrimonio Total Declarado",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Text(
                    text = format.format(declaration.totalPatrimony),
                    fontSize = 16.sp,
                    color = mainPurple,
                    fontWeight = FontWeight.Bold
                )
            }

            // Barra de progreso
            LinearProgressIndicator(
                progress = progress,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(CircleShape),
                color = progressBarColor,
                trackColor = progressBarTrackColor,
                strokeCap = StrokeCap.Round
            )

            // Desglose (iteramos sobre la lista de assets)
            declaration.assets.forEach { asset ->
                BienesRow(
                    label = asset.label,
                    value = format.format(asset.value),
                    color = Color.fromHex(asset.colorHex)
                )
            }
        }
    }
}

@Composable
private fun BienesRow(label: String, value: String, color: Color) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(8.dp)
                .background(color, CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = label,
            modifier = Modifier.weight(1f),
            fontSize = 14.sp,
            color = Color.Black
        )
        Text(
            text = value,
            fontSize = 14.sp,
            color = Color.Black,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun PlanGobiernoContent(navController: NavController, plan: GovernmentPlan) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = lightPurpleBackground)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(text = plan.summary, fontSize = 14.sp, color = Color.Gray)

            // Iteramos sobre las propuestas
            plan.proposals.forEach { proposal ->
                PlanItem(
                    icon = Icons.Default.PlayCircleFilled, // Icono fijo
                    title = proposal.title,
                    description = proposal.description
                )
            }

            Divider()

            ClickableTextRow(
                text = "Ver Plan Completo (PDF)",
                icon = Icons.Default.MenuBook,
                onClick = {
                    // Usamos el ID del documento del modelo
                    navController.navigate(Screen.Detail.createRoute(plan.documentId))
                }
            )
        }
    }
}

@Composable
private fun PlanItem(icon: ImageVector, title: String, description: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = mainPurple,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(text = title, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            Text(text = description, fontSize = 14.sp, color = Color.Gray, maxLines = 2, overflow = TextOverflow.Ellipsis)
        }
    }
}


@Composable
fun FormacionAcademicaContent(navController: NavController, formation: AcademicFormation) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = lightPurpleBackground)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Iteramos sobre los títulos académicos
            formation.degrees.forEach { degree ->
                FormacionItem(
                    icon = Icons.Default.School, // Icono fijo
                    title = degree.title,
                    description = degree.institutionAndPeriod
                )
            }

            Divider()

            ClickableTextRow(
                text = "Verificar en SUNEDU",
                icon = Icons.Default.Shield,
                onClick = {
                    // Usamos el ID del documento del modelo
                    navController.navigate(Screen.Detail.createRoute(formation.documentId))
                }
            )
        }
    }
}

@Composable
private fun FormacionItem(icon: ImageVector, title: String, description: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = mainPurple,
            modifier = Modifier.size(32.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(text = title, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            Text(text = description, fontSize = 14.sp, color = Color.Gray)
        }
    }
}

@Composable
private fun ClickableTextRow(text: String, icon: ImageVector, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = text,
            tint = mainPurple
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            color = mainPurple,
            fontWeight = FontWeight.Medium
        )
    }
}


@Composable
fun ExpandableInfoCard(
    title: String,
    initialExpanded: Boolean = true,
    content: @Composable () -> Unit
) {
    var expanded by remember { mutableStateOf(initialExpanded) }

    Column {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = !expanded },
            shape = RoundedCornerShape(12.dp),
            color = Color.White,
            shadowElevation = 1.dp
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                )
                Icon(
                    imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = "Expandir",
                    tint = Color.Gray
                )
            }
        }

        // Contenido (expandible)
        AnimatedVisibility(visible = expanded) {
            Column {
                Spacer(modifier = Modifier.height(8.dp)) // Espacio entre header y contenido
                content()
            }
        }
    }
}

@Composable
fun PlaceholderTabContent(title: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = Color.Gray
            )
        }
    }
}