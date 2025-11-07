package com.example.grupo06_candidatoinfo.ui.screens.profile

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import com.example.grupo06_candidatoinfo.data.repository.CandidatoRepository
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.grupo06_candidatoinfo.model.Candidate
import com.example.grupo06_candidatoinfo.navigation.Screen
import com.example.grupo06_candidatoinfo.ui.theme.lightGrayBackground
import com.example.grupo06_candidatoinfo.ui.theme.mainPurple
import com.example.grupo06_candidatoinfo.ui.screens.profile.tabs.CareerTabContent
import com.example.grupo06_candidatoinfo.ui.screens.profile.tabs.GeneralTabContent
import com.example.grupo06_candidatoinfo.ui.screens.profile.tabs.BackgroundTabContent
import com.example.grupo06_candidatoinfo.ui.screens.profile.tabs.CurrentTabContent

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavController,
    candidateId: String?
) {
    val repository = remember { CandidatoRepository() }

    var candidate by remember { mutableStateOf<Candidate?>(null) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(candidateId) {
        if (candidateId != null) {
            try {
                val id = candidateId.toIntOrNull()
                if (id != null) {
                    candidate = repository.getCandidato(id)
                }
                isLoading = false
            } catch (e: Exception) {
                e.printStackTrace()
                isLoading = false
            }
        }
    }

    val profileDetails = candidate?.profileDetails

    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("General", "Trayectoria", "Antecedentes", "Actualidad")

    Scaffold(
        containerColor = lightGrayBackground
    ) { paddingValues ->
        if (isLoading) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        } else if (candidate == null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
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
                item {
                    Box(modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 16.dp)) {
                        ProfileHeader(candidate = candidate!!, onBackClick = {
                            navController.popBackStack()
                        })
                    }
                }

                item {
                    Surface(
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        ScrollableTabRow(
                            selectedTabIndex = selectedTabIndex,
                            containerColor = Color.Transparent,
                            contentColor = mainPurple,
                            edgePadding = 4.dp,
                            indicator = { },
                            divider = { }
                        ) {
                            tabs.forEachIndexed { index, title ->
                                Tab(
                                    selected = selectedTabIndex == index,
                                    onClick = { selectedTabIndex = index },
                                    modifier = Modifier
                                        .padding(horizontal = 4.dp, vertical = 8.dp)
                                        .clip(RoundedCornerShape(16.dp)),
                                    selectedContentColor = Color.White,
                                    unselectedContentColor = Color.Gray
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .background(
                                                if (selectedTabIndex == index) mainPurple else Color.Transparent,
                                                RoundedCornerShape(16.dp)
                                            )
                                            .padding(horizontal = 16.dp, vertical = 8.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
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
                            2 -> {
                                BackgroundTabContent(
                                    backgroundReport = profileDetails?.backgroundReport,
                                    onBackgroundClick = { documentId ->
                                        navController.navigate(Screen.InvestigationDetail.createRoute(documentId))
                                    }
                                )
                            }
                            3 -> {
                                CurrentTabContent(
                                    currentEvents = profileDetails?.currentEvents,
                                    onNewsClick = { documentId ->
                                        navController.navigate(Screen.NewsDetail.createRoute(documentId))
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
private fun PlaceholderTabContent(title: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = title)
    }
}
