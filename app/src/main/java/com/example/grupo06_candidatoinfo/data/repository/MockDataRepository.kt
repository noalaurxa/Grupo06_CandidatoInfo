package com.example.grupo06_candidatoinfo.data.repository

<<<<<<< HEAD
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
import com.example.grupo06_candidatoinfo.model.BackgroundHistory
import com.example.grupo06_candidatoinfo.model.BackgroundItem
import com.example.grupo06_candidatoinfo.model.CurrentEvents // <-- AÑADIDO
import com.example.grupo06_candidatoinfo.model.CurrentEventItem // <-- AÑADIDO

=======
import com.example.grupo06_candidatoinfo.model.*
>>>>>>> f0cdad359463fc8d12d70855d733c2060391744a

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
                    assetDeclaration = AssetDeclaration(2500000.0, listOf(
                        AssetItem("Inmuebles", 1800000.0, "#E53935"),
                        AssetItem("Vehículos", 300000.0, "#3C3472"),
                        AssetItem("Ingresos anuales (último)", 400000.0, "#6E6E6E")
                    )),
                    governmentPlan = GovernmentPlan("Plan de gobierno centrado en la inversión privada...", listOf(
                        ProposalItem("Economía", "Fomento de la inversión y reducción de la burocracia."),
                        ProposalItem("Seguridad", "Implementación de sistema de 'policía de barrio' y tecnología.")
                    ), "plan_gobierno_1"),
                    academicFormation = AcademicFormation(listOf(
                        AcademicDegree("Maestría en Administración", "Universidad de Piura (1990-1992)"),
                        AcademicDegree("Ingeniería Industrial", "Universidad de Piura (1979-1984)")
                    ), "sunedu_1"),
                    // --- DATOS DE TRAYECTORIA CORREGIDOS ---
                    careerHistory = CareerHistory(items = listOf(
                        CareerItem("Alcalde de Lima", "2023-Presente", "Gestión municipal enfocada en seguridad, transporte y programas sociales."),
                        CareerItem("Regidor de Lima", "2007-2010", "Participó en comisiones de desarrollo urbano y transporte."),
<<<<<<< HEAD
                        CareerItem("Presidente del Directorio", "1996-Presente", "Fundador y líder de PeruRail, impulsando el turismo en Cusco.")
                    )),
                    // [ANTECEDENTES ACTUALIZADO]
                    backgroundHistory = BackgroundHistory(items = listOf(
                        BackgroundItem(
                            title = "Investigación por Lavado de Activos",
                            description = "Investigación preliminar por presunto delito de lavado de activos en sus empresas.",
                            entity = "Fiscalía de Lavado de Activos",
                            date = "20 sep. 2021",
                            status = "Vigente",
                            category = "Investigación"
                        ),
                        BackgroundItem(
                            title = "Denuncia por Difamación",
                            description = "Denuncia por difamación desestimada por el Poder Judicial.",
                            entity = "Poder Judicial",
                            date = "05 mar. 2019",
                            status = "Archivado",
                            category = "Denuncia"
                        )
                    )),
                    // [ACTUALIDAD AÑADIDO]
                    currentEvents = CurrentEvents(items = listOf(
                        CurrentEventItem(
                            id = 101,
                            type = "noticia",
                            title = "López Aliaga anuncia 'Plan Cero Hambre'",
                            description = "El candidato presentó su plan de gobierno con énfasis en la lucha contra la desnutrición y la promoción de comedores populares.",
                            date = "2025-10-18", // "Hace 3 días" (asumiendo hoy es 21/10)
                            source = "El Comercio",
                            relatedTo = "Rafael López Aliaga",
                            isVerified = true
                        ),
                        CurrentEventItem(
                            id = 102,
                            type = "actividad",
                            title = "Mitin de cierre en Plaza San Martín",
                            description = "Realizó un evento masivo en el centro de Lima, presentando a sus candidatos al congreso.",
                            date = "2025-10-14", // "Hace 1 semana"
                            source = "RPP Noticias",
                            relatedTo = "Rafael López Aliaga",
                            isVerified = false
                        )
                    ))
=======
                        CareerItem("Presidente del Directorio en PeruRail S.A.", "1996-Presente", "Fundador y líder de PeruRail, impulsando el turismo en Cusco.")
                    )),
                    backgroundReport = BackgroundReport(
                        records = listOf(
                            BackgroundRecord("Investigación por Lavado de Activos", "Ministerio Público", "15/05/2021", "Investigación preliminar en el marco del caso 'Panama Papers'. El caso se encuentra en etapa de investigación y no cuenta con sentencia.", listOf("Investigación"), "doc_antecedente_1")
                        )
                    )
>>>>>>> f0cdad359463fc8d12d70855d733c2060391744a
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
                    assetDeclaration = AssetDeclaration(1100000.0, listOf(
                        AssetItem("Inmuebles", 800000.0, "#E53935"),
                        AssetItem("Vehículos", 150000.0, "#3C3472")
                    )),
                    governmentPlan = GovernmentPlan("Propuesta de reactivación económica 'Plan Rescate 2026'...", listOf(
                        ProposalItem("Economía", "Incentivos fiscales para PYMEs y atracción de inversión."),
                        ProposalItem("Seguridad", "Construcción de más penales y fortalecimiento de la policía.")
                    ), "plan_gobierno_2"),
                    academicFormation = AcademicFormation(listOf(
                        AcademicDegree("Master in Business Administration", "Columbia University (2004-2006)"),
                        AcademicDegree("Bachelor of Science", "Boston University (1993-1997)")
                    ), "sunedu_2"),
                    // --- DATOS DE TRAYECTORIA CORREGIDOS ---
                    careerHistory = CareerHistory(items = listOf(
                        CareerItem("Candidata a la Presidencia", "2011, 2016, 2021", "Lideró el partido Fuerza Popular en tres elecciones generales."),
                        CareerItem("Congresista de la República", "2006-2011", "Fue la congresista más votada en las elecciones de 2006.")
                    )),
<<<<<<< HEAD
                    // [ANTECEDENTES ACTUALIZADO]
                    backgroundHistory = BackgroundHistory(items = listOf(
                        BackgroundItem(
                            title = "Caso Odebrecht - Aportes de Campaña",
                            description = "Investigación fiscal por presuntos aportes no declarados de la constructora Odebrecht a su campaña. El caso fue archivado por falta de elementos probatorios.",
                            entity = "Fiscalía Especializada en Delitos de Corrupción",
                            date = "2020", // Fecha de ejemplo
                            status = "Archivado",
                            category = "Investigación"
                        ),
                        BackgroundItem(
                            title = "Denuncia por Obstrucción a la Justicia",
                            description = "Proceso judicial por presunta obstrucción a la justicia en el marco de las investigaciones de campaña.",
                            entity = "Poder Judicial",
                            date = "15 jul. 2022",
                            status = "Vigente",
                            category = "Denuncia"
                        )
                    )),
                    // [ACTUALIDAD AÑADIDO - Basado en tus imágenes]
                    currentEvents = CurrentEvents(items = listOf(
                        CurrentEventItem(
                            id = 201,
                            type = "noticia",
                            title = "Candidata presenta plan de gobierno enfocado en reactivacion economica",
                            description = "María Elena Rodríguez presentó su plan de gobierno con énfasis en la reactivación económica post-pandemia, incluyendo medidas de apoyo a las MiPymes y generación de empleo formal.",
                            date = "2025-10-18", // "Hace 3 días"
                            source = "El Comercio",
                            relatedTo = "Keiko Fujimori", // Asumiendo "Maria Elena" es un placeholder
                            isVerified = true
                        ),
                        CurrentEventItem(
                            id = 202,
                            type = "actividad",
                            title = "Candidata presenta plan de gobierno enfocado en reactivacion economica",
                            description = "Maria Elena Rodrigues presento su plan de gobierno con enfasis...",
                            date = "2025-10-14", // "Hace 1 semana"
                            source = "El Comercio",
                            relatedTo = "Keiko Fujimori",
                            isVerified = false
                        ),
                        CurrentEventItem(
                            id = 203,
                            type = "documento",
                            title = "Candidata presenta plan de gobierno enfocado en reactivacion economica",
                            description = "Maria Elena Rodrigues presento su plan de gobierno con enfasis...",
                            date = "2025-09-21", // "Hace 1 mes"
                            source = "El Comercio",
                            relatedTo = "Keiko Fujimori",
                            isVerified = true
                        )
                    ))
=======
                    backgroundReport = BackgroundReport(
                        records = listOf(
                            BackgroundRecord("Acusación por Lavado de Activos", "Equipo Especial Lava Jato", "11/03/2021", "Acusación fiscal por los presuntos delitos de organización criminal, lavado de activos y otros en el marco del 'Caso Cócteles'. El juicio oral está pendiente de inicio.", listOf("Investigación", "Juicio Oral"), "doc_antecedente_2_1"),
                            BackgroundRecord("Obstrucción a la justicia", "Ministerio Público", "10/10/2018", "Prisión preventiva dictada en el marco de la investigación por presunta obstrucción a la justicia al interferir con testigos del caso.", listOf("Archivado"), "doc_antecedente_2_2")
                        )
                    )
>>>>>>> f0cdad359463fc8d12d70855d733c2060391744a
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
                    assetDeclaration = AssetDeclaration(5000000.0, listOf(
                        AssetItem("Inmuebles", 4000000.0, "#E53935")
                    )),
                    governmentPlan = GovernmentPlan("Énfasis en la educación como motor de desarrollo...", listOf(
                        ProposalItem("Educación", "Inversión del 6% del PBI en educación y becas.")
                    ), "plan_gobierno_3"),
                    academicFormation = AcademicFormation(listOf(
                        AcademicDegree("Doctor en Educación", "Universidad Complutense de Madrid (2000-2003)")
                    ), "sunedu_3"),
                    // --- DATOS DE TRAYECTORIA CORREGIDOS ---
                    careerHistory = CareerHistory(items = listOf(
                        CareerItem("Gobernador Regional de La Libertad", "2015-2018", "Impulsó proyectos de infraestructura y educación en la región."),
                        CareerItem("Alcalde de Trujillo", "2007-2014", "Realizó dos gestiones consecutivas en la alcaldía provincial."),
                        CareerItem("Congresista de la República", "2000-2001", "Participó en el Congreso durante el periodo de transición.")
                    )),
<<<<<<< HEAD
                    // [ANTECEDENTES ACTUALIZADO]
                    backgroundHistory = BackgroundHistory(items = listOf(
                        BackgroundItem(
                            title = "Denuncia por Plagio Académico",
                            description = "Denuncia por presunto plagio en tesis de doctorado (U. Complutense) y maestría (U. de Lima).",
                            entity = "SUNEDU / Universidades",
                            date = "2016",
                            status = "Archivado",
                            category = "Denuncia"
                        ),
                        BackgroundItem(
                            title = "Proceso Civil por Incumplimiento",
                            description = "Demanda por incumplimiento de contrato en una sociedad empresarial privada.",
                            entity = "Poder Judicial",
                            date = "20 nov. 2023",
                            status = "Vigente",
                            category = "Proceso Civil"
                        )
                    )),
                    // [ACTUALIDAD AÑADIDO]
                    currentEvents = CurrentEvents(items = listOf(
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
                    ))
=======
                    backgroundReport = BackgroundReport(
                        records = listOf(
                            BackgroundRecord("Investigación por Plagio Agravado", "Fiscalía de la Nación", "20/04/2016", "Investigación por el presunto plagio en su tesis doctoral. La universidad validó el grado, pero la investigación fiscal continuó en diversas etapas.", listOf("Investigación"), "doc_antecedente_3_1")
                        )
                    )
>>>>>>> f0cdad359463fc8d12d70855d733c2060391744a
                )
            ),
            // --- CANDIDATO 4: Carlos Álvarez ---
            Candidate(
                id = 4,
                name = "Carlos Álvarez",
                party = "País para Todos",
                position = "Presidencia",
                photoUrl = "https://statics.exitosanoticias.pe/2023/10/653bc125b24b1.png",
<<<<<<< HEAD
                profileDetails = null // Sigue sin datos detallados
            ),
            // --- CANDIDATO 5: Mario Vizcarra ---
=======
                profileDetails = null
            ),
            // --- CANDIDATO 5: Martín Vizcarra ---
>>>>>>> f0cdad359463fc8d12d70855d733c2060391744a
            Candidate(
                id = 5,
                name = "Martín Vizcarra",
                party = "Perú Primero",
                position = "Presidencia",
                photoUrl = "https://tse2.mm.bing.net/th/id/OIP.rEIcUIfKg887VmAIBBNYLwHaE8?rs=1&pid=ImgDetMain&o=7&rm=3",
                profileDetails = CandidateProfile(
                    basicInfo = BasicInfo("22/03/1963", "Moquegua", "62 años", "Casado", "Moquegua"),
                    assetDeclaration = AssetDeclaration(900000.0, listOf(
                        AssetItem("Inmuebles", 700000.0, "#E53935")
                    )),
                    governmentPlan = GovernmentPlan("Reforma del sistema político y lucha frontal contra la corrupción.", listOf(
                        ProposalItem("Reforma", "Eliminación de la inmunidad parlamentaria y bicameralidad.")
                    ), "plan_gobierno_5"),
                    academicFormation = AcademicFormation(listOf(
                        AcademicDegree("Ingeniería Civil", "Universidad Nacional de Ingeniería (1980-1985)")
                    ), "sunedu_5"),
                    // --- DATOS DE TRAYECTORIA CORREGIDOS ---
                    careerHistory = CareerHistory(items = listOf(
                        CareerItem("Presidente de la República", "2018-2020", "Asumió la presidencia tras la renuncia de Pedro Pablo Kuczynski."),
                        CareerItem("Gobernador Regional de Moquegua", "2011-2014", "Reconocido por su gestión en el sector educativo de la región.")
                    )),
<<<<<<< HEAD
                    // [ANTECEDENTES ACTUALIZADO]
                    backgroundHistory = BackgroundHistory(items = listOf(
                        BackgroundItem(
                            title = "Inhabilitación por Caso 'Vacunagate'",
                            description = "Inhabilitación de la función pública por 10 años por el Congreso de la República.",
                            entity = "Congreso de la República",
                            date = "abr. 2021",
                            status = "Vigente",
                            category = "Sanción"
                        ),
                        BackgroundItem(
                            title = "Investigación 'Club de la Construcción'",
                            description = "Investigación por supuesta recepción de sobornos por obras públicas cuando era Gobernador de Moquegua.",
                            entity = "Fiscalía de la Nación",
                            date = "2020",
                            status = "Vigente",
                            category = "Investigación"
                        )
                    )),
                    // [ACTUALIDAD AÑADIDO]
                    currentEvents = CurrentEvents(items = listOf(
                        CurrentEventItem(
                            id = 501,
                            type = "actividad",
                            title = "Vizcarra apela inhabilitación ante TC",
                            description = "Defensa legal del candidato presentó un recurso ante el Tribunal Constitucional para poder postular en las elecciones 2026.",
                            date = "2025-10-10",
                            source = "Canal N",
                            relatedTo = "Mario Vizcarra",
                            isVerified = true
                        )
                    ))
=======
                    backgroundReport = BackgroundReport(
                        records = listOf(
                            BackgroundRecord("Inhabilitación por 10 años", "Congreso de la República", "17/04/2021", "El Congreso lo inhabilitó para ejercer cargos públicos por su participación en el caso 'Vacunagate', por vacunación irregular contra la COVID-19.", listOf("Sentenciado"), "doc_antecedente_5_1")
                        )
                    )
>>>>>>> f0cdad359463fc8d12d70855d733c2060391744a
                )
            )
        )
    }

    fun getPositions(): List<String> {
        return listOf("Todos", "Presidencia", "Congreso", "Alcaldía", "Gobernador")
    }

    fun getPoliticalParties(): List<String> {
        return listOf(
            "Todos", "Renovación Popular", "Fuerza Popular", "Alianza para el Progreso", "País para Todos", "Perú Primero"
        )
    }
}