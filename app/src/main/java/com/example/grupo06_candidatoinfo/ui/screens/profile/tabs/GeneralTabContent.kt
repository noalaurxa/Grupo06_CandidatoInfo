package com.example.grupo06_candidatoinfo.ui.screens.profile.tabs

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.grupo06_candidatoinfo.model.*
import com.example.grupo06_candidatoinfo.navigation.Screen
import com.example.grupo06_candidatoinfo.ui.theme.ProfileMainPurple
import java.text.NumberFormat
import java.util.Locale

// Importar Colores
import com.example.grupo06_candidatoinfo.ui.theme.PrimaryColor
import com.example.grupo06_candidatoinfo.ui.theme.SecondaryColor
import com.example.grupo06_candidatoinfo.ui.theme.TertiaryColor
import com.example.grupo06_candidatoinfo.ui.theme.BackgroundLight
import com.example.grupo06_candidatoinfo.ui.theme.BorderColor
import com.example.grupo06_candidatoinfo.ui.theme.AccentColor
import com.example.grupo06_candidatoinfo.ui.theme.SuccessColor

fun Color.Companion.fromHex(hexString: String): Color {
    return Color(android.graphics.Color.parseColor(hexString))
}

// ==================== TAB GENERAL ====================
@Composable
fun GeneralTabContent(navController: NavController, profile: CandidateProfile) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Información básica expandida por defecto
        ModernExpandableCard(
            title = "Información Personal",
            icon = Icons.Outlined.Person,
            initialExpanded = true
        ) {
            BasicInfoContent(info = profile.basicInfo)
        }

        // Declaración de bienes
        ModernExpandableCard(
            title = "Patrimonio Declarado",
            icon = Icons.Outlined.AccountBalance,
            initialExpanded = false
        ) {
            DeclaracionBienesContent(declaration = profile.assetDeclaration)
        }

        // Plan de gobierno
        ModernExpandableCard(
            title = "Plan de Gobierno",
            icon = Icons.Outlined.Description,
            initialExpanded = false
        ) {
            PlanGobiernoContent(navController, plan = profile.governmentPlan)
        }

        // Formación académica
        ModernExpandableCard(
            title = "Formación Académica",
            icon = Icons.Outlined.School,
            initialExpanded = false
        ) {
            FormacionAcademicaContent(navController, formation = profile.academicFormation)
        }
    }
}

// ==================== CARD EXPANDIBLE MODERNA ====================
@Composable
fun ModernExpandableCard(
    title: String,
    icon: ImageVector,
    initialExpanded: Boolean = false,
    content: @Composable () -> Unit
) {
    var expanded by remember { mutableStateOf(initialExpanded) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp
        )
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // Header clickeable
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = !expanded }
                    .padding(20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(AccentColor.copy(alpha = 0.1f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            tint = AccentColor,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Text(
                        text = title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = PrimaryColor
                    )
                }
                Icon(
                    imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                    contentDescription = if (expanded) "Contraer" else "Expandir",
                    tint = TertiaryColor,
                    modifier = Modifier.size(24.dp)
                )
            }

            // Contenido expandible con animación
            AnimatedVisibility(
                visible = expanded,
                enter = expandVertically(animationSpec = tween(300)) + fadeIn(animationSpec = tween(300)),
                exit = shrinkVertically(animationSpec = tween(300)) + fadeOut(animationSpec = tween(300))
            ) {
                Column {
                    Divider(color = BorderColor, thickness = 1.dp)
                    Box(modifier = Modifier.padding(20.dp)) {
                        content()
                    }
                }
            }
        }
    }
}

// ==================== INFORMACIÓN BÁSICA ====================
@Composable
fun BasicInfoContent(info: BasicInfo) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                InfoField(
                    label = "Fecha de Nacimiento",
                    value = info.birthDate,
                    icon = Icons.Outlined.CalendarToday
                )
                InfoField(
                    label = "Lugar de Nacimiento",
                    value = info.birthPlace,
                    icon = Icons.Outlined.Place
                )
                InfoField(
                    label = "Estado Civil",
                    value = info.civilStatus,
                    icon = Icons.Outlined.Favorite
                )
            }
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                InfoField(
                    label = "Edad",
                    value = info.age,
                    icon = Icons.Outlined.Person
                )
                InfoField(
                    label = "Residencia",
                    value = info.residencePlace,
                    icon = Icons.Outlined.Home
                )
            }
        }
    }
}

@Composable
private fun InfoField(label: String, value: String, icon: ImageVector) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.Top
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = AccentColor.copy(alpha = 0.7f),
            modifier = Modifier.size(18.dp).padding(top = 1.dp)
        )
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                text = label,
                fontSize = 12.sp,
                color = TertiaryColor,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = value,
                fontSize = 14.sp,
                color = PrimaryColor,
                fontWeight = FontWeight.Normal,
                lineHeight = 20.sp
            )
        }
    }
}

// ==================== DECLARACIÓN DE BIENES ====================
@Composable
fun DeclaracionBienesContent(declaration: AssetDeclaration) {
    val format = remember {
        NumberFormat.getCurrencyInstance(Locale("es", "PE")).apply {
            minimumFractionDigits = 2
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // Total patrimonio
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(containerColor = BackgroundLight)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Patrimonio Total",
                        fontSize = 12.sp,
                        color = TertiaryColor,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = format.format(declaration.totalPatrimony),
                        fontSize = 22.sp,
                        color = AccentColor,
                        fontWeight = FontWeight.Bold
                    )
                }
                Icon(
                    imageVector = Icons.Outlined.AccountBalanceWallet,
                    contentDescription = null,
                    tint = AccentColor.copy(alpha = 0.3f),
                    modifier = Modifier.size(48.dp)
                )
            }
        }

        // Lista de activos
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Composición del Patrimonio",
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = SecondaryColor
            )

            declaration.assets.forEach { asset ->
                AssetRow(
                    label = asset.label,
                    value = format.format(asset.value),
                    percentage = if (declaration.totalPatrimony > 0)
                        (asset.value / declaration.totalPatrimony * 100).toInt()
                    else 0,
                    color = Color.fromHex(asset.colorHex)
                )
            }
        }
    }
}

@Composable
private fun AssetRow(label: String, value: String, percentage: Int, color: Color) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(color)
                )
                Text(
                    text = label,
                    fontSize = 14.sp,
                    color = SecondaryColor,
                    fontWeight = FontWeight.Normal
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "$percentage%",
                    fontSize = 12.sp,
                    color = TertiaryColor,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = value,
                    fontSize = 14.sp,
                    color = PrimaryColor,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
        LinearProgressIndicator(
            progress = { percentage / 100f },
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .clip(RoundedCornerShape(2.dp)),
            color = color,
            trackColor = BorderColor,
        )
    }
}

// ==================== PLAN DE GOBIERNO ====================
@Composable
fun PlanGobiernoContent(navController: NavController, plan: GovernmentPlan) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // Resumen
        Text(
            text = plan.summary,
            fontSize = 14.sp,
            color = SecondaryColor,
            lineHeight = 22.sp
        )

        // Propuestas principales
        if (plan.proposals.isNotEmpty()) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Propuestas Principales",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = SecondaryColor
                )

                plan.proposals.forEach { proposal ->
                    ProposalItem(
                        title = proposal.title,
                        description = proposal.description
                    )
                }
            }
        }

        // Botón ver documento completo
        OutlinedButton(
            // CÓDIGO ACTUALIZADO: Se asume que 'Screen.PlanDetail' es la ruta correcta.
            onClick = { navController.navigate(Screen.PlanDetail.createRoute(plan.documentId)) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color.Transparent,
                contentColor = AccentColor
            ),
            border = ButtonDefaults.outlinedButtonBorder.copy(width = 1.5.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.Description,
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Ver Plan Completo",
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
private fun ProposalItem(title: String, description: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(BackgroundLight)
            .padding(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier
                .size(6.dp)
                .clip(CircleShape)
                .background(AccentColor)
                .align(Alignment.Top)
                .offset(y = 7.dp)
        )
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = PrimaryColor,
                lineHeight = 20.sp
            )
            Text(
                text = description,
                fontSize = 13.sp,
                color = TertiaryColor,
                lineHeight = 18.sp,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

// ==================== FORMACIÓN ACADÉMICA ====================
@Composable
fun FormacionAcademicaContent(navController: NavController, formation: AcademicFormation) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Timeline de formación académica
        formation.degrees.forEachIndexed { index, degree ->
            DegreeTimelineItem(
                title = degree.title,
                institution = degree.institutionAndPeriod,
                isLast = index == formation.degrees.size - 1
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        // Botón verificar en SUNEDU
        OutlinedButton(
            onClick = { /* Acción de verificación */ },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color.Transparent,
                contentColor = SuccessColor
            ),
            border = ButtonDefaults.outlinedButtonBorder.copy(
                width = 1.5.dp,
                brush = androidx.compose.ui.graphics.SolidColor(SuccessColor)
            )
        ) {
            Icon(
                imageVector = Icons.Outlined.VerifiedUser,
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Verificar en SUNEDU",
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
private fun DegreeTimelineItem(title: String, institution: String, isLast: Boolean) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Timeline con nodo
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(24.dp)
        ) {
            // Nodo circular
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .clip(CircleShape)
                    .background(AccentColor)
            )

            // Línea conectora
            if (!isLast) {
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .height(60.dp)
                        .background(AccentColor.copy(alpha = 0.3f))
                )
            }
        }

        // Contenido del título
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(bottom = if (isLast) 0.dp else 12.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = PrimaryColor,
                lineHeight = 20.sp
            )
            Text(
                text = institution,
                fontSize = 13.sp,
                color = TertiaryColor,
                lineHeight = 18.sp
            )
        }
    }
}

@Composable
private fun DegreeItem(title: String, institution: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(BackgroundLight)
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(AccentColor.copy(alpha = 0.1f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.School,
                contentDescription = null,
                tint = AccentColor,
                modifier = Modifier.size(20.dp)
            )
        }
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = PrimaryColor,
                lineHeight = 20.sp
            )
            Text(
                text = institution,
                fontSize = 13.sp,
                color = TertiaryColor,
                lineHeight = 18.sp
            )
        }
    }
}

// ==================== COMPOSABLES REUTILIZABLES ====================
@Composable
fun GovernmentPlanCard(
    plan: GovernmentPlan,
    onDocumentClick: (documentId: String) -> Unit
) {
    DocumentSectionCard(
        title = "Plan de Gobierno",
        subtitle = "Documento oficial del plan de gestión",
        icon = Icons.Outlined.Description,
        documentId = plan.documentId,
        onDocumentClick = onDocumentClick
    ) {
        Text(
            text = plan.summary,
            fontSize = 14.sp,
            color = SecondaryColor,
            lineHeight = 20.sp,
            maxLines = 4,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(12.dp))
        plan.proposals.take(2).forEach { proposal ->
            Row(
                modifier = Modifier.padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .clip(CircleShape)
                        .background(AccentColor)
                )
                Text(
                    text = proposal.title,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    color = PrimaryColor
                )
            }
        }
    }
}

@Composable
fun AcademicFormationCard(
    academicFormation: AcademicFormation,
    onDocumentClick: (documentId: String) -> Unit
) {
    DocumentSectionCard(
        title = "Formación Académica",
        subtitle = "Títulos registrados ante SUNEDU",
        icon = Icons.Outlined.School,
        documentId = academicFormation.documentId,
        onDocumentClick = onDocumentClick
    ) {
        academicFormation.degrees.forEach { degree ->
            Column(modifier = Modifier.padding(bottom = 12.dp)) {
                Text(
                    text = degree.title,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    color = PrimaryColor
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = degree.institutionAndPeriod,
                    fontSize = 13.sp,
                    color = TertiaryColor
                )
            }
        }
    }
}

@Composable
fun DocumentSectionCard(
    title: String,
    subtitle: String,
    icon: ImageVector,
    documentId: String,
    onDocumentClick: (documentId: String) -> Unit,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(AccentColor.copy(alpha = 0.1f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = AccentColor,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = title,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        color = PrimaryColor
                    )
                    Text(
                        text = subtitle,
                        fontSize = 12.sp,
                        color = TertiaryColor
                    )
                }
            }

            Box(modifier = Modifier.padding(horizontal = 20.dp)) {
                content()
            }
            Spacer(modifier = Modifier.height(16.dp))

            Divider(color = BorderColor, thickness = 1.dp)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDocumentClick(documentId) }
                    .padding(horizontal = 20.dp, vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Ver Documento Completo",
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    color = AccentColor
                )
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Ver documento",
                    tint = AccentColor,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}