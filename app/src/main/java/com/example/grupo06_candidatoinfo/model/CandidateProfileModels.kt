package com.example.grupo06_candidatoinfo.model

data class CandidateProfile(
    val basicInfo: BasicInfo,
    val assetDeclaration: AssetDeclaration,
    val governmentPlan: GovernmentPlan,
    val academicFormation: AcademicFormation,
    val careerHistory: CareerHistory?, // Hacemos nullable por si no hay data
    val backgroundReport: BackgroundReport? // Hacemos nullable por si no hay data
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

// --- MODELO DE TRAYECTORIA CORREGIDO ---
// Se añade 'description' para que coincida con la vista.
data class CareerItem(
    val position: String,
    val period: String,
    val description: String
)

// --- MODELOS PARA ANTECEDENTES ---
data class BackgroundReport(
    val records: List<BackgroundRecord>
)

data class BackgroundRecord(
    val title: String,
    val entity: String,
    val date: String,
    val description: String,
    val tags: List<String>,
    val documentId: String
)

