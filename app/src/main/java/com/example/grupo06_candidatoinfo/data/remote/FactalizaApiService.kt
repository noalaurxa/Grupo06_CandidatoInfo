package com.example.grupo06_candidatoinfo.data.remote

import com.example.grupo06_candidatoinfo.model.DniResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface FactilizaApiService {
    @GET("v1/dni/info/{dni}")
    suspend fun getDniInfo(@Path("dni") dni: String): DniResponse
}
