package com.example.grupo06_candidatoinfo.ui.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack // Icono correcto para 'Atrás'
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.grupo06_candidatoinfo.data.repository.CandidatoRepository
import com.example.grupo06_candidatoinfo.model.CaseTimelineEvent
import com.example.grupo06_candidatoinfo.model.InvestigationDetail
import com.example.grupo06_candidatoinfo.model.InvolvedParty
import com.example.grupo06_candidatoinfo.model.OfficialDocument

// --- Colores Importados ---
import com.example.grupo06_candidatoinfo.ui.theme.PrimaryPurple1
import com.example.grupo06_candidatoinfo.ui.theme.LightPurple1
import com.example.grupo06_candidatoinfo.ui.theme.AccentPurple1
import com.example.grupo06_candidatoinfo.ui.theme.BackgroundGray1
import com.example.grupo06_candidatoinfo.ui.theme.DividerGray1

// ===========================================================
// FUNCIÓN PRINCIPAL: INVESTIGATIONDETAIL
// ===========================================================
@Composable
fun InvestigationDetailScreen(
    navController: NavController,
    documentId: String?
) {
    var investigationDetail: InvestigationDetail? by remember { mutableStateOf(null) }
    val repository = remember { CandidatoRepository() }

    LaunchedEffect(documentId) {
        if (documentId != null) {
            try {
                investigationDetail = repository.getInvestigationDetail(documentId)
            } catch (e: Exception) {
                investigationDetail = null
            }
        }
    }

    Scaffold(
    ) { paddingValues ->

        // Contenido principal con scroll vertical
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues) // Padding esencial del Scaffold
                .verticalScroll(rememberScrollState())
                // Añadimos padding inferior adicional para que el último elemento no quede pegado
                .padding(bottom = 16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            if (investigationDetail == null) {
                // --- Estado de Error ---
                ErrorState(documentId = documentId)
            } else {
                // --- Contenido Detallado ---
                Spacer(modifier = Modifier.height(16.dp)) // Espacio inicial superior

                // Sección 1: Cronología
                SectionTitle(title = "Cronología del Caso")
                Divider(
                    color = DividerGray1,
                    thickness = 1.dp,
                    modifier = Modifier.padding(
                        start = 20.dp,
                        end = 20.dp,
                        bottom = 16.dp,  // Tu padding inferior
                        top = 0.dp
                    )
                    )
                TimelineSection(timeline = investigationDetail?.timeline ?: emptyList())
                Spacer(modifier = Modifier.height(24.dp))

                // Sección 2: Documentos Oficiales
                SectionTitle(title = "Documentos Oficiales")
                Divider(
                    color = DividerGray1,
                    thickness = 1.dp,
                    modifier = Modifier.padding(
                        start = 20.dp,
                        end = 20.dp,
                        bottom = 16.dp,  // Tu padding inferior
                        top = 0.dp
                    )
                )
                DocumentSection(documents = investigationDetail?.officialDocuments ?: emptyList())
                Spacer(modifier = Modifier.height(24.dp))

                // Sección 3: Involucrados
                SectionTitle(title = "Involucrados")
                Divider(
                    color = DividerGray1,
                    thickness = 1.dp,
                    modifier = Modifier.padding(
                        start = 20.dp,
                        end = 20.dp,
                        bottom = 16.dp,
                        top = 0.dp
                    )                )
                InvolvedPartySection(parties = investigationDetail?.involvedParties ?: emptyList())
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

// ===========================================================
// SUB-COMPOSABLES PARA LAS SECCIONES Y ELEMENTOS UI
// ===========================================================

// --- Estado de Error ---
@Composable
private fun ErrorState(documentId: String?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Error: Detalle de investigación no encontrado.",
            color = MaterialTheme.colorScheme.error,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleMedium
        )
        if (documentId != null) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "ID buscado: $documentId",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }
    }
}

// --- Título de Sección ---
@Composable
private fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        color = MaterialTheme.colorScheme.onSurface,
        modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 8.dp, bottom = 16.dp)
    )
}

// --- Sección Cronología ---
@Composable
private fun TimelineSection(timeline: List<CaseTimelineEvent>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        timeline.forEachIndexed { index, event ->
            TimelineItem(
                event = event,
                isLast = index == timeline.size - 1
            )
        }
    }
}

// --- Item de la Cronología (Nodo, Línea y Tarjeta) ---
@Composable
private fun TimelineItem(
    event: CaseTimelineEvent,
    isLast: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min) // Para que la línea se estire correctamente
            .padding(bottom = if (isLast) 0.dp else 8.dp) // Espaciado entre items
    ) {
        // --- Indicador de Tiempo (Nodo y Línea) ---
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .width(40.dp)
                .fillMaxHeight() // Asegura que la columna se estire
        ) {
            // Nodo Circular
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .clip(CircleShape)
                    .background(PrimaryPurple1)
                    .padding(3.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.5f))
            )

            // Línea Conectora (si no es el último)
            if (!isLast) {
                Spacer(
                    modifier = Modifier
                        .width(2.dp)
                        .weight(1f) // Ocupa todo el espacio vertical restante
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    PrimaryPurple1.copy(alpha = 0.7f),
                                    PrimaryPurple1.copy(alpha = 0.3f)
                                )
                            )
                        )
                )
            }
        }

        Spacer(modifier = Modifier.width(12.dp)) // Espacio entre indicador y tarjeta

        // --- Contenido del Evento (Tarjeta) ---
        Card(
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(modifier = Modifier.padding(horizontal = 14.dp, vertical = 12.dp)) {
                Text(
                    text = event.title,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = event.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 4.dp)
                )
                Row(
                    modifier = Modifier.padding(top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .clip(CircleShape)
                            .background(AccentPurple1)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = event.date,
                        style = MaterialTheme.typography.labelSmall,
                        color = AccentPurple1,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

// --- Sección Documentos ---
@Composable
private fun DocumentSection(documents: List<OfficialDocument>) {
    val uriHandler = LocalUriHandler.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        documents.forEach { document ->
            DocumentCard(
                title = document.title,
                icon = "📄",
                onClick = {
                    document.documentUrl?.let { url ->
                        try {
                            uriHandler.openUri(url)
                        } catch (e: Exception) {
                            println("Error al abrir URL del documento: $e")
                        }
                    }
                }
            )
        }
    }
}

// --- Tarjeta de Documento ---
@Composable
private fun DocumentCard(
    title: String,
    icon: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick,
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(LightPurple1),
                contentAlignment = Alignment.Center
            ) {
                Text(text = icon, fontSize = 18.sp)
            }
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = Icons.Default.Download,
                contentDescription = "Abrir/Descargar",
                tint = PrimaryPurple1,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

// --- Sección Involucrados ---
@Composable
private fun InvolvedPartySection(parties: List<InvolvedParty>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        parties.forEach { party ->
            InvolvedPartyCard(
                name = party.name,
                role = party.role
            )
        }
    }
}

// --- Tarjeta de Involucrado ---
@Composable
private fun InvolvedPartyCard(
    name: String,
    role: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(LightPurple1),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint = PrimaryPurple1,
                    modifier = Modifier.size(18.dp)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = name,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = role,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

// --- Componente TopBar ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopBar(
    title: String,
    onBackClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onSurface, // Color de texto principal
                letterSpacing = 0.5.sp
            )
        },
        navigationIcon = {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.size(48.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(LightPurple1),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        // Usar AutoMirrored para que la flecha se adapte a RTL
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Volver",
                        tint = PrimaryPurple1,
                        modifier = Modifier.size(22.dp)
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White, // Fondo blanco para la TopBar
            titleContentColor = MaterialTheme.colorScheme.onSurface // Color del título
        )
    )
}