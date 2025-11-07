package com.example.grupo06_candidatoinfo.data.repository

// CandidateRepository.kt

import com.example.grupo06_candidatoinfo.model.Candidate
import com.example.grupo06_candidatoinfo.model.InvestigationDetail
import com.example.grupo06_candidatoinfo.model.NewsDetail

// ... otros modelos ...

interface CandidateRepository {
    // Las operaciones de red deben ser 'suspend'
    suspend fun getCandidates(): List<Candidate>
    suspend fun getNewsDetail(documentId: String): NewsDetail?
    suspend fun getInvestigationDetail(documentId: String): InvestigationDetail?

    // Los filtros pueden ser funciones normales si los datos son estáticos
    fun getPositions(): List<String>
    fun getPoliticalParties(): List<String>
}

// **IMPORTANTE:** Tu MockDataRepository debe actualizarse para implementar esta interfaz:
// object MockDataRepository : CandidateRepository { ... }