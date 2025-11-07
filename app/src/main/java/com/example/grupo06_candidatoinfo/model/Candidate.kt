package com.example.grupo06_candidatoinfo.model

import com.google.gson.annotations.SerializedName

data class Candidate(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("party")
    val party: String,

    @SerializedName("position")
    val position: String,

    @SerializedName("photo_url")
    val photoUrl: String,

    @SerializedName("basic_info")
    val basicInfo: BasicInfoDto? = null,

    @SerializedName("asset_declaration")
    val assetDeclaration: AssetDeclarationDto? = null,

    @SerializedName("government_plan")
    val governmentPlan: GovernmentPlanDto? = null,

    @SerializedName("academic_formation")
    val academicFormation: AcademicFormationDto? = null,

    @SerializedName("career_history")
    val careerHistory: CareerHistoryDto? = null,

    @SerializedName("background_report")
    val backgroundReport: BackgroundReportDto? = null,

    @SerializedName("current_events")
    val currentEvents: CurrentEventsDto? = null,

    val isSelected: Boolean = false,
    val profileDetails: CandidateProfile? = null
) {
    fun toCandidateProfile(): CandidateProfile {
        val bi = basicInfo?.toBasicInfo() ?: BasicInfo(
            birthDate = "N/A",
            birthPlace = "N/A",
            age = "N/A",
            civilStatus = "N/A",
            residencePlace = "N/A"
        )
        val ad = assetDeclaration?.toAssetDeclaration() ?: AssetDeclaration(
            totalPatrimony = 0.0,
            assets = emptyList()
        )
        val gp = governmentPlan?.toGovernmentPlan() ?: GovernmentPlan(
            summary = "No disponible",
            proposals = emptyList(),
            documentId = "",
            documentUrl = null
        )
        val af = academicFormation?.toAcademicFormation() ?: AcademicFormation(
            degrees = emptyList(),
            documentId = ""
        )
        val ch = careerHistory?.toCareerHistory() ?: CareerHistory(emptyList())
        val br = backgroundReport?.toBackgroundReport() ?: BackgroundReport(emptyList())
        val ce = currentEvents?.toCurrentEvents() ?: CurrentEvents(emptyList())

        return CandidateProfile(
            basicInfo = bi,
            assetDeclaration = ad,
            governmentPlan = gp,
            academicFormation = af,
            careerHistory = ch,
            backgroundReport = br,
            currentEvents = ce
        )
    }
}

// DTOs for deserializing from API
data class BasicInfoDto(
    @SerializedName("birthDate")
    val birthDate: String? = null,
    @SerializedName("birthPlace")
    val birthPlace: String? = null,
    val age: String? = null,
    @SerializedName("maritalStatus")
    val civilStatus: String? = null,
    @SerializedName("residence")
    val residencePlace: String? = null
) {
    fun toBasicInfo() = BasicInfo(
        birthDate = birthDate ?: "N/A",
        birthPlace = birthPlace ?: "N/A",
        age = age ?: "N/A",
        civilStatus = civilStatus ?: "N/A",
        residencePlace = residencePlace ?: "N/A"
    )
}

data class AssetDeclarationDto(
    @SerializedName("totalValue")
    val totalPatrimony: Double? = null,
    val assets: List<AssetItemDto>? = null
) {
    fun toAssetDeclaration() = AssetDeclaration(
        totalPatrimony = totalPatrimony ?: 0.0,
        assets = assets?.map { it.toAssetItem() } ?: emptyList()
    )
}

data class AssetItemDto(
    val name: String? = null,
    val value: Double? = null,
    val color: String? = null
) {
    fun toAssetItem() = AssetItem(
        label = name ?: "Unknown",
        value = value ?: 0.0,
        colorHex = color ?: "#000000"
    )
}
data class GovernmentPlanDto(
    val summary: String? = null,
    val proposals: List<ProposalItemDto>? = null,
    @SerializedName("document_id")
    val documentId: String? = null,
    @SerializedName("document_url")
    val documentUrl: String? = null
) {
    fun toGovernmentPlan() = GovernmentPlan(
        summary = summary ?: "No disponible",
        proposals = proposals?.map { it.toProposalItem() } ?: emptyList(),
        documentId = documentId ?: "",
        documentUrl = documentUrl
    )
}

data class ProposalItemDto(
    val title: String? = null,
    val description: String? = null
) {
    fun toProposalItem() = ProposalItem(
        title = title ?: "",
        description = description ?: ""
    )
}

data class AcademicFormationDto(
    val degrees: List<AcademicDegreeDto>? = null,
    @SerializedName("sunedu_id")
    val documentId: String? = null
) {
    fun toAcademicFormation() = AcademicFormation(
        degrees = degrees?.map { it.toAcademicDegree() } ?: emptyList(),
        documentId = documentId ?: ""
    )
}

data class AcademicDegreeDto(
    val title: String? = null,
    val institution: String? = null
) {
    fun toAcademicDegree() = AcademicDegree(
        title = title ?: "",
        institutionAndPeriod = institution ?: ""
    )
}
data class CareerHistoryDto(
    val items: List<CareerItemDto>? = null
) {
    fun toCareerHistory() = CareerHistory(
        items = items?.map { it.toCareerItem() } ?: emptyList()
    )
}

data class CareerItemDto(
    val position: String? = null,
    val period: String? = null,
    val description: String? = null
) {
    fun toCareerItem() = CareerItem(
        position = position ?: "",
        period = period ?: "",
        description = description ?: ""
    )
}

data class BackgroundReportDto(
    val records: List<BackgroundRecordDto>? = null
) {
    fun toBackgroundReport() = BackgroundReport(
        records = records?.map { it.toBackgroundRecord() } ?: emptyList()
    )
}

data class BackgroundRecordDto(
    val title: String? = null,
    val entity: String? = null,
    val date: String? = null,
    val description: String? = null,
    val statusTags: List<String>? = null,
    val classificationTags: List<String>? = null,
    @SerializedName("documentId")
    val documentId: String? = null
) {
    fun toBackgroundRecord() = BackgroundRecord(
        title = title ?: "",
        entity = entity ?: "",
        date = date ?: "",
        description = description ?: "",
        statusTags = statusTags ?: emptyList(),
        classificationTags = classificationTags ?: emptyList(),
        documentId = documentId ?: ""
    )
}




data class CurrentEventsDto(
    val items: List<CurrentEventItemDto>? = null
) {
    fun toCurrentEvents() = CurrentEvents(
        items = items?.map { it.toCurrentEventItem() } ?: emptyList()
    )
}

data class CurrentEventItemDto(
    val id: Int? = null,
    val type: String? = null,
    val title: String? = null,
    val description: String? = null,
    val date: String? = null,
    val source: String? = null,
    @SerializedName("relatedCandidateName")
    val relatedTo: String? = null,
    @SerializedName("isVerified")
    val isVerified: Boolean? = null,
    @SerializedName("documentId")
    val documentId: String? = null
) {
    fun toCurrentEventItem() = CurrentEventItem(
        id = id ?: 0,
        type = type ?: "",
        title = title ?: "",
        description = description ?: "",
        date = date ?: "",
        source = source ?: "",
        relatedTo = relatedTo ?: "",
        isVerified = isVerified ?: false,
        documentId = documentId ?: ""
    )
}
