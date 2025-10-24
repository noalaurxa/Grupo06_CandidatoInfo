package com.example.grupo06_candidatoinfo.ui.screens.profile.tabs
import androidx.compose.ui.unit.dp
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
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector

// Importación necesaria para abrir URLs
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.grupo06_candidatoinfo.model.*
import java.text.NumberFormat
import java.util.Locale
import androidx.compose.foundation.layout.Arrangement


// Importar Colores
import com.example.grupo06_candidatoinfo.ui.theme.PrimaryColor
import com.example.grupo06_candidatoinfo.ui.theme.SecondaryColor
import com.example.grupo06_candidatoinfo.ui.theme.TertiaryColor
import com.example.grupo06_candidatoinfo.ui.theme.BackgroundLight
import com.example.grupo06_candidatoinfo.ui.theme.BorderColor
import com.example.grupo06_candidatoinfo.ui.theme.AccentColor
import com.example.grupo06_candidatoinfo.ui.theme.SuccessColor

// Función fromHex (sin cambios)
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
            // LLAMADA ACTUALIZADA: Ya no se pasa navController
            PlanGobiernoContent(plan = profile.governmentPlan)
        }

        // Formación académica
        ModernExpandableCard(
            title = "Formación Académica",
            icon = Icons.Outlined.School,
            initialExpanded = false
        ) {

            // Se mantiene navController si es necesario para SUNEDU u otra acción
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
            defaultElevation = 2.dp, // Ligera elevación para destacar
            pressedElevation = 1.dp
        )
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // Header clickeable
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = !expanded }
                    .padding(horizontal = 20.dp, vertical = 16.dp), // Padding ajustado
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(36.dp) // Tamaño ajustado
                            .clip(RoundedCornerShape(8.dp)) // Bordes más suaves
                            .background(AccentColor.copy(alpha = 0.1f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            tint = AccentColor,
                            modifier = Modifier.size(18.dp) // Icono más pequeño
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
                    tint = TertiaryColor.copy(alpha = 0.8f), // Tono más suave
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
                    Divider(color = BorderColor.copy(alpha = 0.5f), thickness = 1.dp) // Divisor más sutil
                    Box(modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp)) { // Padding consistente
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
        verticalArrangement = Arrangement.spacedBy(16.dp) // Espaciado ajustado
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Columna Izquierda
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                InfoField(label = "Fecha de Nacimiento", value = info.birthDate, icon = Icons.Outlined.CalendarToday)
                InfoField(label = "Lugar de Nacimiento", value = info.birthPlace, icon = Icons.Outlined.Place)
                InfoField(label = "Estado Civil", value = info.civilStatus, icon = Icons.Outlined.FavoriteBorder) // Icono alternativo
            }
            // Columna Derecha
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                InfoField(label = "Edad", value = info.age, icon = Icons.Outlined.Cake) // Icono alternativo
                InfoField(label = "Residencia", value = info.residencePlace, icon = Icons.Outlined.Home)
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
            modifier = Modifier.size(18.dp).padding(top = 2.dp) // Ajuste vertical
        )
        Column(verticalArrangement = Arrangement.spacedBy(2.dp)) { // Espaciado reducido
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
                lineHeight = 18.sp // Altura de línea ajustada
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
            maximumFractionDigits = 2 // Asegurar 2 decimales
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp) // Espaciado ajustado
    ) {
        // Total patrimonio
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(containerColor = BackgroundLight.copy(alpha = 0.7f)) // Más sutil
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp), // Padding ajustado
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Patrimonio Total Declarado", // Texto más descriptivo
                        fontSize = 12.sp,
                        color = TertiaryColor,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = format.format(declaration.totalPatrimony),
                        fontSize = 20.sp, // Tamaño ajustado
                        color = AccentColor,
                        fontWeight = FontWeight.Bold
                    )
                }
                Icon(
                    imageVector = Icons.Outlined.AccountBalanceWallet,
                    contentDescription = null,
                    tint = AccentColor.copy(alpha = 0.4f), // Más sutil
                    modifier = Modifier.size(40.dp) // Tamaño ajustado
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
                fontSize = 14.sp, // Ligeramente más grande
                fontWeight = FontWeight.SemiBold,
                color = SecondaryColor
            )

            declaration.assets.forEach { asset ->
                AssetRow(
                    label = asset.label,
                    value = format.format(asset.value),
                    percentage = if (declaration.totalPatrimony > 0.001) // Evitar división por cero o muy pequeño
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
        verticalArrangement = Arrangement.spacedBy(6.dp) // Espaciado reducido
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp) // Espaciado ajustado
            ) {
                Box(
                    modifier = Modifier
                        .size(8.dp) // Más pequeño
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
                    fontSize = 13.sp, // Tamaño ajustado
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
                .height(5.dp) // Ligeramente más grueso
                .clip(RoundedCornerShape(3.dp)), // Bordes más redondeados
            color = color,
            trackColor = BorderColor.copy(alpha = 0.3f), // Track más sutil
        )
    }
}

// ==================== PLAN DE GOBIERNO ====================
@Composable
fun PlanGobiernoContent(plan: GovernmentPlan) { // <-- NO RECIBE NavController
    // Obtener el manejador de URIs del contexto local
    val uriHandler = LocalUriHandler.current

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp) // Espaciado ajustado
    ) {
        // Resumen
        Text(
            text = "Resumen del Plan:", // Añadir título
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = SecondaryColor,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            text = plan.summary,
            fontSize = 14.sp,
            color = TertiaryColor, // Color más suave para el resumen
            lineHeight = 20.sp // Altura de línea ajustada
        )

        // Propuestas principales
        if (plan.proposals.isNotEmpty()) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Propuestas Clave", // Título alternativo
                    fontSize = 14.sp,
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

        // --- BOTÓN ACTUALIZADO PARA ABRIR URL ---
        if (!plan.documentUrl.isNullOrEmpty()) {
            OutlinedButton(
                onClick = {
                    try {
                        uriHandler.openUri(plan.documentUrl) // Usa el documentUrl
                    } catch (e: Exception) {
                        println("Error al intentar abrir la URL del plan: ${plan.documentUrl} - $e")
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = AccentColor
                ),
                border = ButtonDefaults.outlinedButtonBorder.copy(
                    width = 1.dp, // Borde más fino
                    brush = SolidColor(AccentColor.copy(alpha = 0.5f)) // Borde más sutil
                ),
                contentPadding = PaddingValues(vertical = 12.dp) // Padding interno
            ) {
                Icon(
                    imageVector = Icons.Outlined.Article, // Icono alternativo
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Ver Plan de Gobierno Completo", // Texto ajustado
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp
                )
            }
        } else {
            Text(
                text = "El documento completo del plan no está disponible.",
                fontSize = 13.sp,
                color = TertiaryColor.copy(alpha = 0.7f), // Más sutil
                modifier = Modifier.padding(top = 8.dp).align(Alignment.CenterHorizontally) // Centrado
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
            .background(BackgroundLight.copy(alpha = 0.7f)) // Fondo más sutil
            .padding(horizontal = 12.dp, vertical = 10.dp), // Padding ajustado
        horizontalArrangement = Arrangement.spacedBy(10.dp) // Espaciado ajustado
    ) {
        // Indicador visual (opcional)
        Box(
            modifier = Modifier
                .width(4.dp)
                .height(40.dp)
                .clip(RoundedCornerShape(2.dp))
                .background(AccentColor.copy(alpha = 0.5f))
                .align(Alignment.Top)
        )
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = PrimaryColor,
                lineHeight = 18.sp
            )
            Text(
                text = description,
                fontSize = 13.sp,
                color = TertiaryColor,
                lineHeight = 18.sp,
                maxLines = 3, // Permitir hasta 3 líneas
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

// ==================== FORMACIÓN ACADÉMICA ====================
@Composable
fun FormacionAcademicaContent(navController: NavController, formation: AcademicFormation) {
    // Obtener el manejador de URIs para el botón SUNEDU
    val uriHandler = LocalUriHandler.current
    // URL de ejemplo para SUNEDU (reemplazar con la real si existe)
    val suneduUrl = "https://enlinea.sunedu.gob.pe/verificaautoridad"

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Timeline de formación académica
        if (formation.degrees.isNotEmpty()) {
            Text(
                text = "Grados Registrados:", // Título
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = SecondaryColor,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            formation.degrees.forEachIndexed { index, degree ->
                DegreeTimelineItem(
                    title = degree.title,
                    institution = degree.institutionAndPeriod,
                    isLast = index == formation.degrees.size - 1
                )
            }
        } else {
            Text(
                text = "No se encontraron grados académicos registrados.",
                fontSize = 13.sp,
                color = TertiaryColor
            )
        }


        Spacer(modifier = Modifier.height(8.dp)) // Mayor espacio antes del botón

        // Botón verificar en SUNEDU
        OutlinedButton(
            onClick = {
                try {
                    uriHandler.openUri(suneduUrl) // Abrir enlace SUNEDU
                } catch (e: Exception) {
                    println("Error al abrir URL de SUNEDU: $e")
                }
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color.Transparent,
                contentColor = SuccessColor // Color verde
            ),
            border = ButtonDefaults.outlinedButtonBorder.copy(
                width = 1.dp, // Borde más fino
                brush = SolidColor(SuccessColor.copy(alpha = 0.5f)) // Borde más sutil
            ),
            contentPadding = PaddingValues(vertical = 12.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.VerifiedUser,
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Verificar Grados en SUNEDU", // Texto actualizado
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
            modifier = Modifier.width(16.dp) // Más estrecho
        ) {
            // Nodo circular
            Box(
                modifier = Modifier
                    .size(10.dp) // Más pequeño
                    .clip(CircleShape)
                    .background(AccentColor)
            )

            // Línea conectora
            if (!isLast) {
                Spacer( // Usamos Spacer para la línea
                    modifier = Modifier
                        .width(2.dp)
                        .weight(1f) // Ocupa espacio restante
                        .background(AccentColor.copy(alpha = 0.3f))
                )
            }
        }

        // Contenido del título
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = PrimaryColor,
                lineHeight = 18.sp
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
// Estos composables definen una estructura base para mostrar secciones
// que tienen un documento asociado (sea interno o externo).

@Composable
fun GovernmentPlanCard(
    plan: GovernmentPlan,
    // La acción ahora es abrir la URL, no navegar internamente
    // onDocumentClick: (documentId: String) -> Unit
) {
    val uriHandler = LocalUriHandler.current
    DocumentSectionCard(
        title = "Plan de Gobierno",
        subtitle = "Propuestas y lineamientos principales", // Subtítulo ajustado
        icon = Icons.Outlined.Description,
        documentId = plan.documentId,
        onDocumentClick = { _ ->
            plan.documentUrl?.let { url ->
                try { uriHandler.openUri(url) } catch (e: Exception) { println("Error URL plan: $e")}
            }
        },
        // Cambiamos el texto del botón
        buttonText = "Abrir Plan (PDF/Web)"
    ) {
        // Contenido interno de la tarjeta (resumen y propuestas)
        Text(
            text = plan.summary,
            fontSize = 14.sp,
            color = SecondaryColor,
            lineHeight = 20.sp,
            maxLines = 4,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        // Muestra solo hasta 2 propuestas clave
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
    // La acción aquí podría ser abrir URL de SUNEDU o navegar a detalle interno
    onDocumentClick: (documentId: String) -> Unit
) {
    val uriHandler = LocalUriHandler.current
    val suneduUrl = "https://enlinea.sunedu.gob.pe/"

    DocumentSectionCard(
        title = "Formación Académica",
        subtitle = "Grados y títulos registrados", // Subtítulo ajustado
        icon = Icons.Outlined.School,
        documentId = academicFormation.documentId, // ID interno
        // La acción podría ser abrir SUNEDU o la acción pasada
        onDocumentClick = { _ ->
            // Podrías decidir aquí si abrir SUNEDU o ejecutar onDocumentClick(documentId)
            try { uriHandler.openUri(suneduUrl) } catch (e: Exception) { /* ... */ }
            // Opcionalmente: onDocumentClick(academicFormation.documentId) si hay detalle interno
        },
        buttonText = "Verificar en SUNEDU" // Texto del botón
    ) {
        // Muestra los grados académicos
        if (academicFormation.degrees.isNotEmpty()){
            academicFormation.degrees.forEach { degree ->
                Column(modifier = Modifier.padding(bottom = 10.dp)) { // Espaciado ajustado
                    Text(
                        text = degree.title,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        color = PrimaryColor
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = degree.institutionAndPeriod,
                        fontSize = 13.sp,
                        color = TertiaryColor
                    )
                }
            }
        } else {
            Text(text = "No hay grados registrados.", fontSize = 13.sp, color = TertiaryColor)
        }
    }
}

@Composable
fun DocumentSectionCard(
    title: String,
    subtitle: String,
    icon: ImageVector,
    documentId: String, // ID interno del documento/sección
    onDocumentClick: (documentId: String) -> Unit, // Acción genérica al hacer clic
    buttonText: String = "Ver Documento Completo", // Texto del botón personalizable
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp) // Sombra base
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // Header de la sección (Icono, Título, Subtítulo)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 16.dp), // Padding ajustado
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(AccentColor.copy(alpha = 0.1f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = AccentColor,
                        modifier = Modifier.size(18.dp)
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

            // Contenido específico de la sección
            Box(modifier = Modifier.padding(horizontal = 20.dp)) {
                content()
            }
            Spacer(modifier = Modifier.height(16.dp))

            Divider(color = BorderColor.copy(alpha=0.5f), thickness = 1.dp)

            // Fila inferior con el botón/acción
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDocumentClick(documentId) }
                    .padding(horizontal = 20.dp, vertical = 14.dp), // Padding ajustado
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = buttonText,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    color = AccentColor
                )
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = buttonText, // Descripción para accesibilidad
                    tint = AccentColor,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}