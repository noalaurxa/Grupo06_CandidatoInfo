package com.example.grupo06_candidatoinfo.ui.screens.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Description
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.grupo06_candidatoinfo.ui.theme.ProfileLightGrayBackground
import com.example.grupo06_candidatoinfo.ui.theme.ProfileMainPurple

// ==================== MOCK DATA TEMPORAL ====================
data class DocumentDetail(
    val title: String,
    val subtitle: String,
    val content: String,
    val type: String
)

fun getMockDocument(documentId: String?): DocumentDetail {
    return when (documentId) {
        "news_infraestructura" -> DocumentDetail(
            title = "Plan Nacional de Conectividad (Detalle)",
            subtitle = "Propuesta de Infraestructura y Transporte para el Sur",
            content = "El Plan Nacional de Conectividad, anunciado en Arequipa, proyecta una inversión de 5,000 millones de soles para la construcción de una red vial interconectada y la modernización de cuatro puertos clave. El objetivo es reducir los costos logísticos en un 25% en los próximos 5 años.\n\nEl candidato enfatizó que 'la infraestructura no es un gasto, es la base de la competitividad y la justicia social. No puede ser que un agricultor demore más de un día en llevar sus productos al mercado principal'.\n\nDetalles del proyecto incluyen:\n\n1. Ampliación de la Carretera Interoceánica.\n2. Concesión de la Red de Fibra Óptica Rural (RFOC).\n3. Creación de un fondo de emergencia para mantenimiento vial.",
            type = "Noticia de Campaña"
        )
        "news_economia" -> DocumentDetail(
            title = "Detalle del Plan Económico",
            subtitle = "Foco en Micro y Pequeña Empresa (MYPE)",
            content = "El eje central es la simplificación tributaria y la línea de crédito subsidiada para negocios emergentes. Se eliminará la tasa de IGV por tres años a toda MYPE de nueva creación que demuestre tener menos de 10 empleados.",
            type = "Noticia de Campaña"
        )
        "plan_gobierno_1" -> DocumentDetail(
            title = "Plan de Gobierno 2026-2031 (Documento Oficial)",
            subtitle = "Resumen Ejecutivo de la Plataforma Electoral",
            content = "El documento oficial del Plan de Gobierno, registrado ante el Jurado Nacional de Elecciones (JNE), consta de 8 capítulos principales. Los pilares son: Lucha Anticorrupción, Reactivación Económica y Salud Universal. El costo total estimado del plan es de 15,000 millones de soles.",
            type = "Plan de Gobierno"
        )
        "sunedu_1" -> DocumentDetail(
            title = "Registro de Títulos Universitarios (Detalle)",
            subtitle = "Título: Licenciado en Administración de Empresas",
            content = "La Sunedu certifica que el título de Licenciado en Administración de Empresas, otorgado por la Universidad de Lima en 1995, se encuentra debidamente registrado y es válido para ejercer la profesión en el territorio nacional. No presenta observaciones.",
            type = "Registro Académico"
        )
        else -> DocumentDetail(
            title = "Documento No Encontrado",
            subtitle = "ID: $documentId",
            content = "Lo sentimos, el ID del documento solicitado no existe o no ha sido cargado en el sistema.",
            type = "Error"
        )
    }
}

// ==================== SCREEN PRINCIPAL ====================
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navController: NavController,
    documentId: String? = null
) {
    val document = getMockDocument(documentId)

    Scaffold(
        containerColor = ProfileLightGrayBackground,
        topBar = {
            TopAppBar(
                title = { Text(document.type, fontWeight = FontWeight.SemiBold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Volver")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = ProfileMainPurple,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            item {
                // Tarjeta de Título
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Icon(
                            imageVector = Icons.Default.Description,
                            contentDescription = "Documento",
                            tint = ProfileMainPurple,
                            modifier = Modifier.size(32.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = document.title,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 20.sp,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = document.subtitle,
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                // Contenido del Documento
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Text(
                            text = "CONTENIDO DETALLADO",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = ProfileMainPurple.copy(alpha = 0.8f)
                        )
                        Divider(
                            modifier = Modifier.padding(vertical = 8.dp),
                            color = ProfileLightGrayBackground
                        )
                        Text(
                            text = document.content,
                            fontSize = 15.sp,
                            lineHeight = 22.sp,
                            color = Color(0xFF333333)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }

            item {
                // Botón de Volver (Opcional, ya está en TopBar)
                OutlinedButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = ProfileMainPurple)
                ) {
                    Text("Volver al Perfil")
                }
            }
        }
    }
}