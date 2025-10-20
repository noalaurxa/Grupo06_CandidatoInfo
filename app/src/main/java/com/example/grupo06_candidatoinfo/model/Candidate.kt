package com.example.grupo06_candidatoinfo.model

data class Candidate(
    val id: Int,
    val name: String,
    val party: String,
    val position: String,
    val photoUrl: String,
    val isSelected: Boolean = false
)