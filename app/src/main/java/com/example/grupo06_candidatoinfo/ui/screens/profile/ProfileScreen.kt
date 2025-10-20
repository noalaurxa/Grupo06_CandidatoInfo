package com.example.grupo06_candidatoinfo.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.grupo06_candidatoinfo.data.repository.MockDataRepository
import com.example.grupo06_candidatoinfo.ui.theme.ProfileLightGrayBackground
import com.example.grupo06_candidatoinfo.ui.theme.ProfileMainPurple
// IMPORTS de la carpeta 'tabs'
import com.example.grupo06_candidatoinfo.ui.screens.profile.tabs.CareerTabContent
import com.example.grupo06_candidatoinfo.ui.screens.profile.tabs.GeneralTabContent
import com.example.grupo06_candidatoinfo.ui.screens.profile.tabs.BackgroundTabContent
import com.example.grupo06_candidatoinfo.ui.screens.profile.tabs.CurrentTabContent

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
                                    // Sigue usando PlaceholderTabContent aquí o crea un composable específico
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
                            2 -> BackgroundTabContent()
                            3 -> CurrentTabContent()
                        }
                    }
                }
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