package com.example.grupo06_candidatoinfo.model

data class CandidateProfile(
    val basicInfo: BasicInfo,
    val assetDeclaration: AssetDeclaration,
    val governmentPlan: GovernmentPlan,
    val academicFormation: AcademicFormation,
    val careerHistory: CareerHistory,
    // [NUEVO] Propiedad de antecedentes
    val backgroundHistory: BackgroundHistory?
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

// TRAYECTORIA
data class CareerHistory(
    val items: List<CareerItem>
)

data class CareerItem(
    val position: String,
    val period: String,
    val description: String
)
// ANTECEDENTES (Background)
data class BackgroundHistory(
    val items: List<BackgroundItem>
)

data class BackgroundItem(
    val type: String, // Ejemplo: "Denuncia", "Investigación", "Sentencia"
    val description: String,
    val date: String,
    val status: String // Ejemplo: "Vigente", "Archivado", "Concluido"
)