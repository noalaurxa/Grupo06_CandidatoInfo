package com.example.grupo06_candidatoinfo.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://10.0.2.2:8000/api/" // 10.0.2.2 es localhost en emulador

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val candidatoApi: CandidatoApiService = retrofit.create(CandidatoApiService::class.java)
}
