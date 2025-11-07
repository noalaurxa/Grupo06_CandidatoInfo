package com.example.grupo06_candidatoinfo.data.local

// --------------------------------------------------------------------
// --- FILTROS ---
// --------------------------------------------------------------------
fun getPositions(): List<String> {
    return listOf("Todos", "Presidencia", "Congreso", "Alcaldía", "Gobernador")
}

fun getPoliticalParties(): List<String> {
    return listOf(
        "Todos",
        "Renovación Popular",
        "Fuerza Popular",
        "Alianza para el Progreso",
        "País para Todos",
        "Perú Primero"
    )
}
