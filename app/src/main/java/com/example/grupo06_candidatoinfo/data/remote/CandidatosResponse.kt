package com.example.grupo06_candidatoinfo.data.remote

import com.example.grupo06_candidatoinfo.model.Candidate
import com.google.gson.annotations.SerializedName

data class CandidatosResponse(
    @SerializedName("count")
    val count: Int,

    @SerializedName("next")
    val next: String?,

    @SerializedName("previous")
    val previous: String?,

    @SerializedName("results")
    val results: List<Candidate>
)
