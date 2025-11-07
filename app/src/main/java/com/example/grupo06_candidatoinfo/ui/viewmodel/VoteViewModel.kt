package com.example.grupo06_candidatoinfo.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.grupo06_candidatoinfo.data.repository.VoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class VoteViewModel : ViewModel() {

    private val voteRepository = VoteRepository

    fun hasVoted(candidateId: Int): Flow<Boolean> {
        return voteRepository.hasVoted(candidateId)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), false)
    }

    fun vote(candidateId: Int) {
        voteRepository.addVote(candidateId)
    }
}
