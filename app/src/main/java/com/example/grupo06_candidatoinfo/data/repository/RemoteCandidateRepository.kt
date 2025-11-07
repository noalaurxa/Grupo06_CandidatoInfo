// data/repository/RemoteCandidateRepository.kt
package com.example.grupo06_candidatoinfo.data.repository

import com.example.grupo06_candidatoinfo.data.remote.CandidateApiService
import com.example.grupo06_candidatoinfo.model.Candidate
import com.example.grupo06_candidatoinfo.model.InvestigationDetail
import com.example.grupo06_candidatoinfo.model.NewsDetail
import java.io.IOException

class RemoteCandidateRepository(
    private val apiService: CandidateApiService,
    // Puedes inyectar el mock para las funciones estáticas
    private val mockRepo: MockDataRepository
) : CandidateRepository {

    override suspend fun getCandidates(): List<Candidate> {
        return try {
            apiService.getCandidates()
        } catch (e: IOException) {
            // Error de red (sin conexión, servidor caído)
            emptyList()
        } catch (e: Exception) {
            // Otros errores (JSON mal formado, etc.)
            emptyList()
        }
    }

    override suspend fun getNewsDetail(documentId: String): NewsDetail? {
        return try {
            apiService.getNewsDetail(documentId)
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getInvestigationDetail(documentId: String): InvestigationDetail? {
        return try {
            apiService.getInvestigationDetail(documentId)
        } catch (e: Exception) {
            null
        }
    }

    // Mantenemos las listas de filtros desde el MockDataRepository (datos estáticos)
    override fun getPositions(): List<String> = mockRepo.getPositions()
    override fun getPoliticalParties(): List<String> = mockRepo.getPoliticalParties()
}