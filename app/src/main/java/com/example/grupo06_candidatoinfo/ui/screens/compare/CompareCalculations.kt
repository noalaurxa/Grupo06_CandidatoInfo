package com.example.grupo06_candidatoinfo.ui.screens.compare

import com.example.grupo06_candidatoinfo.model.Candidate

/**
 * Funciones de cálculo para métricas de comparación de candidatos
 */

/**
 * Calcula la puntuación general del candidato (0-100)
 * Promedia: experiencia, educación, propuestas y transparencia (inverso del riesgo)
 */
fun calculateSimpleScore(candidate: Candidate): Int {
    val exp = calculateExperience(candidate)
    val edu = calculateEducation(candidate)
    val prop = calculateProposals(candidate)
    val risk = 100 - calculateRiskScore(candidate)

    return (exp + edu + prop + risk) / 4
}

/**
 * Calcula puntuación de experiencia basada en número de cargos públicos
 * Máximo 100 puntos (5 cargos o más)
 */
fun calculateExperience(candidate: Candidate): Int {
    val positions = candidate.profileDetails?.careerHistory?.items?.size ?: 0
    return (positions * 20).coerceAtMost(100)
}

/**
 * Calcula puntuación de educación basada en el máximo grado académico
 * - Doctorado: 100 puntos
 * - Maestría/Master: 85 puntos
 * - Bachiller/Bachelor: 70 puntos
 * - Sin datos: 50 puntos
 */
fun calculateEducation(candidate: Candidate): Int {
    val degrees = candidate.profileDetails?.academicFormation?.degrees ?: emptyList()

    return when {
        degrees.any { it.title.contains("Doctor", ignoreCase = true) } -> 100
        degrees.any {
            it.title.contains("Maestr", ignoreCase = true) ||
                    it.title.contains("Master", ignoreCase = true)
        } -> 85
        degrees.any {
            it.title.contains("Bachiller", ignoreCase = true) ||
                    it.title.contains("Bachelor", ignoreCase = true)
        } -> 70
        degrees.isNotEmpty() -> 60
        else -> 50
    }
}

/**
 * Calcula puntuación de propuestas basada en cantidad
 * Máximo 100 puntos (4 propuestas o más)
 */
fun calculateProposals(candidate: Candidate): Int {
    val proposals = candidate.profileDetails?.governmentPlan?.proposals?.size ?: 0
    return (proposals * 25).coerceAtMost(100)
}

/**
 * Calcula el nivel de riesgo del candidato (0-100)
 * Basado en:
 * - Casos activos (hasta 60 puntos)
 * - Total de antecedentes (hasta 40 puntos)
 */
fun calculateRiskScore(candidate: Candidate): Int {
    val records = candidate.profileDetails?.backgroundReport?.records ?: emptyList()

    // Contar casos activos
    val activeCount = records.count {
        it.statusTags.any { tag -> tag.equals("Activo", ignoreCase = true) }
    }

    // Contar total de registros
    val totalRecords = records.size

    // Peso mayor a casos activos (más grave)
    val activeScore = (activeCount * 30).coerceAtMost(60)
    val totalScore = (totalRecords * 10).coerceAtMost(40)

    return (activeScore + totalScore).coerceAtMost(100)
}