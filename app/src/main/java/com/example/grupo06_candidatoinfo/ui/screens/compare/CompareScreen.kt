package com.example.grupo06_candidatoinfo.ui.screens.compare

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.grupo06_candidatoinfo.data.repository.MockDataRepository
import com.example.grupo06_candidatoinfo.model.Candidate
import com.example.grupo06_candidatoinfo.ui.screens.compare.components.*
import com.example.grupo06_candidatoinfo.ui.screens.compare.tabs.*
import com.example.grupo06_candidatoinfo.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompareScreen(
    navController: NavController,
    candidateIds: String?
) {
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("Resumen", "Experiencia", "Propuestas", "Transparencia")

    val candidates = remember(candidateIds) {
        loadCandidates(candidateIds)
    }

    Scaffold(
        topBar = {
            CompareTopBar(
                selectedTab = selectedTab,
                tabs = tabs,
                onTabSelected = { selectedTab = it },
                onBackClick = { navController.popBackStack() },
                showTabs = candidates.size == 2
            )
        },
        containerColor = lightGray
    ) { paddingValues ->
        if (candidates.size == 2) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(bottom = 24.dp)
            ) {
                item {
                    ComparisonHeader(candidates[0], candidates[1])
                }

                when (selectedTab) {
                    0 -> item { SummaryTab(candidates[0], candidates[1]) }
                    1 -> item { ExperienceTab(candidates[0], candidates[1]) }
                    2 -> item { ProposalsTab(candidates[0], candidates[1]) }
                    3 -> item { TransparencyTab(candidates[0], candidates[1]) }
                }
            }
        } else {
            ErrorScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CompareTopBar(
    selectedTab: Int,
    tabs: List<String>,
    onTabSelected: (Int) -> Unit,
    onBackClick: () -> Unit,
    showTabs: Boolean
) {
    Column {
        TopAppBar(
            title = { Text("Comparación Detallada", fontWeight = FontWeight.Bold) },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = primaryPurple,
                titleContentColor = Color.White,
                navigationIconContentColor = Color.White
            )
        )

        if (showTabs) {
            TabRow(
                selectedTabIndex = selectedTab,
                containerColor = primaryPurple,
                contentColor = Color.White,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                        color = Color.White,
                        height = 3.dp
                    )
                }
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { onTabSelected(index) },
                        text = {
                            Text(
                                title,
                                fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal
                            )
                        }
                    )
                }
            }
        }
    }
}

private fun loadCandidates(candidateIds: String?): List<Candidate> {
    val ids = candidateIds?.split(',')?.mapNotNull { it.toIntOrNull() } ?: emptyList()

    if (ids.size != 2) return emptyList()

    val candidate1 = MockDataRepository.getCandidates().find { it.id == ids[0] }
    val candidate2 = MockDataRepository.getCandidates().find { it.id == ids[1] }

    return if (candidate1 != null && candidate2 != null) {
        listOf(candidate1, candidate2)
    } else {
        emptyList()
    }
}