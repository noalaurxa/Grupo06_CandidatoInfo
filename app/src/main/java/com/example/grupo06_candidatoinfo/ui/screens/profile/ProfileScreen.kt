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
import androidx.compose.material.icons.filled.BusinessCenter
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.PlayCircleFilled
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.grupo06_candidatoinfo.data.repository.MockDataRepository
import com.example.grupo06_candidatoinfo.model.*
import com.example.grupo06_candidatoinfo.navigation.Screen
import com.example.grupo06_candidatoinfo.ui.theme.*
import java.text.NumberFormat
import java.util.Locale

fun Color.Companion.fromHex(hexString: String): Color {
    return Color(android.graphics.Color.parseColor(hexString))
}

// ==================== SCREEN PRINCIPAL ====================
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

    val profileDetails = candidate?.profileDetails
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("General", "Trayectoria", "Antecedentes", "Actualidad")

    Scaffold(
        containerColor = ProfileLightGrayBackground
    ) { paddingValues ->
        if (candidate == null) {
            Column(
                modifier = Modifier.fillMaxSize().padding(paddingValues).padding(16.dp),
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
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = paddingValues.calculateBottomPadding()),
                contentPadding = PaddingValues(0.dp)
            ) {
                // Header con foto
                item {
                    Box(modifier = Modifier.padding(horizontal = 16.dp).padding(top = 25.dp)) {
                        ProfileHeader(candidate = candidate, onBackClick = {
                            navController.popBackStack()
                        })
                    }
                }

                // Tabs de navegación
                item {
                    Surface(
                        color = Color.White,
                        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
                    ) {
                        ScrollableTabRow(
                            selectedTabIndex = selectedTabIndex,
                            containerColor = Color.Transparent,
                            contentColor = ProfileMainPurple,
                            edgePadding = 4.dp,
                            indicator = { },
                            divider = { }
                        ) {
                            tabs.forEachIndexed { index, title ->
                                Tab(
                                    selected = selectedTabIndex == index,
                                    onClick = { selectedTabIndex = index },
                                    modifier = Modifier
                                        .padding(horizontal = 4.dp, vertical=8.dp)
                                        .clip(RoundedCornerShape(16.dp)),
                                    selectedContentColor = Color.White,
                                    unselectedContentColor = Color.Gray
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .background(
                                                if (selectedTabIndex == index) ProfileMainPurple else Color.Transparent,
                                                RoundedCornerShape(16.dp)
                                            )
                                            .padding(horizontal = 16.dp, vertical = 8.dp),
                                        contentAlignment = Alignment.Center
                                    ){
                                        Text(
                                            text = title,
                                            fontSize = 13.sp,
                                            fontWeight = if (selectedTabIndex == index) FontWeight.Bold else FontWeight.Normal,
                                            maxLines = 1
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                // Contenido dinámico según el Tab
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 12.dp)
                    ) {
                        when (selectedTabIndex) {
                            0 -> {
                                if (profileDetails != null) {
                                    GeneralTabContent(
                                        navController = navController,
                                        profile = profileDetails
                                    )
                                } else {
                                    PlaceholderTabContent(title = "Información General no disponible")
                                }
                            }
                            1 -> {
                                if (profileDetails?.careerHistory != null) {
                                    CareerTabContent(careerHistory = profileDetails.careerHistory)
                                } else {
                                    PlaceholderTabContent(title = "Trayectoria no disponible")
                                }
                            }
                            2 -> PlaceholderTabContent(title = "Antecedentes")
                            3 -> PlaceholderTabContent(title = "Actualidad")
                        }
                    }
                }
            }
        }
    }
}

// ==================== HEADER ====================
@Composable
fun ProfileHeader(candidate: Candidate, onBackClick: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(32.dp
            ),
            colors = CardDefaults.cardColors(containerColor = ProfileMainPurple)
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .padding(top = 40.dp, bottom = 32.dp),
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
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Card(
                        modifier = Modifier.padding(vertical = 8.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = ProfileLighterPurpleCard)
                    ) {
                        Column(
                            modifier = Modifier.padding(horizontal = 24.dp, vertical = 10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Organización Política",
                                style = MaterialTheme.typography.labelMedium,
                                color = Color(0xFFD0CDE1)
                            )
                            Text(
                                text = candidate.party,
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color.White,
                                fontWeight = FontWeight.Medium,
                                textAlign = TextAlign.Center
                            )
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
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}

// ==================== TAB GENERAL ====================
@Composable
fun GeneralTabContent(navController: NavController, profile: CandidateProfile) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        BasicInfoCard(info = profile.basicInfo)
        ExpandableInfoCard(title = "DECLARACIÓN JURADA DE BIENES") {
            DeclaracionBienesContent(declaration = profile.assetDeclaration)
        }
        ExpandableInfoCard(title = "PLAN DE GOBIERNO") {
            PlanGobiernoContent(navController, plan = profile.governmentPlan)
        }
        ExpandableInfoCard(title = "FORMACIÓN ACADÉMICA") {
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
                .padding(horizontal = 20.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                InfoRow(label = "Fecha de Nacimiento:", value = info.birthDate, labelSize = 13.sp, valueSize = 15.sp)
                InfoRow(label = "Lugar de Nacimiento:", value = info.birthPlace, labelSize = 13.sp, valueSize = 15.sp)
            }
            Spacer(modifier = Modifier.width(20.dp))
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                InfoRow(label = "Edad:", value = info.age, labelSize = 13.sp, valueSize = 15.sp)
                InfoRow(label = "Estado Civil:", value = info.civilStatus, labelSize = 13.sp, valueSize = 15.sp)
                InfoRow(label = "Lugar de Residencia:", value = info.residencePlace, labelSize = 13.sp, valueSize = 15.sp)
            }
        }
    }
}

@Composable
private fun InfoRow(label: String, value: String, labelSize: androidx.compose.ui.unit.TextUnit, valueSize: androidx.compose.ui.unit.TextUnit) {
    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
        Text(
            text = label,
            fontSize = labelSize,
            color = Color(0xFF666666),
            fontWeight = FontWeight.Normal,
            lineHeight = (labelSize.value + 2).sp
        )
        Text(
            text = value,
            fontSize = valueSize,
            color = Color.Black,
            fontWeight = FontWeight.SemiBold,
            lineHeight = (valueSize.value + 2).sp
        )
    }
}

@Composable
fun DeclaracionBienesContent(declaration: AssetDeclaration) {
    val format = remember {
        NumberFormat.getCurrencyInstance(Locale("es", "PE")).apply {
            minimumFractionDigits = 2
        }
    }
    val progress = if (declaration.totalPatrimony > 0 && declaration.assets.isNotEmpty()) {
        (declaration.assets[0].value / declaration.totalPatrimony).toFloat()
    } else { 0f }
    val progressBarColor = if (declaration.assets.isNotEmpty()) Color.fromHex(declaration.assets[0].colorHex) else Color.Red

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = ProfileLightPurpleBackground)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Patrimonio Total Declarado",
                    fontSize = 14.sp,
                    color = Color(0xFF666666)
                )
                Text(
                    text = format.format(declaration.totalPatrimony),
                    fontSize = 16.sp,
                    color = ProfileMainPurple,
                    fontWeight = FontWeight.Bold
                )
            }
            LinearProgressIndicator(
                progress = progress,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(RoundedCornerShape(4.dp)),
                color = progressBarColor,
                trackColor = Color(0xFFE0E0E0),
                strokeCap = StrokeCap.Round
            )
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
                .size(9.dp)
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
        colors = CardDefaults.cardColors(containerColor = ProfileLightPurpleBackground)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = plan.summary,
                fontSize = 14.sp,
                color = Color(0xFF555555),
                lineHeight = 18.sp
            )
            plan.proposals.forEach { proposal ->
                PlanItem(
                    icon = Icons.Default.PlayCircleFilled,
                    title = proposal.title,
                    description = proposal.description
                )
            }
            Divider(
                modifier = Modifier.padding(vertical = 6.dp),
                thickness = 0.5.dp,
                color = Color.Gray.copy(alpha = 0.3f)
            )
            ClickableTextRow(
                text = "Ver Plan Completo (PDF)",
                icon = Icons.Default.MenuBook,
                onClick = {
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
        verticalAlignment = Alignment.Top
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = ProfileMainPurple,
            modifier = Modifier.size(36.dp).padding(top = 2.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
            Text(
                text = title,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                lineHeight = 18.sp
            )
            Text(
                text = description,
                fontSize = 13.sp,
                color = Color(0xFF666666),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                lineHeight = 16.sp
            )
        }
    }
}

@Composable
fun FormacionAcademicaContent(navController: NavController, formation: AcademicFormation) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = ProfileLightPurpleBackground)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            formation.degrees.forEach { degree ->
                FormacionItem(
                    icon = Icons.Default.School,
                    title = degree.title,
                    description = degree.institutionAndPeriod
                )
            }
            Divider(
                modifier = Modifier.padding(vertical = 6.dp),
                thickness = 0.5.dp,
                color = Color.Gray.copy(alpha = 0.3f)
            )
            ClickableTextRow(
                text = "Verificar en SUNEDU",
                icon = Icons.Default.Shield,
                onClick = {}
            )
        }
    }
}

@Composable
private fun FormacionItem(icon: ImageVector, title: String, description: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = ProfileMainPurple,
            modifier = Modifier.size(32.dp).padding(top = 2.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
            Text(
                text = title,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                lineHeight = 18.sp
            )
            Text(
                text = description,
                fontSize = 13.sp,
                color = Color(0xFF666666),
                lineHeight = 16.sp
            )
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
            tint = ProfileMainPurple,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            color = ProfileMainPurple,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp
        )
    }
}

@Composable
fun ExpandableInfoCard(
    title: String,
    initialExpanded: Boolean = false,
    content: @Composable () -> Unit
) {
    var expanded by remember { mutableStateOf(initialExpanded) }

    Column(modifier = Modifier.fillMaxWidth()) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = !expanded },
            shape = RoundedCornerShape(8.dp),
            color = Color.White,
            shadowElevation = 1.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF888888),
                    letterSpacing = 0.2.sp
                )
                Icon(
                    imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = "Expandir",
                    tint = Color(0xFF888888),
                    modifier = Modifier.size(22.dp)
                )
            }
        }

        AnimatedVisibility(visible = expanded) {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp),
                color = Color.White,
                shadowElevation = 1.dp
            ) {
                Box(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 8.dp)) {
                    content()
                }
            }
        }
    }
}

// ==================== TAB TRAYECTORIA ====================
@Composable
fun CareerTabContent(careerHistory: CareerHistory) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        careerHistory.items.forEachIndexed { index, careerItem ->
            CareerItemCard(
                careerItem = careerItem,
                isLastItem = index == careerHistory.items.size - 1
            )
        }
    }
}

@Composable
fun CareerItemCard(careerItem: CareerItem, isLastItem: Boolean) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(IntrinsicSize.Min)
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(ProfileMainPurple.copy(alpha = 0.8f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.BusinessCenter,
                    contentDescription = "Trabajo",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
            if (!isLastItem) {
                Divider(
                    color = ProfileMainPurple.copy(alpha = 0.5f),
                    modifier = Modifier
                        .width(2.dp)
                        .weight(1f)
                        .defaultMinSize(minHeight = 40.dp)
                )
            }
        }

        Card(
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = ProfileLightPurpleBackground.copy(alpha = 0.8f)),
            elevation = CardDefaults.cardElevation(0.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = careerItem.position,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = ProfileMainPurple.copy(alpha = 0.9f)
                ) {
                    Text(
                        text = careerItem.period,
                        color = Color.White,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                    )
                }
                Text(
                    text = careerItem.description,
                    fontSize = 13.sp,
                    color = Color(0xFF555555),
                    lineHeight = 17.sp
                )
            }
        }
    }
}

// ==================== PLACEHOLDER ====================
@Composable
fun PlaceholderTabContent(title: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = Color.Gray
            )
        }
    }
}