package com.example.grupo06_candidatoinfo.data.repository

import com.example.grupo06_candidatoinfo.model.AcademicDegree
import com.example.grupo06_candidatoinfo.model.AcademicFormation
import com.example.grupo06_candidatoinfo.model.AssetDeclaration
import com.example.grupo06_candidatoinfo.model.AssetItem
import com.example.grupo06_candidatoinfo.model.BasicInfo
import com.example.grupo06_candidatoinfo.model.Candidate
import com.example.grupo06_candidatoinfo.model.CandidateProfile
import com.example.grupo06_candidatoinfo.model.CareerHistory
import com.example.grupo06_candidatoinfo.model.CareerItem
import com.example.grupo06_candidatoinfo.model.ElectionType
import com.example.grupo06_candidatoinfo.model.GovernmentPlan
import com.example.grupo06_candidatoinfo.model.ProposalItem
import com.example.grupo06_candidatoinfo.model.BackgroundReport
import com.example.grupo06_candidatoinfo.model.BackgroundRecord
import com.example.grupo06_candidatoinfo.model.CurrentEvents
import com.example.grupo06_candidatoinfo.model.CurrentEventItem // Modelo para Actualidad

object MockDataRepository {

    fun getElectionTypes(): List<ElectionType> {
        return listOf(
            ElectionType(1, "Elecciones Generales 2026")
        )
    }

    fun getCandidates(): List<Candidate> {
        return listOf(
            // --- CANDIDATO 1: Rafael López Aliaga ---
            Candidate(
                id = 1,
                name = "Rafael López Aliaga",
                party = "Renovación Popular",
                position = "Presidencia",
                photoUrl = "https://tse1.mm.bing.net/th/id/OIP.aAFAdIKomuFf5FSyxibhMQHaHa?rs=1&pid=ImgDetMain&o=7&rm=3",
                profileDetails = CandidateProfile(
                    basicInfo = BasicInfo("11/02/1961", "Lima", "64 años", "Soltero", "Lima"),
                    assetDeclaration = AssetDeclaration(
                        2500000.0, listOf(
                            AssetItem("Inmuebles", 1800000.0, "#E53935"),
                            AssetItem("Vehículos", 300000.0, "#3C3472"),
                            AssetItem("Ingresos anuales (último)", 400000.0, "#6E6E6E")
                        )
                    ),
                    governmentPlan = GovernmentPlan(
                        "Plan de gobierno centrado en la inversión privada...", listOf(
                            ProposalItem(
                                "Economía",
                                "Fomento de la inversión y reducción de la burocracia."
                            ),
                            ProposalItem(
                                "Seguridad",
                                "Implementación de sistema de 'policía de barrio' y tecnología."
                            )
                        ), "plan_gobierno_1"
                    ),
                    academicFormation = AcademicFormation(
                        listOf(
                            AcademicDegree(
                                "Maestría en Administración",
                                "Universidad de Piura (1990-1992)"
                            ),
                            AcademicDegree(
                                "Ingeniería Industrial",
                                "Universidad de Piura (1979-1984)"
                            )
                        ), "sunedu_1"
                    ),
                    // --- DATOS DE TRAYECTORIA ---
                    careerHistory = CareerHistory(
                        items = listOf(
                            CareerItem(
                                "Alcalde de Lima",
                                "2023-Presente",
                                "Gestión municipal enfocada en seguridad, transporte y programas sociales."
                            ),
                            CareerItem(
                                "Regidor de Lima",
                                "2007-2010",
                                "Participó en comisiones de desarrollo urbano y transporte."
                            ),
                            CareerItem(
                                "Presidente del Directorio en PeruRail S.A.",
                                "1996-Presente",
                                "Fundador y líder de PeruRail, impulsando el turismo en Cusco."
                            )
                        )
                    ),
                    // --- ANTECEDENTES (ACTUALIZADO: statusTags y classificationTags) ---
                    backgroundReport = BackgroundReport(
                        records = listOf(
                            // Registro 1: Denuncia activa (Lavado de Activos)
                            BackgroundRecord(
                                title = "Investigación por Lavado de Activos",
                                entity = "Ministerio Público",
                                date = "15/05/2021",
                                description = "Investigación preliminar en el marco del caso 'Panama Papers'. El caso se encuentra en etapa de investigación y no cuenta con sentencia.",
                                statusTags = listOf("Activo", "Investigación"),
                                classificationTags = listOf("Lavado de Activos"),
                                documentId = "doc_antecedente_1_1"
                            ),
                            // Registro 2: Sanción administrativa archivada
                            BackgroundRecord(
                                title = "Multa por Infracción Tributaria",
                                entity = "SUNAT",
                                date = "01/03/2019",
                                description = "Sanción administrativa por omisión en declaración de activos. Monto cancelado y caso cerrado.",
                                statusTags = listOf("Archivado"),
                                classificationTags = listOf("Administrativo", "Tributario"),
                                documentId = "doc_antecedente_1_2"
                            )
                        )
                    ),
                    // --- ACTUALIDAD (Añadido) ---
                    currentEvents = CurrentEvents(
                        items = listOf(
                            CurrentEventItem(
                                id = 101,
                                type = "noticia",
                                title = "López Aliaga anuncia 'Plan Cero Hambre'",
                                description = "El candidato presentó su plan de gobierno con énfasis en la lucha contra la desnutrición y la promoción de comedores populares.",
                                date = "2025-10-18",
                                source = "El Comercio",
                                relatedTo = "Rafael López Aliaga",
                                isVerified = true
                            ),
                            CurrentEventItem(
                                id = 102,
                                type = "actividad",
                                title = "Mitin de cierre en Plaza San Martín",
                                description = "Realizó un evento masivo en el centro de Lima, presentando a sus candidatos al congreso.",
                                date = "2025-10-14",
                                source = "RPP Noticias",
                                relatedTo = "Rafael López Aliaga",
                                isVerified = false
                            )
                        )
                    )
                )
            ),
            // --- CANDIDATO 2: Keiko Fujimori ---
            Candidate(
                id = 2,
                name = "Keiko Fujimori",
                party = "Fuerza Popular",
                position = "Presidencia",
                photoUrl = "https://tvperu.gob.pe/sites/default/files/000778186w.jpg",
                profileDetails = CandidateProfile(
                    basicInfo = BasicInfo("25/05/1975", "Lima", "50 años", "Casada", "Lima"),
                    assetDeclaration = AssetDeclaration(
                        1100000.0, listOf(
                            AssetItem("Inmuebles", 800000.0, "#E53935"),
                            AssetItem("Vehículos", 150000.0, "#3C3472")
                        )
                    ),
                    governmentPlan = GovernmentPlan(
                        "Propuesta de reactivación económica 'Plan Rescate 2026'...", listOf(
                            ProposalItem(
                                "Economía",
                                "Incentivos fiscales para PYMEs y atracción de inversión."
                            ),
                            ProposalItem(
                                "Seguridad",
                                "Construcción de más penales y fortalecimiento de la policía."
                            )
                        ), "plan_gobierno_2"
                    ),
                    academicFormation = AcademicFormation(
                        listOf(
                            AcademicDegree(
                                "Master in Business Administration",
                                "Columbia University (2004-2006)"
                            ),
                            AcademicDegree("Bachelor of Science", "Boston University (1993-1997)")
                        ), "sunedu_2"
                    ),
                    // --- DATOS DE TRAYECTORIA ---
                    careerHistory = CareerHistory(
                        items = listOf(
                            CareerItem(
                                "Candidata a la Presidencia",
                                "2011, 2016, 2021",
                                "Lideró el partido Fuerza Popular en tres elecciones generales."
                            ),
                            CareerItem(
                                "Congresista de la República",
                                "2006-2011",
                                "Fue la congresista más votada en las elecciones de 2006."
                            )
                        )
                    ),
                    // --- ANTECEDENTES (ACTUALIZADO: statusTags y classificationTags) ---
                    backgroundReport = BackgroundReport(
                        records = listOf(
                            // Registro 1: Juicio Oral por Lavado de Activos
                            BackgroundRecord(
                                title = "Acusación por Lavado de Activos",
                                entity = "Equipo Especial Lava Jato",
                                date = "11/03/2021",
                                description = "Acusación fiscal por los presuntos delitos de organización criminal, lavado de activos y otros en el marco del 'Caso Cócteles'. El juicio oral está pendiente de inicio.",
                                statusTags = listOf("Juicio Oral", "Pendiente"),
                                classificationTags = listOf("Lavado de Activos", "Corrupción"),
                                documentId = "doc_antecedente_2_1"
                            ),
                            // Registro 2: Obstruction (Archivado/Desestimado)
                            BackgroundRecord(
                                title = "Obstrucción a la justicia",
                                entity = "Ministerio Público",
                                date = "10/10/2018",
                                description = "Prisión preventiva dictada en el marco de la investigación por presunta obstrucción a la justicia al interferir con testigos del caso. Posteriormente archivado.",
                                statusTags = listOf("Archivado", "Desestimado"),
                                classificationTags = listOf("Obstrucción"),
                                documentId = "doc_antecedente_2_2"
                            )
                        )
                    ),
                    // --- ACTUALIDAD (Añadido) ---
                    currentEvents = CurrentEvents(
                        items = listOf(
                            CurrentEventItem(
                                id = 201,
                                type = "noticia",
                                title = "Candidata presenta plan de gobierno enfocado en reactivacion economica",
                                description = "María Elena Rodríguez presentó su plan de gobierno con énfasis en la reactivación económica post-pandemia, incluyendo medidas de apoyo a las MiPymes y generación de empleo formal.",
                                date = "2025-10-18",
                                source = "El Comercio",
                                relatedTo = "Keiko Fujimori",
                                isVerified = true
                            ),
                            CurrentEventItem(
                                id = 202,
                                type = "actividad",
                                title = "Candidata presenta plan de gobierno enfocado en reactivacion economica",
                                description = "Maria Elena Rodrigues presento su plan de gobierno con enfasis...",
                                date = "2025-10-14",
                                source = "El Comercio",
                                relatedTo = "Keiko Fujimori",
                                isVerified = false
                            ),
                            CurrentEventItem(
                                id = 203,
                                type = "documento",
                                title = "Candidata presenta plan de gobierno enfocado en reactivacion economica",
                                description = "Maria Elena Rodrigues presento su plan de gobierno con enfasis...",
                                date = "2025-09-21",
                                source = "El Comercio",
                                relatedTo = "Keiko Fujimori",
                                isVerified = true
                            )
                        )
                    )
                )
            ),
            // --- CANDIDATO 3: César Acuña ---
            Candidate(
                id = 3,
                name = "César Acuña",
                party = "Alianza para el Progreso",
                position = "Presidencia",
                photoUrl = "https://portal.andina.pe/EDPfotografia3/Thumbnail/2021/03/29/000761739W.jpg",
                profileDetails = CandidateProfile(
                    basicInfo = BasicInfo("11/08/1952", "Cajamarca", "73 años", "Casado", "Lima"),
                    assetDeclaration = AssetDeclaration(
                        5000000.0, listOf(
                            AssetItem("Inmuebles", 4000000.0, "#E53935")
                        )
                    ),
                    governmentPlan = GovernmentPlan(
                        "Énfasis en la educación como motor de desarrollo...", listOf(
                            ProposalItem(
                                "Educación",
                                "Inversión del 6% del PBI en educación y becas."
                            )
                        ), "plan_gobierno_3"
                    ),
                    academicFormation = AcademicFormation(
                        listOf(
                            AcademicDegree(
                                "Doctor en Educación",
                                "Universidad Complutense de Madrid (2000-2003)"
                            )
                        ), "sunedu_3"
                    ),
                    // --- DATOS DE TRAYECTORIA ---
                    careerHistory = CareerHistory(
                        items = listOf(
                            CareerItem(
                                "Gobernador Regional de La Libertad",
                                "2015-2018",
                                "Impulsó proyectos de infraestructura y educación en la región."
                            ),
                            CareerItem(
                                "Alcalde de Trujillo",
                                "2007-2014",
                                "Realizó dos gestiones consecutivas en la alcaldía provincial."
                            ),
                            CareerItem(
                                "Congresista de la República",
                                "2000-2001",
                                "Participó en el Congreso durante el periodo de transición."
                            )
                        )
                    ),
                    // --- ANTECEDENTES (ACTUALIZADO: statusTags y classificationTags) ---
                    backgroundReport = BackgroundReport(
                        records = listOf(
                            // Registro 1: Investigación por Plagio
                            BackgroundRecord(
                                title = "Investigación por Plagio Agravado",
                                entity = "Fiscalía de la Nación",
                                date = "20/04/2016",
                                description = "Investigación por el presunto plagio en su tesis doctoral. La universidad validó el grado, pero la investigación fiscal continuó en diversas etapas.",
                                statusTags = listOf("Investigación", "Pendiente"),
                                classificationTags = listOf("Plagio", "Académico"),
                                documentId = "doc_antecedente_3_1"
                            ),
                            // Registro 2: Demanda Civil
                            BackgroundRecord(
                                title = "Demanda por Incumplimiento de Contrato",
                                entity = "Poder Judicial - Civil",
                                date = "10/09/2015",
                                description = "Proceso civil por disputa contractual con ex-socio en el ámbito empresarial. Sentencia a favor del demandante.",
                                statusTags = listOf("Sentenciado"),
                                classificationTags = listOf("Civil", "Contractual"),
                                documentId = "doc_antecedente_3_2"
                            )
                        )
                    ),
                    // --- ACTUALIDAD (Añadido) ---
                    currentEvents = CurrentEvents(
                        items = listOf(
                            CurrentEventItem(
                                id = 301,
                                type = "noticia",
                                title = "Acuña promete 'Educación como Revolución'",
                                description = "El candidato de APP centró su discurso en la inversión del 6% del PBI para el sector educativo.",
                                date = "2025-10-17",
                                source = "La República",
                                relatedTo = "César Acuña",
                                isVerified = false
                            )
                        )
                    )
                )
            ),
            // --- CANDIDATO 4: Carlos Álvarez ---
            Candidate(
                id = 4,
                name = "Carlos Álvarez",
                party = "País para Todos",
                position = "Presidencia",
                photoUrl = "https://statics.exitosanoticias.pe/2023/10/653bc125b24b1.png",
                profileDetails = null // Sigue sin datos detallados
            ),
            // --- CANDIDATO 5: Martín Vizcarra ---
            Candidate(
                id = 5,
                name = "Martín Vizcarra", // Nombre corregido
                party = "Perú Primero",
                position = "Presidencia",
                photoUrl = "https://tse2.mm.bing.net/th/id/OIP.rEIcUIfKg887VmAIBBNYLwHaE8?rs=1&pid=ImgDetMain&o=7&rm=3",
                profileDetails = CandidateProfile(
                    basicInfo = BasicInfo(
                        "22/03/1963",
                        "Moquegua",
                        "62 años",
                        "Casado",
                        "Moquegua"
                    ),
                    assetDeclaration = AssetDeclaration(
                        900000.0, listOf(
                            AssetItem("Inmuebles", 700000.0, "#E53935")
                        )
                    ),
                    governmentPlan = GovernmentPlan(
                        "Reforma del sistema político y lucha frontal contra la corrupción.",
                        listOf(
                            ProposalItem(
                                "Reforma",
                                "Eliminación de la inmunidad parlamentaria y bicameralidad."
                            )
                        ),
                        "plan_gobierno_5"
                    ),
                    academicFormation = AcademicFormation(
                        listOf(
                            AcademicDegree(
                                "Ingeniería Civil",
                                "Universidad Nacional de Ingeniería (1980-1985)"
                            )
                        ), "sunedu_5"
                    ),
                    // --- DATOS DE TRAYECTORIA ---
                    careerHistory = CareerHistory(
                        items = listOf(
                            CareerItem(
                                "Presidente de la República",
                                "2018-2020",
                                "Asumió la presidencia tras la renuncia de Pedro Pablo Kuczynski."
                            ),
                            CareerItem(
                                "Gobernador Regional de Moquegua",
                                "2011-2014",
                                "Reconocido por su gestión en el sector educativo de la región."
                            )
                        )
                    ),
                    // --- ANTECEDENTES (ACTUALIZADO: statusTags y classificationTags) ---
                    backgroundReport = BackgroundReport(
                        records = listOf(
                            // Registro 1: Inhabilitación
                            BackgroundRecord(
                                title = "Inhabilitación por 10 años",
                                entity = "Congreso de la República",
                                date = "17/04/2021",
                                description = "El Congreso lo inhabilitó para ejercer cargos públicos por su participación en el caso 'Vacunagate', por vacunación irregular contra la COVID-19.",
                                statusTags = listOf("Sentenciado", "Inhabilitado"),
                                classificationTags = listOf("Administrativo", "Ético"),
                                documentId = "doc_antecedente_5_1"
                            ),
                            // Registro 2: Investigación por Corrupción
                            BackgroundRecord(
                                title = "Investigación por 'Club de la Construcción'",
                                entity = "Fiscalía - Corrupción",
                                date = "05/01/2020",
                                description = "Investigación por presuntos pagos irregulares y licitaciones fraudulentas cuando fue Gobernador de Moquegua. Proceso en curso.",
                                statusTags = listOf("Activo", "Investigación"),
                                classificationTags = listOf("Corrupción"),
                                documentId = "doc_antecedente_5_2"
                            )
                        )
                    ),
                    // --- ACTUALIDAD ---
                    currentEvents = CurrentEvents(
                        items = listOf(
                            CurrentEventItem(
                                id = 501,
                                type = "actividad",
                                title = "Vizcarra apela inhabilitación ante TC",
                                description = "Defensa legal del candidato presentó un recurso ante el Tribunal Constitucional para poder postular en las elecciones 2026.",
                                date = "2025-10-10",
                                source = "Canal N",
                                relatedTo = "Martín Vizcarra",
                                isVerified = true
                            )
                        )
                    )
                )
            )
        )
    }

    fun getPositions(): List<String> {
        return listOf("Todos", "Presidencia", "Congreso", "Alcaldía", "Gobernador")
    }

    fun getPoliticalParties(): List<String> {
        return listOf(
            "Todos",
            "Renovación Popular",
            "Fuerza Popular",
            "Alianza para el Progreso",
            "País para Todos",
            "Perú Primero"
        )
    }
}
