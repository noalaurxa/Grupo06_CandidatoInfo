package com.example.grupo06_candidatoinfo.data.remote

import com.example.grupo06_candidatoinfo.model.Candidate
import com.example.grupo06_candidatoinfo.model.InvestigationDetail
import com.example.grupo06_candidatoinfo.model.NewsDetail
import com.example.grupo06_candidatoinfo.data.remote.CandidatosResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CandidatoApiService {

    @GET("candidatos/")
    suspend fun getCandidatos(): CandidatosResponse

    @GET("candidatos/{id}/")
    suspend fun getCandidato(@Path("id") id: Int): Candidate

    @GET("candidatos/{id}/news/")
    suspend fun getCandidateNews(@Path("id") id: Int): List<NewsDetail>

    @GET("candidatos/{id}/investigations/")
    suspend fun getCandidateInvestigations(@Path("id") id: Int): List<InvestigationDetail>

    @GET("news/{document_id}/")
    suspend fun getNewsDetail(@Path("document_id") documentId: String): NewsDetail

    @GET("investigations/{document_id}/")
    suspend fun getInvestigationDetail(@Path("document_id") documentId: String): InvestigationDetail
}
