@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.grupo06_candidatoinfo.ui.screens.home

// --- AÑADE ESTAS IMPORTACIONES ---
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import com.example.grupo06_candidatoinfo.R
// ---

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.grupo06_candidatoinfo.data.repository.MockDataRepository
import com.example.grupo06_candidatoinfo.model.Candidate
import java.text.Normalizer

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

    // --- ESTADO PARA EL MENÚ DE ELECCIÓN ---
    var electionMenuExpanded by remember { mutableStateOf(false) }
    val electionTypeOptions = remember { electionTypes.map { it.name } }
    // ---

    var selectedCandidates by remember { mutableStateOf<Set<Int>>(emptySet()) }
    val selectedCount = selectedCandidates.size

    val filteredCandidates = candidates.filter { candidate ->
        val normalizedCandidateName = Normalizer.normalize(candidate.name, Normalizer.Form.NFD)
            .replace("\\p{InCombiningDiacriticalMarks}+".toRegex(), "")
        val normalizedSearchQuery = Normalizer.normalize(searchQuery, Normalizer.Form.NFD)
            .replace("\\p{InCombiningDiacriticalMarks}+".toRegex(), "")

        val matchesSearch = normalizedCandidateName.contains(normalizedSearchQuery, ignoreCase = true)
        val matchesPosition = selectedPosition == "Todos" || candidate.position == selectedPosition
        val matchesParty = selectedParty == "Todos" || candidate.party == selectedParty
        matchesSearch && matchesPosition && matchesParty
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                // --- FONDO GRIS OSCURO ---
                .background(color = Color(0xFF4A4A4A))
        ) {
            // Header
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    // --- USA EL FONDO GRIS DEL PADRE ---
                    .padding(horizontal = 20.dp, vertical = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // --- LOGO HORIZONTAL ---
                Image(
                    painter = painterResource(id = R.drawable.logo_enc),
                    contentDescription = "Voto Informado Logo",
                    modifier = Modifier
                        .height(90.dp)
                        .padding(top = 15.dp),
                )

                Spacer(modifier = Modifier.height(15.dp))

                // --- INICIO SELECTOR DE ELECCIÓN INTERACTIVO ---
                ExposedDropdownMenuBox(
                    expanded = electionMenuExpanded,
                    onExpandedChange = { electionMenuExpanded = !it },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // El "botón" blanco
                    Surface(
                        color = Color.White,
                        shape = RoundedCornerShape(16.dp),
                        shadowElevation = 8.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor()
                            .clickable { electionMenuExpanded = true }
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = selectedElection,
                                    fontSize = 18.sp,
                                    color = Color(0xFF3C3472),
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center
                                )
                                Icon(
                                    imageVector = Icons.Default.ArrowDropDown,
                                    contentDescription = "Cambiar elección",
                                    tint = Color(0xFF3C3472),
                                    modifier = Modifier.padding(start = 8.dp)
                                )
                            }
                        }
                    }

                    // El menú desplegable
                    ExposedDropdownMenu(
                        expanded = electionMenuExpanded,
                        onDismissRequest = { electionMenuExpanded = false },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFF6F3FA)) // Fondo lila
                            .clip(RoundedCornerShape(12.dp))
                    ) {
                        electionTypeOptions.forEach { option ->
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        option,
                                        fontWeight = if (option == selectedElection) FontWeight.Bold else FontWeight.Normal,
                                        color = if (option == selectedElection) Color(0xFF3C3472) else Color(0xFF333333),
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                },
                                onClick = {
                                    selectedElection = option
                                    electionMenuExpanded = false
                                },
                                modifier = Modifier.fillMaxWidth(),
                                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 14.dp)
                            )
                        }
                    }
                }
                // --- FIN SELECTOR DE ELECCIÓN ---
            }

            // Main Content
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
                color = Color(0xFFF8F9FA)
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp)
                    ) {
                        // Búsqueda minimalista
                        OutlinedTextField(
                            value = searchQuery,
                            onValueChange = { searchQuery = it },
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = {
                                Text(
                                    "Buscar por nombre",
                                    color = Color(0xFF999999)
                                )
                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = null,
                                    tint = Color(0xFF3C3472)
                                )
                            },
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedBorderColor = Color(0xFFE0E0E0),
                                focusedBorderColor = Color(0xFF3C3472),
                                unfocusedContainerColor = Color.White,
                                focusedContainerColor = Color.White
                            ),
                            singleLine = true
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Filtros en fila
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Box(modifier = Modifier.weight(1f)) {
                                DropdownSelector( // <-- ESTE YA ESTÁ ACTUALIZADO
                                    label = "Cargo",
                                    value = selectedPosition,
                                    options = positions,
                                    onValueChange = { selectedPosition = it }
                                )
                            }

                            Box(modifier = Modifier.weight(1f)) {
                                DropdownSelector( // <-- ESTE YA ESTÁ ACTUALIZADO
                                    label = "Partido",
                                    value = selectedParty,
                                    options = parties,
                                    onValueChange = { selectedParty = it }
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        // Contador de resultados
                        Text(
                            text = "${filteredCandidates.size} candidatos encontrados",
                            fontSize = 14.sp,
                            color = Color.Gray,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )

                        // Lista de candidatos con cards mejoradas
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                            contentPadding = PaddingValues(bottom = if (selectedCount > 0) 88.dp else 16.dp)
                        ) {
                            items(filteredCandidates, key = { it.id }) { candidate ->
                                val isSelected = selectedCandidates.contains(candidate.id)
                                val canSelectMore = selectedCount < 2

                                CandidateCard(
                                    candidate = candidate,
                                    isSelected = isSelected,
                                    canSelect = isSelected || canSelectMore,
                                    onSelectToggle = {
                                        selectedCandidates = if (isSelected) {
                                            selectedCandidates - candidate.id
                                        } else if (canSelectMore) {
                                            selectedCandidates + candidate.id
                                        } else {
                                            selectedCandidates
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
                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .fillMaxWidth()
                                .background(
                                    Color.White.copy(alpha = 0.95f)
                                )
                                .padding(16.dp)
                        ) {
                            Button(
                                onClick = {
                                    if (selectedCount == 2) {
                                        val ids = selectedCandidates.joinToString(",")
                                        navController.navigate("compare?ids=$ids")
                                    }
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(56.dp),
                                enabled = selectedCount == 2,
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF3C3472),
                                    disabledContainerColor = Color(0xFFE0E0E0),
                                    disabledContentColor = Color(0xFF999999)
                                ),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Text(
                                    text = if (selectedCount == 2)
                                        "Comparar candidatos"
                                    else
                                        "Selecciona $selectedCount de 2",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

// --- FUNCIÓN DropdownSelector ACTUALIZADA ---
@Composable
fun DropdownSelector(
    label: String,
    value: String,
    options: List<String>,
    onValueChange: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    // Colores del diseño
    val VotoPurple = Color(0xFF3C3472)
    val VotoMenuBg = Color(0xFFF6F3FA) // Fondo lila
    val VotoTextDark = Color(0xFF333333)

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            value = if (value == "Todos") label else value,
            onValueChange = {},
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(),
            textStyle = LocalTextStyle.current.copy(
                fontSize = 14.sp,
                fontWeight = if (value != "Todos") FontWeight.SemiBold else FontWeight.Normal,
                color = VotoTextDark // Texto oscuro
            ),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    tint = VotoPurple,
                    modifier = Modifier.size(20.dp)
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = VotoPurple, // Borde púrpura
                focusedBorderColor = VotoPurple,
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White
            ),
            shape = RoundedCornerShape(12.dp),
            singleLine = true
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .background(VotoMenuBg) // Fondo lila
                .clip(RoundedCornerShape(12.dp))
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = {
                        Text(
                            option,
                            fontWeight = if (option == value) FontWeight.Bold else FontWeight.Normal,
                            color = if (option == value) VotoPurple else VotoTextDark
                        )
                    },
                    onClick = {
                        onValueChange(option)
                        expanded = false
                    },
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp) // Más padding
                )
            }
        }
    }
}
// --- FIN FUNCIÓN ACTUALIZADA ---

@Composable
fun CandidateCard(
    candidate: Candidate,
    isSelected: Boolean,
    canSelect: Boolean,
    onSelectToggle: () -> Unit,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        color = if (isSelected) Color(0xFFF0EBFF) else Color.White,
        shadowElevation = if (isSelected) 8.dp else 4.dp,
        border = if (isSelected)
            androidx.compose.foundation.BorderStroke(2.dp, Color(0xFF3C3472))
        else
            null
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Foto con sombra
            Surface(
                shape = CircleShape,
                shadowElevation = 4.dp,
                modifier = Modifier.size(72.dp)
            ) {
                AsyncImage(
                    model = candidate.photoUrl,
                    contentDescription = candidate.name,
                    modifier = Modifier
                        .size(72.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Información del candidato
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = candidate.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = candidate.party,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(8.dp))
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = Color(0xFF3C3472)
                ) {
                    Text(
                        text = candidate.position,
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Selector circular (estilo minimalista)
            IconButton(
                onClick = onSelectToggle,
                enabled = canSelect
            ) {
                Icon(
                    imageVector = if (isSelected)
                        Icons.Filled.CheckCircle
                    else
                        Icons.Outlined.Circle,
                    contentDescription = if (isSelected) "Deseleccionar" else "Seleccionar",
                    tint = if (isSelected)
                        Color(0xFF3C3472)
                    else if (canSelect)
                        Color(0xFFBDBDBD)
                    else
                        Color(0xFFE0E0E0),
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }
}