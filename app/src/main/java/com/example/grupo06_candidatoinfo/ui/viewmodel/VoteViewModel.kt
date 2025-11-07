package com.example.grupo06_candidatoinfo.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.grupo06_candidatoinfo.data.repository.MockDataRepository
import com.example.grupo06_candidatoinfo.data.repository.VoteRepository
import com.example.grupo06_candidatoinfo.model.Candidate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class VoteViewModel : ViewModel() {

    private val voteRepository = VoteRepository
    private val mockDataRepository = MockDataRepository

    fun hasVoted(candidateId: Int): Flow<Boolean> {
        return voteRepository.hasVoted(candidateId)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), false)
    }

    fun vote(candidateId: Int) {
        voteRepository.addVote(candidateId)
    }

    fun getRankedCandidates(): Flow<List<Pair<Candidate, Int>>> {
        // Obtiene la lista estática de candidatos
        val candidates = mockDataRepository.getCandidates()
        val candidateMap = candidates.associateBy { it.id }

        // Combina el Flow de votos con la lista estática
        return voteRepository.votes.map { votes ->
            votes.mapNotNull { (candidateId, voteCount) ->
                candidateMap[candidateId]?.let { candidate ->
                    Pair(candidate, voteCount)
                }
            }.sortedByDescending { it.second } // Ordenar por número de votos
        }
    }
}
