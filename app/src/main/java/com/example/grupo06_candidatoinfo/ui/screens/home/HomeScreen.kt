@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.grupo06_candidatoinfo.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.grupo06_candidatoinfo.data.repository.MockDataRepository
import com.example.grupo06_candidatoinfo.model.Candidate

@Composable
fun HomeScreen(navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedPosition by remember { mutableStateOf("Todos") }
    var selectedParty by remember { mutableStateOf("Todos") }

    val candidates = remember { MockDataRepository.getCandidates() }
    val positions = remember { MockDataRepository.getPositions() }
    val parties = remember { MockDataRepository.getPoliticalParties() }
    val electionTypes = remember { MockDataRepository.getElectionTypes() }
    var selectedElection by remember { mutableStateOf(electionTypes.first().name) }

    var selectedCandidates by remember { mutableStateOf<Set<Int>>(emptySet()) }
    val selectedCount = selectedCandidates.size

    val filteredCandidates = candidates.filter { candidate ->
        val matchesSearch = candidate.name.contains(searchQuery, ignoreCase = true)
        val matchesPosition = selectedPosition == "Todos" || candidate.position == selectedPosition
        val matchesParty = selectedParty == "Todos" || candidate.party == selectedParty
        matchesSearch && matchesPosition && matchesParty
    }

    Scaffold(containerColor = Color(0xFF3C3472)) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Header
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF3C3472))
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "CandidatoInfo",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(16.dp))

                Surface(
                    color = Color.White,
                    shape = RoundedCornerShape(12.dp),
                    shadowElevation = 4.dp
                ) {
                    DropdownSelector(
                        value = selectedElection,
                        options = electionTypes.map { it.name },
                        onValueChange = { selectedElection = it }
                    )
                }
            }

            // Main Content
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
                color = Color.White
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        OutlinedTextField(
                            value = searchQuery,
                            onValueChange = { searchQuery = it },
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = { Text("Buscar candidato", color = Color.Gray) },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = "Buscar",
                                    tint = Color.Gray
                                )
                            },
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedBorderColor = Color.LightGray,
                                focusedBorderColor = Color(0xFF3C3472)
                            )
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Cargo Electoral",
                            fontSize = 14.sp,
                            color = Color.Gray,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                        DropdownSelector(
                            value = selectedPosition,
                            options = positions,
                            onValueChange = { selectedPosition = it }
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Organización Política",
                            fontSize = 14.sp,
                            color = Color.Gray,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                        DropdownSelector(
                            value = selectedParty,
                            options = parties,
                            onValueChange = { selectedParty = it }
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                            contentPadding = PaddingValues(bottom = 80.dp)
                        ) {
                            items(filteredCandidates, key = { it.id }) { candidate ->
                                val isSelected = selectedCandidates.contains(candidate.id)
                                val canSelectMore = selectedCount < 2

                                CandidateCard(
                                    candidate = candidate,
                                    isSelected = isSelected,
                                    isCheckboxEnabled = isSelected || canSelectMore,
                                    onCheckedChange = { newCheckedState ->
                                        selectedCandidates = if (newCheckedState) {
                                            if (canSelectMore) selectedCandidates + candidate.id else selectedCandidates
                                        } else {
                                            selectedCandidates - candidate.id
                                        }
                                    },
                                    onClick = {
                                        navController.navigate("profile/${candidate.id}")
                                    }
                                )
                            }
                        }
                    }

                    // Botón de comparar
                    if (selectedCount > 0) {
                        Button(
                            onClick = {
                                if (selectedCount == 2) {
                                    val ids = selectedCandidates.joinToString(",")
                                    navController.navigate("compare?ids=$ids")
                                }
                            },
                            enabled = selectedCount == 2,
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(16.dp)
                                .fillMaxWidth()
                                .height(56.dp),
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF3C3472),
                                disabledContainerColor = Color(0xFF3C3472).copy(alpha = 0.5f),
                                disabledContentColor = Color.White.copy(alpha = 0.7f)
                            )
                        ) {
                            Text(
                                text = "COMPARAR ($selectedCount/2)",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DropdownSelector(
    value: String,
    options: List<String>,
    onValueChange: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = {},
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Abrir lista"
                )
            },
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.LightGray,
                focusedBorderColor = Color(0xFF3C3472)
            )
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onValueChange(option)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun CandidateCard(
    candidate: Candidate,
    isSelected: Boolean,
    isCheckboxEnabled: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = candidate.photoUrl,
                contentDescription = candidate.name,
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = candidate.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = candidate.party,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(4.dp))
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = Color(0xFF3C3472)
                ) {
                    Text(
                        text = candidate.position,
                        color = Color.White,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                    )
                }
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.height(64.dp)
            ) {
                Checkbox(
                    checked = isSelected,
                    onCheckedChange = onCheckedChange,
                    enabled = isCheckboxEnabled
                )

                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Ver más",
                    tint = Color.Black
                )
            }
        }
    }
}
