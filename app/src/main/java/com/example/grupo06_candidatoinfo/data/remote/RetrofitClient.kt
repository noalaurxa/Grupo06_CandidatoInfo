// Archivo: com.example.grupo06_candidatoinfo.data.remote.RetrofitClient.kt

package com.example.grupo06_candidatoinfo.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    // **USA 10.0.2.2 si corres en EMULADOR.** Si es dispositivo real, usa tu IP LAN (ej. 192.168.1.5)
    private const val BASE_URL = "http://10.0.2.2:8000/api/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: CandidateApiService by lazy {
        retrofit.create(CandidateApiService::class.java)
    }
}