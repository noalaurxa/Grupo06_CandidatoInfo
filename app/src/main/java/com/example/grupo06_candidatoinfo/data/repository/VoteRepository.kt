package com.example.grupo06_candidatoinfo.data.repository

import com.example.grupo06_candidatoinfo.model.Candidate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map

object VoteRepository {

    // Mapa para almacenar votos: CandidateID -> VoteCount
    private val _votes = MutableStateFlow<Map<Int, Int>>(emptyMap())
    val votes = _votes.asStateFlow()

    // Conjunto para rastrear por quién ha votado el usuario actual (en esta sesión)
    private val _votedCandidates = MutableStateFlow<Set<Int>>(emptySet())

    init {
        // Simular algunos votos iniciales para que el ranking no esté vacío
        _votes.value = mapOf(
            1 to 1580,
            2 to 1250,
            3 to 980,
            4 to 750,
            5 to 620,
            6 to 510,
        )
    }

    fun addVote(candidateId: Int) {
        // Actualizar el conteo de votos
        val currentVotes = _votes.value.toMutableMap()
        currentVotes[candidateId] = (currentVotes[candidateId] ?: 0) + 1
        _votes.value = currentVotes

        // Marcar que el usuario ya votó por este candidato
        val currentVotedSet = _votedCandidates.value.toMutableSet()
        currentVotedSet.add(candidateId)
        _votedCandidates.value = currentVotedSet
    }

    // Revisa si el usuario actual ha votado por un candidato específico
    fun hasVoted(candidateId: Int) = _votedCandidates.map { it.contains(candidateId) }
}
