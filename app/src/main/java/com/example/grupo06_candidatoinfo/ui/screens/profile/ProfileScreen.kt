package com.example.grupo06_candidatoinfo.ui.screens.profile

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.grupo06_candidatoinfo.data.repository.MockDataRepository
import com.example.grupo06_candidatoinfo.navigation.Screen
import com.example.grupo06_candidatoinfo.ui.screens.profile.tabs.CareerTabContent
import com.example.grupo06_candidatoinfo.ui.screens.profile.tabs.GeneralTabContent
import com.example.grupo06_candidatoinfo.ui.screens.profile.tabs.BackgroundTabContent
import com.example.grupo06_candidatoinfo.ui.screens.profile.tabs.CurrentTabContent
import com.example.grupo06_candidatoinfo.ui.theme.lightGrayBackground
import com.example.grupo06_candidatoinfo.ui.theme.mainPurple
import com.example.grupo06_candidatoinfo.ui.viewmodel.VoteViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavController,
    candidateId: String?,
    voteViewModel: VoteViewModel = viewModel()
) {
    val candidate = remember(candidateId) {
        MockDataRepository.getCandidates().find {
            it.id == candidateId?.toIntOrNull()
        }
    }
    val profileDetails = candidate?.profileDetails

    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("General", "Trayectoria", "Antecedentes", "Actualidad")

    var showVoteDialog by remember { mutableStateOf(false) }
    val hasVoted = voteViewModel.hasVoted(candidate?.id ?: -1).collectAsState(initial = false)
    val context = LocalContext.current

    if (showVoteDialog) {
        AlertDialog(
            onDismissRequest = { showVoteDialog = false },
            title = { Text("Confirmar Voto") },
            text = { Text("¿Votarías por este candidato?") },
            confirmButton = {
                Button(onClick = {
                    candidate?.id?.let { voteViewModel.vote(it) }
                    showVoteDialog = false
                    Toast.makeText(context, "Tu voto ha sido registrado", Toast.LENGTH_SHORT).show()
                }) {
                    Text("Sí")
                }
            },
            dismissButton = {
                Button(onClick = { showVoteDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }

    Scaffold(
        containerColor = lightGrayBackground
    ) { paddingValues ->
        if (candidate == null) {
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
                        ProfileHeader(
                            candidate = candidate,
                            onBackClick = { navController.popBackStack() },
                            onVoteClick = { showVoteDialog = true },
                            onRankingClick = { navController.navigate(Screen.Ranking.route) }, // <-- AÑADIDO
                            hasVoted = hasVoted.value
                        )
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
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
        }
    }
}
