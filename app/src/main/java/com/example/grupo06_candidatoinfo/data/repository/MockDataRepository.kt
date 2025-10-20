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

object MockDataRepository {

    fun getElectionTypes(): List<ElectionType> {
        return listOf(
            ElectionType(1, "Elecciones Generales 2026")
        )
    }

    fun getCandidates(): List<Candidate> {
        return listOf(
            // --- CANDIDATO 1 ---
            Candidate(
                id = 1,
                name = "Rafael López Aliaga",
                party = "Renovación Popular",
                position = "Presidencia",
                photoUrl = "https://tse1.mm.bing.net/th/id/OIP.aAFAdIKomuFf5FSyxibhMQHaHa?rs=1&pid=ImgDetMain&o=7&rm=3",
                profileDetails = CandidateProfile(
                    basicInfo = BasicInfo(
                        birthDate = "11/02/1961",
                        birthPlace = "Lima",
                        age = "64 años",
                        civilStatus = "Soltero",
                        residencePlace = "Lima"
                    ),
                    assetDeclaration = AssetDeclaration(
                        totalPatrimony = 2500000.0,
                        assets = listOf(
                            AssetItem("Inmuebles", 1800000.0, "#E53935"),
                            AssetItem("Vehículos", 300000.0, "#3C3472"),
                            AssetItem("Ingresos anuales (último)", 400000.0, "#6E6E6E")
                        )
                    ),
                    governmentPlan = GovernmentPlan(
                        summary = "Plan de gobierno centrado en la inversión privada, seguridad ciudadana y lucha contra la corrupción.",
                        proposals = listOf(
                            ProposalItem("Economía", "Fomento de la inversión y reducción de la burocracia."),
                            ProposalItem("Seguridad", "Implementación de sistema de 'policía de barrio' y tecnología."),
                            ProposalItem("Salud", "Construcción de 5 hospitales de alta complejidad.")
                        ),
                        documentId = "plan_gobierno_1"
                    ),
                    academicFormation = AcademicFormation(
                        degrees = listOf(
                            AcademicDegree("Maestría en Administración", "Universidad de Piura (1990-1992)"),
                            AcademicDegree("Ingeniería Industrial", "Universidad de Piura (1979-1984)")
                        ),
                        documentId = "sunedu_1"
                    ),
                    careerHistory = CareerHistory(items = listOf(
                        CareerItem("Alcalde de Lima", "2023-Presente", "Gestión municipal enfocada en seguridad, transporte y programas sociales."),
                        CareerItem("Regidor de Lima", "2007-2010", "Participó en comisiones de desarrollo urbano y transporte."),
                        CareerItem("Presidente del Directorio", "1996-Presente", "Fundador y líder de PeruRail, impulsando el turismo en Cusco.")
                    ))
                )
            ),
            // --- CANDIDATO 2 ---
            Candidate(
                id = 2,
                name = "Keiko Fujimori",
                party = "Fuerza Popular",
                position = "Presidencia",
                photoUrl = "https://tvperu.gob.pe/sites/default/files/000778186w.jpg",
                profileDetails = CandidateProfile(
                    basicInfo = BasicInfo(
                        birthDate = "25/05/1975",
                        birthPlace = "Lima",
                        age = "50 años",
                        civilStatus = "Casada",
                        residencePlace = "Lima"
                    ),
                    assetDeclaration = AssetDeclaration(
                        totalPatrimony = 1100000.0,
                        assets = listOf(
                            AssetItem("Inmuebles", 800000.0, "#E53935"),
                            AssetItem("Vehículos", 150000.0, "#3C3472"),
                            AssetItem("Ingresos anuales (último)", 150000.0, "#6E6E6E")
                        )
                    ),
                    governmentPlan = GovernmentPlan(
                        summary = "Propuesta de reactivación económica 'Plan Rescate 2026' y 'mano dura' contra la delincuencia.",
                        proposals = listOf(
                            ProposalItem("Economía", "Incentivos fiscales para PYMEs y atracción de inversión."),
                            ProposalItem("Seguridad", "Construcción de más penales y fortalecimiento de la policía."),
                            ProposalItem("Educación", "Reforma educativa con enfoque en infraestructura y tecnología.")
                        ),
                        documentId = "plan_gobierno_2"
                    ),
                    academicFormation = AcademicFormation(
                        degrees = listOf(
                            AcademicDegree("Master in Business Administration", "Columbia University (2004-2006)"),
                            AcademicDegree("Bachelor of Science", "Boston University (1993-1997)")
                        ),
                        documentId = "sunedu_2"
                    ),
                    careerHistory = CareerHistory(items = listOf(
                        CareerItem("Candidata a la Presidencia", "2011, 2016, 2021", "Lideró el partido Fuerza Popular en tres elecciones generales."),
                        CareerItem("Congresista de la República", "2006-2011", "Fue la congresista más votada en las elecciones de 2006.")
                    ))
                )
            ),
            // --- CANDIDATO 3 ---
            Candidate(
                id = 3,
                name = "César Acuña",
                party = "Alianza para el Progreso",
                position = "Presidencia",
                photoUrl = "https://portal.andina.pe/EDPfotografia3/Thumbnail/2021/03/29/000761739W.jpg",
                profileDetails = CandidateProfile(
                    basicInfo = BasicInfo(
                        birthDate = "11/08/1952",
                        birthPlace = "Cajamarca",
                        age = "73 años",
                        civilStatus = "Casado",
                        residencePlace = "Lima"
                    ),
                    assetDeclaration = AssetDeclaration(
                        totalPatrimony = 5000000.0,
                        assets = listOf(
                            AssetItem("Inmuebles", 4000000.0, "#E53935"),
                            AssetItem("Vehículos", 500000.0, "#3C3472"),
                            AssetItem("Ingresos anuales (último)", 500000.0, "#6E6E6E")
                        )
                    ),
                    governmentPlan = GovernmentPlan(
                        summary = "Énfasis en la educación como motor de desarrollo y descentralización económica.",
                        proposals = listOf(
                            ProposalItem("Educación", "Inversión del 6% del PBI en educación y becas."),
                            ProposalItem("Salud", "Hospitales especializados en cada región."),
                            ProposalItem("Economía", "Impulso a la agroindustria y obras de infraestructura.")
                        ),
                        documentId = "plan_gobierno_3"
                    ),
                    academicFormation = AcademicFormation(
                        degrees = listOf(
                            AcademicDegree("Doctor en Educación", "Universidad Complutense de Madrid (2000-2003)"),
                            AcademicDegree("Ingeniería Química", "Universidad Nacional de Trujillo (1970-1975)")
                        ),
                        documentId = "sunedu_3"
                    ),
                    careerHistory = CareerHistory(items = listOf(
                        CareerItem("Gobernador Regional de La Libertad", "2015-2018", "Impulsó proyectos de infraestructura y educación en la región."),
                        CareerItem("Alcalde de Trujillo", "2007-2014", "Realizó dos gestiones consecutivas en la alcaldía provincial."),
                        CareerItem("Congresista de la República", "2000-2001", "Participó en el Congreso durante el periodo de transición.")
                    ))
                )
            ),
            // --- CANDIDATO 4 ---
            Candidate(
                id = 4,
                name = "Carlos Álvarez",
                party = "País para Todos",
                position = "Presidencia",
                photoUrl = "https://statics.exitosanoticias.pe/2023/10/653bc125b24b1.png",
                profileDetails = null // Este candidato sigue sin datos detallados
            ),
            // --- CANDIDATO 5 ---
            Candidate(
                id = 5,
                name = "Mario Vizcarra",
                party = "Perú Primero",
                position = "Presidencia",
                photoUrl = "https://tse2.mm.bing.net/th/id/OIP.rEIcUIfKg887VmAIBBNYLwHaE8?rs=1&pid=ImgDetMain&o=7&rm=3",
                profileDetails = CandidateProfile(
                    basicInfo = BasicInfo(
                        birthDate = "22/03/1963",
                        birthPlace = "Moquegua",
                        age = "62 años",
                        civilStatus = "Casado",
                        residencePlace = "Moquegua"
                    ),
                    assetDeclaration = AssetDeclaration(
                        totalPatrimony = 900000.0,
                        assets = listOf(
                            AssetItem("Inmuebles", 700000.0, "#E53935"),
                            AssetItem("Vehículos", 100000.0, "#3C3472"),
                            AssetItem("Ingresos anuales (último)", 100000.0, "#6E6E6E")
                        )
                    ),
                    governmentPlan = GovernmentPlan(
                        summary = "Reforma del sistema político y lucha frontal contra la corrupción.",
                        proposals = listOf(
                            ProposalItem("Reforma", "Eliminación de la inmunidad parlamentaria y bicameralidad."),
                            ProposalItem("Infraestructura", "Cierre de brechas en agua y saneamiento.")
                        ),
                        documentId = "plan_gobierno_5"
                    ),
                    academicFormation = AcademicFormation(
                        degrees = listOf(
                            AcademicDegree("Ingeniería Civil", "Universidad Nacional de Ingeniería (1980-1985)")
                        ),
                        documentId = "sunedu_5"
                    ),
                    careerHistory = CareerHistory(items = listOf(
                        CareerItem("Presidente de la República", "2018-2020", "Asumió la presidencia tras la renuncia de Pedro Pablo Kuczynski."),
                        CareerItem("Primer Vicepresidente", "2016-2018", "Electo en la plancha presidencial de Peruanos por el Kambio."),
                        CareerItem("Gobernador Regional de Moquegua", "2011-2014", "Reconocido por su gestión en el sector educativo de la región.")
                    ))
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

