package com.example.grupo06_candidatoinfo.ui.screens.profile.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BusinessCenter
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.grupo06_candidatoinfo.model.CareerHistory
import com.example.grupo06_candidatoinfo.model.CareerItem

// Importar colores
import com.example.grupo06_candidatoinfo.ui.theme.CardBackgroundColor
import com.example.grupo06_candidatoinfo.ui.theme.TimelineLineColor
import com.example.grupo06_candidatoinfo.ui.theme.IconTintColor
import com.example.grupo06_candidatoinfo.ui.theme.PrimaryTextColor
import com.example.grupo06_candidatoinfo.ui.theme.IconBackgroundColor
import com.example.grupo06_candidatoinfo.ui.theme.SecondaryTextColor
import com.example.grupo06_candidatoinfo.ui.theme.TagBackgroundColor
import com.example.grupo06_candidatoinfo.ui.theme.TagTextColor

// ==================== TAB TRAYECTORIA ====================
@Composable
fun CareerTabContent(careerHistory: CareerHistory) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 800.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        items(careerHistory.items.size) { index ->
            CareerItemCard(
                careerItem = careerHistory.items[index],
                esUltimoItem = index == careerHistory.items.size - 1,
                esPrimerItem = index == 0
            )
        }
    }
}

// ==================== ITEM DE TRAYECTORIA ====================
@Composable
fun CareerItemCard(careerItem: CareerItem, esUltimoItem: Boolean, esPrimerItem: Boolean = false) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            // Columna de Timeline (Icono + Línea DENTRO de la card)
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.width(40.dp)
            ) {
                // Icono minimalista
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(IconBackgroundColor),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.BusinessCenter,
                        contentDescription = "Cargo",
                        tint = IconTintColor,
                        modifier = Modifier.size(20.dp)
                    )
                }

                // Línea sutil debajo del icono
                Box(
                    modifier = Modifier
                        .width(3.dp)
                        .height(45.dp)
                        .background(TimelineLineColor)
                )
            }

            // Contenido del cargo
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Título del cargo
                Text(
                    text = careerItem.position,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = PrimaryTextColor,
                    lineHeight = 21.sp
                )

                // Tag de período minimalista
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = TagBackgroundColor
                ) {
                    Text(
                        text = careerItem.period,
                        color = TagTextColor,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                    )
                }

                // Descripción
                Text(
                    text = careerItem.description,
                    fontSize = 13.sp,
                    color = SecondaryTextColor,
                    lineHeight = 19.sp
                )
            }
        }
    }
}