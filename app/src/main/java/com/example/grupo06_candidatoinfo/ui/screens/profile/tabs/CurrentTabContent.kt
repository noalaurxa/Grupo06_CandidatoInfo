package com.example.grupo06_candidatoinfo.ui.screens.profile.tabs

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed //para la línea de tiempo
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Article // Para noticias/documentos
import androidx.compose.material.icons.filled.Campaign // Para actividad
import androidx.compose.material.icons.filled.CheckCircle // Para verificado
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.RssFeed // Fallback
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.grupo06_candidatoinfo.model.CurrentEventItem
import com.example.grupo06_candidatoinfo.model.CurrentEvents
import com.example.grupo06_candidatoinfo.ui.theme.ProfileLightPurpleBackground
import com.example.grupo06_candidatoinfo.ui.theme.ProfileMainPurple
import com.example.grupo06_candidatoinfo.ui.theme.TimelineColor

import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter


// ==================== TAB ACTUALIDAD PRINCIPAL ====================
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CurrentTabContent(
    currentEvents: CurrentEvents?, // RECIBE LOS DATOS
    onNewsClick: (documentId: String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Título de la sección
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Article, // ICONO
                contentDescription = "Actualidad",
                tint = ProfileMainPurple,
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = "Noticias y Actividades", // TITULO
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = ProfileMainPurple
            )
        }

        Divider(modifier = Modifier.padding(bottom = 8.dp))

        // Listado de Noticias del Modelo
        if (currentEvents == null || currentEvents.items.isEmpty()) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Sin actividad reciente registrada.",
                        color = Color.Gray,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        } else {
            // LazyColumn CON LÍNEA DE TIEMPO
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 800.dp) // Limita la altura
            ) {
                itemsIndexed(currentEvents.items) { index, item ->
                    val isFirst = index == 0
                    val isLast = index == currentEvents.items.lastIndex

                    TimelineNode( // El Composable para la línea de tiempo
                        isFirst = isFirst,
                        isLast = isLast,
                        item = item,
                        onNewsClick = { onNewsClick(item.id.toString()) }
                    )
                }
            }
        }

    }
}

// ==================== NODO DE LA LÍNEA DE TIEMPO ====================
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TimelineNode(
    isFirst: Boolean,
    isLast: Boolean,
    item: CurrentEventItem,
    onNewsClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min) // ASEGURA QUE EL CARD TENGA LA MISMA ALTURA
    ) {
        // 1. Stepper (Punto y Línea)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .clip(CircleShape)
                    .background(if (isFirst) ProfileMainPurple else TimelineColor)
            )
            if (!isLast) {
                Spacer(modifier = Modifier.height(4.dp))
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .fillMaxHeight() // Se expande a la altura de la Card
                        .background(TimelineColor)
                )
            }
        }

        // 2. Card de Noticia
        NewsItemCard(
            item = item,
            onNewsClick = onNewsClick,
            modifier = Modifier.padding(bottom = 16.dp)
        )
    }
}


// ==================== TARJETA INDIVIDUAL DE NOTICIA ====================
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NewsItemCard(
    item: CurrentEventItem,
    onNewsClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onNewsClick() }, // Ejecuta el lambda
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.Top, // Alinea icono y texto arriba
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Icono basado en el tipo
            val icon = when (item.type) {
                "noticia" -> Icons.Default.Article
                "actividad" -> Icons.Default.Campaign
                "documento" -> Icons.Default.Article
                else -> Icons.Default.RssFeed
            }
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(ProfileLightPurpleBackground),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = item.type,
                    tint = ProfileMainPurple
                )
            }

            // Contenido principal
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                // Fila de Fecha y Fuente
                Row {
                    Text(
                        text = getRelativeTime(item.date), // Usa el helper de fecha
                        fontSize = 12.sp,
                        color = ProfileMainPurple,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = " • ${item.source}",
                        fontSize = 12.sp,
                        color = Color.Gray,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                // Titular
                Text(
                    text = item.title,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp,
                    color = Color.Black,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                // Descripción
                Text(
                    text = item.description,
                    fontWeight = FontWeight.Normal,
                    fontSize = 13.sp,
                    color = Color(0xFF555555),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 18.sp
                )

                // Etiqueta de Verificación
                if (item.isVerified) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = "Verificado",
                            tint = Color(0xFF006400), // Verde oscuro
                            modifier = Modifier.size(14.dp)
                        )
                        Text(
                            text = "Información Verificada JNE",
                            fontSize = 11.sp,
                            color = Color(0xFF006400),
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }

            // Icono de Navegación
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = "Ver Noticia",
                tint = ProfileMainPurple,
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.CenterVertically) // Centra el chevron verticalmente
            )
        }
    }
}



// ==================== HELPER DE FECHA ====================
@RequiresApi(Build.VERSION_CODES.O)
private fun getRelativeTime(dateString: String): String {
    return try {
        val formatter = DateTimeFormatter.ISO_LOCAL_DATE
        val date = LocalDate.parse(dateString, formatter)
        val today = LocalDate.now()

        val period = Period.between(date, today)

        when {
            period.isZero -> "Hoy"
            period.days == 1 -> "Ayer"
            period.days < 7 -> "Hace ${period.days} días"
            period.days < 14 -> "Hace 1 semana"
            period.days < 30 -> "Hace ${period.days / 7} semanas"
            period.months == 1 -> "Hace 1 mes"
            period.months < 12 -> "Hace ${period.months} meses"
            period.years == 1 -> "Hace 1 año"
            period.years > 1 -> "Hace ${period.years} años"
            else -> dateString
        }
    } catch (e: Exception) {
        dateString
    }
}