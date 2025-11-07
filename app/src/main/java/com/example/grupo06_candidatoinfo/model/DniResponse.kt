package com.example.grupo06_candidatoinfo.model

data class DniResponse(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: DniData
)

data class DniData(
    val numero: String,
    val nombres: String,
    val apellido_paterno: String,
    val apellido_materno: String
)
