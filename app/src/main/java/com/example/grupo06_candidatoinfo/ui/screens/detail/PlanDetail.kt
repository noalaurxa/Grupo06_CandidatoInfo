package com.example.grupo06_candidatoinfo.ui.screens.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun PlanDetail(
    navController: NavController,
    documentId: String? // Recibe el ID del documento/plan (aunque para el plan podría ser fijo)
) {
    Scaffold(
        // Aquí podrías añadir un TopAppBar con botón de regreso
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Plan de Gobierno: Detalle")
            if (documentId != null) {
                Text(text = "Cargando plan con ID: $documentId")
            } else {
                Text(text = "Error: ID de plan no encontrado.")
            }


        }
    }
}