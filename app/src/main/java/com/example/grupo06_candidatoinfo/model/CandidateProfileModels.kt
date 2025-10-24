package com.example.grupo06_candidatoinfo.model

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
    val documentId: String
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
    val title: String,
    val summary: String, // Texto introductorio bajo el título
    val date: String,
    val source: String,
    val relatedCandidateName: String,
    val fullDescription: String, // El cuerpo del texto principal
    val isVerified: Boolean,
    val imageUrl: String, // Para la imagen en el detalle
    val sourceUrl: String? // URL de la fuente original
)