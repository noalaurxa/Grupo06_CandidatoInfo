package com.example.grupo06_candidatoinfo.data.repository

import com.example.grupo06_candidatoinfo.data.remote.RetrofitClient
import com.example.grupo06_candidatoinfo.model.Candidate
import com.example.grupo06_candidatoinfo.model.InvestigationDetail
import com.example.grupo06_candidatoinfo.model.NewsDetail

class CandidatoRepository {

    suspend fun getCandidatos(): List<Candidate> {
        return try {
            println("[v0] Repository: Calling API getCandidatos()...")
            val response = RetrofitClient.candidatoApi.getCandidatos()
            println("[v0] Repository: API Response received - size: ${response.results.size}")
            response.results
        } catch (e: java.io.IOException) {
            println("[v0] Repository: Network error - ${e.message}")
            e.printStackTrace()
            emptyList()
        } catch (e: Exception) {
            println("[v0] Repository: Unexpected error - ${e.javaClass.simpleName}: ${e.message}")
            e.printStackTrace()
            emptyList()
        }
    }

    suspend fun getCandidato(id: Int): Candidate? {
        return try {
            val candidate = RetrofitClient.candidatoApi.getCandidato(id)
            val profile = candidate.toCandidateProfile()
            candidate.copy(profileDetails = profile)
        } catch (e: Exception) {
            println("[v0] Exception in getCandidato: ${e.message}")
            e.printStackTrace()
            null
        }
    }

    suspend fun getCandidateNews(id: Int): List<NewsDetail> {
        return try {
            println("[v0] API call: getCandidateNews($id)")
            val response = RetrofitClient.candidatoApi.getCandidateNews(id)
            println("[v0] API Response received with ${response.size} news results")
            response
        } catch (e: Exception) {
            println("[v0] Exception in getCandidateNews: ${e.message}")
            e.printStackTrace()
            emptyList()
        }
    }

    suspend fun getCandidateInvestigations(id: Int): List<InvestigationDetail> {
        return try {
            println("[v0] API call: getCandidateInvestigations($id)")
            val response = RetrofitClient.candidatoApi.getCandidateInvestigations(id)
            println("[v0] API Response received with ${response.size} investigation results")
            response
        } catch (e: Exception) {
            println("[v0] Exception in getCandidateInvestigations: ${e.message}")
            e.printStackTrace()
            emptyList()
        }
    }

    suspend fun getInvestigationDetail(documentId: String): InvestigationDetail? {
        return try {
            println("[v0] API call: getInvestigationDetail($documentId)")
            val response = RetrofitClient.candidatoApi.getInvestigationDetail(documentId)
            println("[v0] API Response received for investigation detail")
            response
        } catch (e: Exception) {
            println("[v0] Exception in getInvestigationDetail: ${e.message}")
            null
        }
    }

    suspend fun getNewsDetail(documentId: String): NewsDetail? {
        return try {
            println("[v0] API call: getNewsDetail($documentId)")
            val response = RetrofitClient.candidatoApi.getNewsDetail(documentId)
            println("[v0] API Response received for news detail")
            response
        } catch (e: Exception) {
            println("[v0] Exception in getNewsDetail: ${e.message}")
            null
        }
    }




}
