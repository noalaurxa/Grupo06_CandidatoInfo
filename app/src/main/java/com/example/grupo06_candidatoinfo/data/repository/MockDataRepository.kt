package com.example.grupo06_candidatoinfo.data.repository

import com.example.grupo06_candidatoinfo.model.*

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
                        CareerItem("Presidente del Directorio en PeruRail S.A.", "1996-Presente", "Fundador y líder de PeruRail, impulsando el turismo en Cusco.")
                    )),
                    backgroundReport = BackgroundReport(
                        records = listOf(
                            BackgroundRecord("Investigación por Lavado de Activos", "Ministerio Público", "15/05/2021", "Investigación preliminar en el marco del caso 'Panama Papers'. El caso se encuentra en etapa de investigación y no cuenta con sentencia.", listOf("Investigación"), "doc_antecedente_1")
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
                    backgroundReport = BackgroundReport(
                        records = listOf(
                            BackgroundRecord("Acusación por Lavado de Activos", "Equipo Especial Lava Jato", "11/03/2021", "Acusación fiscal por los presuntos delitos de organización criminal, lavado de activos y otros en el marco del 'Caso Cócteles'. El juicio oral está pendiente de inicio.", listOf("Investigación", "Juicio Oral"), "doc_antecedente_2_1"),
                            BackgroundRecord("Obstrucción a la justicia", "Ministerio Público", "10/10/2018", "Prisión preventiva dictada en el marco de la investigación por presunta obstrucción a la justicia al interferir con testigos del caso.", listOf("Archivado"), "doc_antecedente_2_2")
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
                    backgroundReport = BackgroundReport(
                        records = listOf(
                            BackgroundRecord("Investigación por Plagio Agravado", "Fiscalía de la Nación", "20/04/2016", "Investigación por el presunto plagio en su tesis doctoral. La universidad validó el grado, pero la investigación fiscal continuó en diversas etapas.", listOf("Investigación"), "doc_antecedente_3_1")
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
                profileDetails = null
            ),
            // --- CANDIDATO 5: Martín Vizcarra ---
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
                    backgroundReport = BackgroundReport(
                        records = listOf(
                            BackgroundRecord("Inhabilitación por 10 años", "Congreso de la República", "17/04/2021", "El Congreso lo inhabilitó para ejercer cargos públicos por su participación en el caso 'Vacunagate', por vacunación irregular contra la COVID-19.", listOf("Sentenciado"), "doc_antecedente_5_1")
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
            "Todos", "Renovación Popular", "Fuerza Popular", "Alianza para el Progreso", "País para Todos", "Perú Primero"
        )
    }
}

