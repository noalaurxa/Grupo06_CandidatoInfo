package com.example.grupo06_candidatoinfo.data.repository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

object VoteRepository {

    private val _votedCandidates = MutableStateFlow<Set<Int>>(emptySet())

    fun addVote(candidateId: Int) {
        val currentVotes = _votedCandidates.value.toMutableSet()
        currentVotes.add(candidateId)
        _votedCandidates.value = currentVotes
    }

    fun hasVoted(candidateId: Int) = _votedCandidates.map { it.contains(candidateId) }
}
