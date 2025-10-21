package com.example.grupo06_candidatoinfo.ui.screens.profile.tabs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material.icons.filled.RssFeed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.grupo06_candidatoinfo.ui.theme.ProfileMainPurple

// Modelo de datos simulado para las noticias (temporal)
data class MockNewsItem(
    val id: Int,
    val title: String,
    val date: String,
    val source: String,
    val url: String,
    val documentId: String // Usaremos este ID para la navegación
)

// Datos simulados para 3 noticias
val mockNewsList = listOf(
    MockNewsItem(
        id = 1,
        title = "Candidato Lanza Propuesta Clave de Infraestructura para el Sur del País",
        date = "18 de Octubre, 2025",
        source = "Prensa Oficial",
        url = "https://www.ejemplo.com/noticia/1",
        documentId = "news_infraestructura" // ID para la pantalla Detail
    ),
    MockNewsItem(
        id = 2,
        title = "Análisis: Impacto del Plan Económico del Partido en la Microempresa",
        date = "16 de Octubre, 2025",
        source = "Diario El País",
        url = "https://www.ejemplo.com/noticia/2",
        documentId = "news_economia" // ID para la pantalla Detail
    ),
    MockNewsItem(
        id = 3,
        title = "Debate Político: Reacciones a la postura sobre Seguridad Ciudadana",
        date = "15 de Octubre, 2025",
        source = "Noticias 24/7",
        url = "https://www.ejemplo.com/noticia/3",
        documentId = "news_seguridad" // ID para la pantalla Detail
    )
)

// ==================== TAB ACTUALIDAD PRINCIPAL ====================
@Composable
fun CurrentTabContent(
    onNewsClick: (documentId: String) -> Unit // NUEVO: Función para navegar
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Título de la sección
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.RssFeed,
                contentDescription = "Actualidad",
                tint = ProfileMainPurple,
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = "Últimas Noticias (Feed)",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = ProfileMainPurple
            )
        }

        Divider(modifier = Modifier.padding(bottom = 8.dp))

        // Listado de las 3 Noticias Simuladas
        mockNewsList.forEach { newsItem ->
            NewsItemCard(
                item = newsItem,
                onNewsClick = onNewsClick // Le pasamos la función de navegación
            )
        }

        // Botón de Acción para "Ver Más" (Sigue derivando al navegador externo)
        Spacer(modifier = Modifier.height(8.dp))
        FullNewsButton()
    }
}

// ==================== TARJETA INDIVIDUAL DE NOTICIA ====================
@Composable
fun NewsItemCard(
    item: MockNewsItem,
    onNewsClick: (documentId: String) -> Unit // RECIBE la función de navegación
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            // Al hacer clic, ejecuta la función de navegación con el documentId
            .clickable { onNewsClick(item.documentId) },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                // Fuente y Fecha
                Text(
                    text = "${item.source} • ${item.date}",
                    fontSize = 11.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(4.dp))
                // Titular
                Text(
                    text = item.title,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    color = Color.Black,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            // Icono de Navegación
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = "Ver Noticia",
                tint = ProfileMainPurple,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

// ... (El composable FullNewsButton se mantiene igual, ya que deriva a una web)
@Composable
fun FullNewsButton() {
    val uriHandler = LocalUriHandler.current

    Button(
        onClick = {
            uriHandler.openUri("https://www.ejemplo.com/noticias-del-candidato")
        },
        colors = ButtonDefaults.buttonColors(containerColor = ProfileMainPurple),
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            imageVector = Icons.Default.OpenInNew,
            contentDescription = "Ver Más",
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "Ir al Portal de Noticias", color = Color.White, fontWeight = FontWeight.SemiBold)
    }
}