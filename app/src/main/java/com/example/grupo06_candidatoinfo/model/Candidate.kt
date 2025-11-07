package com.example.grupo06_candidatoinfo.model

import com.google.gson.annotations.SerializedName

data class Candidate(

    val id: Int,
    @SerializedName("nombre_completo")
    val name: String,
    @SerializedName("partido_politico")
    val party: String,
    val position: String,
    @SerializedName("foto_url")
    val photoUrl: String,
    val isSelected: Boolean = false,
    @SerializedName("profile_details")
    val profileDetails: CandidateProfile?
)