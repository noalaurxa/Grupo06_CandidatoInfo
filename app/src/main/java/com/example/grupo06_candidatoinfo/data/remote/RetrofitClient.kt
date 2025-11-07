package com.example.grupo06_candidatoinfo.data.remote

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://api.factiliza.com/"
    private const val TOKEN = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIzOTgwNiIsImh0dHA6Ly9zY2hlbWFzLm1pY3Jvc29mdC5jb20vd3MvMjAwOC8wNi9pZGVudGl0eS9jbGFpbXMvcm9sZSI6ImNvbnN1bHRvciJ9.DEZbRTMaKQQ-fIZeJ0fxEiUKthAJT5Ic11yS-joI2u0" // tu token real aquí

    private val authInterceptor = Interceptor { chain ->
        val newRequest = chain.request().newBuilder()
            .addHeader("Authorization", TOKEN)
            .build()
        chain.proceed(newRequest)
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .build()

    val api: FactilizaApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FactilizaApiService::class.java)
    }
}
