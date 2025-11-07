
// CandidateApiService.kt
package com.example.grupo06_candidatoinfo.data.remote

import com.example.grupo06_candidatoinfo.model.Candidate
import com.example.grupo06_candidatoinfo.model.InvestigationDetail
import com.example.grupo06_candidatoinfo.model.NewsDetail
import retrofit2.http.GET
import retrofit2.http.Path
// ... importa tus modelos

interface CandidateApiService {

    @GET("candidates/") // Reemplaza con el endpoint real de tu API de Django
    suspend fun getCandidates(): List<Candidate>

    @GET("news/{id}/")
    suspend fun getNewsDetail(@Path("id") documentId: String): NewsDetail

    @GET("investigation/{id}/")
    suspend fun getInvestigationDetail(@Path("id") documentId: String): InvestigationDetail
}