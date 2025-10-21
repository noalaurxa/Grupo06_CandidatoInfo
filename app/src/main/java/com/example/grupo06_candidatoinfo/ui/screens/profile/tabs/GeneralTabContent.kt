package com.example.grupo06_candidatoinfo.ui.screens.profile.tabs

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.grupo06_candidatoinfo.model.*
import com.example.grupo06_candidatoinfo.navigation.Screen
// --- IMPORTS DE COLOR CORREGIDOS ---
import com.example.grupo06_candidatoinfo.ui.theme.lightPurpleCard
import com.example.grupo06_candidatoinfo.ui.theme.mainPurple
import java.text.NumberFormat
import java.util.Locale

// Función de utilidad
fun Color.Companion.fromHex(hexString: String): Color {
    return Color(android.graphics.Color.parseColor(hexString))
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
        // --- COLOR CORREGIDO ---
        colors = CardDefaults.cardColors(containerColor = lightPurpleCard)
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
                    // --- COLOR CORREGIDO ---
                    color = mainPurple,
                    fontWeight = FontWeight.Bold
                )
            }
            // --- SINTAXIS DE PROGRESS CORREGIDA ---
            LinearProgressIndicator(
                progress = { progress },
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
        // --- COLOR CORREGIDO ---
        colors = CardDefaults.cardColors(containerColor = lightPurpleCard)
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
            // --- COLOR CORREGIDO ---
            tint = mainPurple,
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
        // --- COLOR CORREGIDO ---
        colors = CardDefaults.cardColors(containerColor = lightPurpleCard)
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
            // --- COLOR CORREGIDO ---
            tint = mainPurple,
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
            // --- COLOR CORREGIDO ---
            tint = mainPurple,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            // --- COLOR CORREGIDO ---
            color = mainPurple,
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


@Composable
fun GovernmentPlanCard(
    plan: GovernmentPlan,
    onDocumentClick: (documentId: String) -> Unit // RECIBE la función
) {
    DocumentSectionCard(
        title = "Plan de Gobierno",
        subtitle = "Documento oficial del plan de gestión",
        icon = Icons.Default.MenuBook,
        documentId = plan.documentId,
        onDocumentClick = onDocumentClick // Pasa la acción al Composable clickable
    ) {
        // Contenido del resumen del plan
        Text(
            text = plan.summary,
            fontSize = 14.sp,
            color = Color(0xFF555555),
            lineHeight = 20.sp,
            maxLines = 4,
        )
        // Puedes listar algunas propuestas clave si quieres
        plan.proposals.take(2).forEach { proposal ->
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Pequeño círculo como viñeta
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .clip(RoundedCornerShape(3.dp))
                        .background(ProfileMainPurple)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = proposal.title,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
            }
        }
    }
}

// ==================== FORMACIÓN ACADÉMICA ====================
@Composable
fun AcademicFormationCard(
    academicFormation: AcademicFormation,
    onDocumentClick: (documentId: String) -> Unit // RECIBE la función
) {
    DocumentSectionCard(
        title = "Formación Académica",
        subtitle = "Títulos registrados ante SUNEDU",
        icon = Icons.Default.School,
        documentId = academicFormation.documentId,
        onDocumentClick = onDocumentClick // Pasa la acción al Composable clickable
    ) {
        // Contenido de la formación académica
        academicFormation.degrees.forEach { degree ->
            Column(modifier = Modifier.padding(bottom = 8.dp)) {
                Text(
                    text = degree.title,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    color = Color.Black
                )
                Text(
                    text = degree.institutionAndPeriod,
                    fontSize = 13.sp,
                    color = Color.Gray
                )
            }
        }
    }
}


// ==================== COMPOSABLE REUTILIZABLE (CLICKEABLE) ====================
@Composable
fun DocumentSectionCard(
    title: String,
    subtitle: String,
    icon: ImageVector,
    documentId: String,
    onDocumentClick: (documentId: String) -> Unit, // NUEVO: Función de click
    content: @Composable () -> Unit // Contenido principal de la tarjeta
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // Título de la sección
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = ProfileMainPurple,
                    modifier = Modifier.size(24.dp)
                )
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = ProfileMainPurple
                    )
                    Text(
                        text = subtitle,
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }

            // Contenido
            Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                content()
            }
            Spacer(modifier = Modifier.height(16.dp))


            // Botón de VER DOCUMENTO
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDocumentClick(documentId) } // <--- ¡LA ACCIÓN DE NAVEGACIÓN!
                    .background(Color.Transparent)
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Ver Documento Completo",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
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