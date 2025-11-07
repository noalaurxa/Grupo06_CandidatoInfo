package com.example.grupo06_candidatoinfo.model

import com.google.gson.annotations.SerializedName

// ==================== MODELOS DE PERFIL (CANDIDATE PROFILE) ====================

data class CandidateProfile(
    val basicInfo: BasicInfo,
    val assetDeclaration: AssetDeclaration,
    val governmentPlan: GovernmentPlan,
    val academicFormation: AcademicFormation,
    val careerHistory: CareerHistory?,
    val backgroundReport: BackgroundReport?,
    val currentEvents: CurrentEvents?
)

data class BasicInfo(
    val birthDate: String,
    val birthPlace: String,
    val age: String,
    val civilStatus: String,
    val residencePlace: String
)

data class AssetDeclaration(
    val totalPatrimony: Double,
    val assets: List<AssetItem>
)

data class AssetItem(
    val label: String,
    val value: Double,
    val colorHex: String
)

data class GovernmentPlan(
    val summary: String,
    val proposals: List<ProposalItem>,
    val documentId: String,
    val documentUrl: String?
)

data class ProposalItem(
    val title: String,
    val description: String
)

data class AcademicFormation(
    val degrees: List<AcademicDegree>,
    val documentId: String
)

data class AcademicDegree(
    val title: String,
    val institutionAndPeriod: String
)

// ==================== MODELOS DE TRAYECTORIA ====================

data class CareerHistory(
    val items: List<CareerItem>
)

data class CareerItem(


    val position: String,
    val period: String,
    val description: String
)

// ==================== MODELOS PARA ANTECEDENTES ====================

data class BackgroundReport(
    val records: List<BackgroundRecord>
)

data class BackgroundRecord(
    val title: String,
    val entity: String,
    val date: String,
    val description: String,
    val statusTags: List<String>,
    val classificationTags: List<String>,
    val documentId: String
)

// modelo DETALLE INVESTIGACION

data class InvestigationDetail(
    @SerializedName("case_title")
    val caseTitle: String,

    @SerializedName("case_entity")
    val caseEntity: String,

    @SerializedName("case_date")
    val caseDate: String,

    @SerializedName("status")
    val status: String,

    @SerializedName("timeline")
    val timeline: List<CaseTimelineEvent> = emptyList(),

    @SerializedName("official_documents")
    val officialDocuments: List<OfficialDocument> = emptyList(), // ← clave corregida

    @SerializedName("involved_parties")
    val involvedParties: List<InvolvedParty> = emptyList() // ← clave corregida
)

/**
 * Modelo para un evento dentro de la Cronología del Caso.
 */


data class CaseTimelineEvent(
    val date: String,
    val title: String,
    val description: String
)

/**
 * Modelo para un Documento Oficial relacionado al caso (ej: Carpeta Fiscal).
 */
data class OfficialDocument(
    @SerializedName("title")
    val title: String,

    @SerializedName("documentUrl") // ← backend usa camelCase aquí
    val documentUrl: String?
)



// ==================== MODELOS PARA ACTUALIDAD Y DETALLE ====================

data class CurrentEvents(
    val items: List<CurrentEventItem>
)

data class CurrentEventItem(
    val id: Int,
    val type: String,
    val title: String,
    val description: String,
    val date: String,
    val source: String,
    val relatedTo: String,
    val isVerified: Boolean,
    val documentId: String // ¡Crucial para la navegación!
)


// --- MODELO PARA DETALLE DE NOTICIAS ---

data class NewsDetail(
    @SerializedName("title")
    val title: String,

    @SerializedName("summary")
    val summary: String, // Texto introductorio bajo el título

    @SerializedName("date")
    val date: String,

    @SerializedName("source")
    val source: String,

    @SerializedName("related_candidate_name")
    val relatedCandidateName: String, // ← corregido

    @SerializedName("full_description")
    val fullDescription: String, // ← corregido

    @SerializedName("is_verified")
    val isVerified: Boolean, // ← corregido

    @SerializedName("image_url")
    val imageUrl: String, // ← corregido

    @SerializedName("source_url")
    val sourceUrl: String? // ← corregido
)

/**
 * Modelo para una Parte Involucrada en la investigación.
 */
data class InvolvedParty(
    @SerializedName("name")
    val name: String,

    @SerializedName("role")
    val role: String
)
