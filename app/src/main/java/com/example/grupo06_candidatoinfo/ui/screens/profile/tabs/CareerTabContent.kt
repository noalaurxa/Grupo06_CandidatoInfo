package com.example.grupo06_candidatoinfo.ui.screens.profile.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BusinessCenter
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.grupo06_candidatoinfo.model.CareerHistory
import com.example.grupo06_candidatoinfo.model.CareerItem
import com.example.grupo06_candidatoinfo.ui.theme.ProfileLightPurpleBackground
import com.example.grupo06_candidatoinfo.ui.theme.ProfileMainPurple

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