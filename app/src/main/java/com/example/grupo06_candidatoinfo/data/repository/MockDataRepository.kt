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
import com.example.grupo06_candidatoinfo.model.CaseTimelineEvent
import com.example.grupo06_candidatoinfo.model.CurrentEvents
import com.example.grupo06_candidatoinfo.model.CurrentEventItem
import com.example.grupo06_candidatoinfo.model.InvestigationDetail
import com.example.grupo06_candidatoinfo.model.InvolvedParty
import com.example.grupo06_candidatoinfo.model.NewsDetail // Modelo para Detalle de Noticias
import com.example.grupo06_candidatoinfo.model.OfficialDocument

/**
 * Repositorio que proporciona datos simulados (Mock Data) para la aplicación.
 * Contiene la lógica para obtener la lista de candidatos, tipos de elección
 * y el detalle de noticias/eventos.
 */
object MockDataRepository {

    // --------------------------------------------------------------------
    // --- DATOS GENERALES DE ELECCIONES Y CANDIDATOS ---
    // --------------------------------------------------------------------
    fun getElectionTypes(): List<ElectionType> {
        return listOf(
            ElectionType(1, "Elecciones Generales 2026")
        )
    }

    fun getCandidates(): List<Candidate> {
        return listOf(
            // --- CANDIDATO 1: Rafael López Aliaga (MOCK DETALLADO COMPLETO) ---
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
                        summary = "Plan de gobierno centrado en la inversión privada, seguridad ciudadana y programas sociales directos.",
                        proposals = listOf(
                            ProposalItem(
                                "Economía",
                                "Fomento de la inversión y reducción de la burocracia para la creación de 2 millones de empleos."
                            ),
                            ProposalItem(
                                "Seguridad",
                                "Implementación de sistema de 'policía de barrio' y tecnología de vigilancia avanzada."
                            ),
                            ProposalItem(
                                "Social",
                                "Lanzamiento del 'Plan Cero Hambre' enfocado en la lucha contra la desnutrición infantil."
                            )
                        ),
                        documentId = "plan_gobierno_1",
                        documentUrl = "https://declara.jne.gob.pe/ASSETS/PLANGOBIERNO/FILEPLANGOBIERNO/16482.pdf" // <-- ADDED
                    ),
                    academicFormation = AcademicFormation(
                        listOf(
                            AcademicDegree(
                                "Maestría en Administración de Empresas (MBA)",
                                "Universidad de Piura (1990-1992)"
                            ),
                            AcademicDegree(
                                "Ingeniería Industrial",
                                "Universidad Nacional de Ingeniería (UNI) (1979-1984)"
                            ),
                            AcademicDegree(
                                "Programa de Alta Dirección (PAD)",
                                "IESE Business School, Barcelona"
                            )
                        ),
                        "sunedu_1"
                    ),
                    careerHistory = CareerHistory(
                        items = listOf(
                            CareerItem(
                                "Alcalde Metropolitano de Lima",
                                "2023-Presente",
                                "Gestión municipal enfocada en seguridad, transporte y programas sociales. Liderazgo en el sector público."
                            ),
                            CareerItem(
                                "Presidente del Directorio",
                                "Grupo PeruRail (1999-2022)",
                                "Liderazgo y gestión en el sector ferroviario y turismo. Expansión de rutas y servicios."
                            ),
                            CareerItem(
                                "Gerente General",
                                "Banco de Comercio (1995-1999)",
                                "Responsable de la dirección estratégica y operativa del banco."
                            )
                        )
                    ),
                    backgroundReport = BackgroundReport(
                        records = listOf(
                            BackgroundRecord(
                                title = "Investigación por Lavado de Activos",
                                entity = "Ministerio Público (Fiscalía de la Nación)",
                                date = "15/05/2021",
                                description = "Investigación preliminar en el marco del caso 'Panama Papers' por presuntos aportes no justificados. El caso está en etapa de indagación.",
                                statusTags = listOf("Activo", "Investigación Preliminar"),
                                classificationTags = listOf("Lavado de Activos", "Finanzas"),
                                documentId = "doc_antecedente_1_1"
                            ),
                            BackgroundRecord(
                                title = "Proceso Administrativo por Deudas Tributarias",
                                entity = "Superintendencia Nacional de Aduanas y de Administración Tributaria (SUNAT)",
                                date = "01/03/2019",
                                description = "Deuda tributaria de una de sus empresas, actualmente en proceso de fraccionamiento y pago.",
                                statusTags = listOf("En Proceso de Pago", "Administrativo"),
                                classificationTags = listOf("Tributario", "Finanzas"),
                                documentId = "doc_antecedente_1_2"
                            )
                        )
                    ),
                    currentEvents = CurrentEvents(
                        items = listOf(
                            CurrentEventItem(
                                id = 101,
                                type = "noticia",
                                title = "López Aliaga anuncia 'Plan Cero Hambre'",
                                description = "El candidato presentó su plan de gobierno con énfasis en la lucha contra la desnutrición.",
                                date = "2025-10-18",
                                source = "El Comercio",
                                relatedTo = "Rafael López Aliaga",
                                isVerified = true,
                                documentId = "doc_noticia_101" // ID VÁLIDO
                            ),
                            CurrentEventItem(
                                id = 102,
                                type = "actividad",
                                title = "Mitin de cierre en Plaza San Martín",
                                description = "Realizó un evento masivo en el centro de Lima.",
                                date = "2025-10-14",
                                source = "RPP Noticias",
                                relatedTo = "Rafael López Aliaga",
                                isVerified = false,
                                documentId = "doc_actividad_102" // ID VÁLIDO
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
                    assetDeclaration = AssetDeclaration(1100000.0, listOf(
                        AssetItem("Bienes Inmuebles", 550000.0, "#388E3C"),
                        AssetItem("Cuentas Bancarias", 550000.0, "#00796B")
                    )),
                    governmentPlan = GovernmentPlan(
                        summary = "Propuesta de reactivación económica y reforma política.",
                        proposals = listOf(
                            ProposalItem("Reforma Judicial", "Creación de una comisión de alto nivel para depurar el sistema de justicia."),
                            ProposalItem("Economía", "Incentivos fiscales para la inversión en regiones.")
                        ),
                        documentId = "plan_gobierno_2",
                        documentUrl = "https://apisije-e.jne.gob.pe/TRAMITE/ESCRITO/1095/ARCHIVO/FIRMADO/3017.PDF"
                    ),
                    academicFormation = AcademicFormation(listOf(
                        AcademicDegree("Maestría en Administración de Negocios (MBA)", "Columbia University (2000-2002)"),
                        AcademicDegree("Bachiller en Administración de Empresas", "Boston University (1993-1997)")
                    ), "sunedu_2"),
                    careerHistory = CareerHistory(listOf(
                        CareerItem("Congresista de la República", "2006-2011", "Representante por Lima. Presidenta de la Comisión de Fiscalización.")
                    )),
                    backgroundReport = BackgroundReport(listOf(
                        BackgroundRecord(
                            title = "Investigación por Aportes de Campaña",
                            entity = "Ministerio Público",
                            date = "20/09/2018",
                            description = "Investigación por presuntos aportes ilícitos a la campaña presidencial de 2011 y 2016 (Caso Cócteles).",
                            statusTags = listOf("Activo", "Investigación Formalizada"),
                            classificationTags = listOf("Crimen Organizado", "Financiamiento Ilegal"),
                            documentId = "doc_antecedente_2_1"
                        )
                    )),
                    currentEvents = CurrentEvents(
                        items = listOf(
                            CurrentEventItem(
                                id = 201,
                                type = "noticia",
                                title = "Fujimori presenta plan de gobierno enfocado en reactivacion economica",
                                description = "La candidata detalló su plan de reactivación económica.",
                                date = "2025-10-18",
                                source = "El Comercio",
                                relatedTo = "Keiko Fujimori",
                                isVerified = true,
                                documentId = "doc_noticia_201" // ID VÁLIDO
                            ),
                            CurrentEventItem(
                                id = 203,
                                type = "documento",
                                title = "Fuerza Popular publica versión final de plan de gobierno",
                                description = "El partido publicó la versión consolidada del plan de gobierno ante el JNE.",
                                date = "2025-09-21",
                                source = "JNE",
                                relatedTo = "Keiko Fujimori",
                                isVerified = true,
                                documentId = "doc_documento_203" // ID VÁLIDO
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
                    assetDeclaration = AssetDeclaration(5000000.0, listOf(
                        AssetItem("Empresas y Acciones", 3500000.0, "#FFC107"),
                        AssetItem("Inmuebles", 1500000.0, "#FF9800")
                    )),
                    governmentPlan = GovernmentPlan(
                        summary = "Énfasis en la educación y descentralización del país.",
                        proposals = listOf(
                            ProposalItem("Educación", "Inversión del 6% del PBI y creación de universidades tecnológicas en cada región."),
                            ProposalItem("Descentralización", "Mayor autonomía a gobiernos regionales y locales.")
                        ),
                        documentId = "plan_gobierno_3",
                        documentUrl = "https://mpesije.jne.gob.pe/docs/1632dad2-4408-461c-b5e0-ba87a0c19027.pdf" // <-- ADDED
                    ),
                    academicFormation = AcademicFormation(listOf(
                        AcademicDegree("Doctor en Educación", "Universidad Complutense de Madrid"),
                        AcademicDegree("Maestría en Dirección Universitaria", "Universidad de Los Andes")
                    ), "sunedu_3"),
                    careerHistory = CareerHistory(listOf(
                        CareerItem("Gobernador Regional de La Libertad", "2019-Presente", "Gestión regional con enfoque en infraestructura y salud."),
                        CareerItem("Alcalde de Trujillo", "1999-2006", "Dos periodos de gestión municipal en Trujillo.")
                    )),
                    backgroundReport = BackgroundReport(listOf(
                        BackgroundRecord(
                            title = "Denuncia por Plagio",
                            entity = "Comisión de Ética del Congreso",
                            date = "20/01/2016",
                            description = "Acusación por plagio en tesis doctoral y otros trabajos académicos. Generó polémica pública.",
                            statusTags = listOf("Cerrado", "Polémico"),
                            classificationTags = listOf("Ética", "Académico"),
                            documentId = "doc_antecedente_3_1"
                        )
                    )),
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
                                isVerified = false,
                                documentId = "doc_noticia_301" // ID VÁLIDO
                            )
                        )
                    )
                )
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
                        AssetItem("Inmuebles", 700000.0, "#03A9F4"),
                        AssetItem("Cuentas Bancarias", 200000.0, "#00BCD4")
                    )),
                    governmentPlan = GovernmentPlan(
                        summary = "Reforma del sistema político y lucha contra la corrupción.",
                        proposals = listOf(
                            ProposalItem("Política", "Reforma constitucional para limitar la inmunidad parlamentaria."),
                            ProposalItem("Salud", "Fortalecimiento del sistema de salud pública post-pandemia.")
                        ),
                        documentId = "plan_gobierno_5",
                        documentUrl = "https://apisije-e.jne.gob.pe/TRAMITE/ESCRITO/1859/ARCHIVO/FIRMADO/7346.PDF" // <-- ADDED
                    ),
                    academicFormation = AcademicFormation(listOf(
                        AcademicDegree("Ingeniería Civil", "Universidad Nacional de Ingeniería (UNI) (1981-1986)")
                    ), "sunedu_5"),
                    careerHistory = CareerHistory(listOf(
                        CareerItem("Presidente de la República", "2018-2020", "Gobierno enfocado en la lucha anticorrupción y reforma política."),
                        CareerItem("Ministro de Transportes y Comunicaciones", "2016-2017", "Gestión de proyectos de infraestructura a nivel nacional.")
                    )),
                    backgroundReport = BackgroundReport(listOf(
                        BackgroundRecord(
                            title = "Inhabilitación Política",
                            entity = "Congreso de la República",
                            date = "16/04/2021",
                            description = "Inhabilitado por 10 años para ejercer cargos públicos por el caso 'Vacunagate' (vacunación irregular).",
                            statusTags = listOf("Inhabilitado", "Sanción"),
                            classificationTags = listOf("Corrupción", "Función Pública"),
                            documentId = "doc_antecedente_5_1"
                        )
                    )),
                    currentEvents = CurrentEvents(
                        items = listOf(
                            CurrentEventItem(
                                id = 501,
                                type = "actividad",
                                title = "Vizcarra apela inhabilitación ante TC",
                                description = "Defensa legal del candidato presentó un recurso ante el Tribunal Constitucional.",
                                date = "2025-10-10",
                                source = "Canal N",
                                relatedTo = "Martín Vizcarra",
                                isVerified = true,
                                documentId = "doc_actividad_501" // ID VÁLIDO
                            )
                        )
                    )
                )
            )
        )
    }

    // --------------------------------------------------------------------
    // --- FUNCIÓN CRÍTICA PARA EL DETALLE DE NOTICIAS ---
    // --------------------------------------------------------------------
    fun getNewsDetail(documentId: String): NewsDetail? {
        return when (documentId) {
            // Rafael López Aliaga (RLA)
            "doc_noticia_101" -> NewsDetail(
                title = "López Aliaga anuncia 'Plan Cero Hambre'",
                summary = "El candidato anunció un ambicioso plan de ayuda social en zonas vulnerables como parte de su propuesta de gobierno.",
                date = "18 de octubre de 2025",
                source = "El Comercio",
                relatedCandidateName = "Rafael López Aliaga",
                fullDescription = "El candidato de Renovación Popular presentó su 'Plan Cero Hambre', enfocado en la lucha contra la desnutrición en las zonas más vulnerables de Lima. La propuesta incluye la creación de nuevos comedores populares, la alianza estratégica con la empresa privada para la donación masiva de alimentos y la implementación de un padrón de beneficiarios transparente y digitalizado. Busca reducir el impacto de la crisis económica en las familias de bajos recursos y asegurar la alimentación básica.",
                isVerified = true,
                imageUrl = "https://tse2.mm.bing.net/th/id/OIP.x5tI1QlTquTNVm1kdFyKBQHaEK?cb=12&rs=1&pid=ImgDetMain&o=7&rm=3",
                sourceUrl = "https://elcomercio.pe/politica/lopez-aliaga-plan-cero-hambre-noticia/" // <-- ENLACE AÑADIDO
            )
            "doc_actividad_102" -> NewsDetail(
                title = "Mitin de cierre en Plaza San Martín",
                summary = "Evento de fin de campaña en el centro de Lima con la participación de candidatos al Congreso.",
                date = "14 de octubre de 2025",
                source = "RPP Noticias",
                relatedCandidateName = "Rafael López Aliaga",
                fullDescription = "López Aliaga realizó un evento masivo en el centro de Lima, presentando a sus candidatos al congreso. El mitin estuvo centrado en temas de seguridad ciudadana, prometiendo la compra de nuevas motocicletas para la policía municipal y la reactivación de proyectos de infraestructura en la capital, destacando su experiencia en la gestión pública reciente.",
                isVerified = false,
                imageUrl = "https://ojo.pe/resizer/RdDYhL73JIUveYaUOSoXpiWvbLg=/580x330/smart/filters:format(jpeg):quality(75)/cloudfront-us-east-1.images.arcpublishing.com/elcomercio/JHTWBC75RVFABPKXBJMWVGBNTY.jpg",
                sourceUrl = "https://rpp.pe/actualidad/mitin-rla-san-martin-cierre-campana" // <-- ENLACE AÑADIDO
            )

            // Keiko Fujimori (KF)
            "doc_noticia_201" -> NewsDetail(
                title = "Fujimori presenta plan de gobierno enfocado en reactivación económica",
                summary = "Keiko Fujimori detalla su estrategia post-pandemia en un evento con empresarios y gremios.",
                date = "18 de octubre de 2025",
                source = "Perú21",
                relatedCandidateName = "Keiko Fujimori",
                fullDescription = "La candidata de Fuerza Popular presentó su plan de gobierno con énfasis en la reactivación económica post-pandemia, incluyendo medidas de apoyo a las MiPymes y generación de empleo formal. El plan incluye la creación de un fondo de garantías para microempresas, la reducción de impuestos temporales para los sectores más afectados y la simplificación de trámites para nuevos negocios.",
                isVerified = true,
                imageUrl = "https://mf.b37mrtl.ru/actualidad/public_images/2021.07/original/60faa33959bf5b65296f2f33.jpg",
                sourceUrl = "https://peru21.pe/politica/keiko-plan-reactivacion-economica-2026-noticia/" // <-- ENLACE AÑADIDO
            )
            "doc_documento_203" -> NewsDetail(
                title = "Fuerza Popular publica versión final de plan de gobierno",
                summary = "Plan oficial del partido presentado ante el JNE, cumpliendo con los plazos electorales.",
                date = "21 de septiembre de 2025",
                source = "JNE",
                relatedCandidateName = "Keiko Fujimori",
                fullDescription = "El partido Fuerza Popular publicó la versión consolidada del plan de gobierno ante el Jurado Nacional de Elecciones (JNE). El documento, de más de 200 páginas, detalla las propuestas en seguridad, economía y justicia, con un capítulo especial dedicado a la reforma del Estado y la lucha contra la corrupción estructural.",
                isVerified = true,
                imageUrl = "https://imgv2-2-f.scribdassets.com/img/document/388126415/original/405c3d0ee1/1726092831?v=1",
                sourceUrl = "https://portal.jne.gob.pe/plan-gobierno/fuerza-popular-version-final-2026" // <-- ENLACE AÑADIDO (JNE es la fuente)
            )

            // César Acuña (CA)
            "doc_noticia_301" -> NewsDetail(
                title = "Acuña promete 'Educación como Revolución'",
                summary = "Propuesta central del candidato de APP con enfoque en inversión educativa y becas nacionales.",
                date = "17 de octubre de 2025",
                source = "La República",
                relatedCandidateName = "César Acuña",
                fullDescription = "El candidato de Alianza para el Progreso (APP) centró su discurso en la inversión del 6% del PBI para el sector educativo, prometiendo la creación de nuevas universidades tecnológicas y un programa masivo de becas 'Beca 18 Plus' para estudiantes de bajos recursos, buscando reducir la brecha educativa entre la costa y la sierra del país. La propuesta fue presentada en su tierra natal, Cajamarca.",
                isVerified = false,
                imageUrl = "https://macronorte.pe/wp-content/uploads/2023/03/WhatsApp-Image-2023-03-07-at-21.24.12.jpg",
                sourceUrl = "https://larepublica.pe/elecciones/acuna-educacion-revolucion-pbi-noticia/" // <-- ENLACE AÑADIDO
            )

            // Martín Vizcarra (MV)
            "doc_actividad_501" -> NewsDetail(
                title = "Vizcarra apela inhabilitación ante TC",
                summary = "Busca revertir la inhabilitación impuesta por el Congreso para poder participar en las próximas elecciones.",
                date = "10 de octubre de 2025",
                source = "Canal N",
                relatedCandidateName = "Martín Vizcarra",
                fullDescription = "La defensa legal del expresidente presentó un recurso de agravio constitucional ante el Tribunal Constitucional (TC) para revertir la inhabilitación por 10 años impuesta por el Congreso. Argumentan una violación al debido proceso y a sus derechos políticos en la decisión que lo sancionó por el caso 'Vacunagate'. El TC admitió a trámite el recurso y se espera una decisión en las próximas semanas, lo cual es clave para su candidatura.",
                isVerified = true,
                imageUrl = "https://tse1.mm.bing.net/th/id/OIP.ixCiEaiAAcJlaxUaUBSqUgHaD4?cb=12&w=1200&h=630&rs=1&pid=ImgDetMain&o=7&rm=3",
                sourceUrl = "https://canaln.pe/actualidad/vizcarra-apela-inhabilitacion-tribunal-constitucional-tc-noticia/" // <-- ENLACE AÑADIDO
            )

            else -> null // Retorna null si el ID no es encontrado
        }
    }

    // --------------------------------------------------------------------
    // --- FUNCIÓN CRÍTICA PARA EL DETALLE DE INVESTIGACIÓN ---
    // --------------------------------------------------------------------
    fun getInvestigationDetail(documentId: String): InvestigationDetail? {
        return when (documentId) {
            // --- RLA: doc_antecedente_1_1 (Lavado de Activos) ---
            "doc_antecedente_1_1" -> InvestigationDetail(
                caseTitle = "Investigación por Lavado de Activos",
                caseEntity = "Ministerio Público (Fiscalía de la Nación)",
                caseDate = "15/05/2021",
                status = "Activo - En Indagación Preliminar",
                timeline = listOf(
                    CaseTimelineEvent(
                        date = "15/05/2021",
                        title = "Apertura de Investigación Preliminar",
                        description = "La Fiscalía inicia indagación en el marco de 'Panama Papers' por presuntos aportes y patrimonio no justificados."
                    ),
                    CaseTimelineEvent(
                        date = "10/08/2022",
                        title = "Requisitoria Documental",
                        description = "La fiscalía solicita levantamiento del secreto bancario a sus empresas relacionadas."
                    ),
                    CaseTimelineEvent(
                        date = "01/03/2023",
                        title = "Declaración del Investigado",
                        description = "Rafael López Aliaga brinda su testimonio ante el despacho fiscal."
                    )
                ),
                officialDocuments = listOf(
                    OfficialDocument(title = "Resolución Fiscal N° 001 - Inicio de Diligencias", documentUrl = "https://fiscalia.gob.pe/res-rla-001"),
                    OfficialDocument(title = "Requerimiento de Información Bancaria", documentUrl = "https://fiscalia.gob.pe/req-info-banco")
                ),
                involvedParties = listOf(
                    InvolvedParty(name = "Rafael López Aliaga", role = "Investigado Principal"),
                    InvolvedParty(name = "Empresas del Grupo", role = "Terceros Involucrados")
                ),
            )

            // --- RLA: doc_antecedente_1_2 (Deudas Tributarias) ---
            "doc_antecedente_1_2" -> InvestigationDetail(
                caseTitle = "Proceso Administrativo por Deudas Tributarias",
                caseEntity = "Superintendencia Nacional de Aduanas y de Administración Tributaria (SUNAT)",
                caseDate = "01/03/2019",
                status = "En Proceso de Fraccionamiento y Pago",
                timeline = listOf(
                    CaseTimelineEvent(
                        date = "01/03/2019",
                        title = "Emisión de Resolución de Cobranza",
                        description = "SUNAT emite resolución por deuda pendiente de una de las empresas por Impuesto a la Renta."
                    ),
                    CaseTimelineEvent(
                        date = "15/05/2019",
                        title = "Solicitud de Fraccionamiento",
                        description = "La defensa legal presenta solicitud de fraccionamiento de la deuda."
                    ),
                    CaseTimelineEvent(
                        date = "01/07/2019",
                        title = "Aprobación de Fraccionamiento",
                        description = "SUNAT aprueba el plan de pagos por 36 meses."
                    )
                ),
                officialDocuments = listOf(
                    OfficialDocument(title = "Resolución de Deuda SUNAT", documentUrl = "https://sunat.gob.pe/res-deuda-rla"),
                    OfficialDocument(title = "Acuerdo de Fraccionamiento N° 456-2019", documentUrl = "https://sunat.gob.pe/acuerdo-rla")
                ),
                involvedParties = listOf(
                    InvolvedParty(name = "Empresa X de RLA", role = "Deudor Principal")
                ),

            )

            // --- KF: doc_antecedente_2_1 (Aportes de Campaña / Cócteles) ---
            "doc_antecedente_2_1" -> InvestigationDetail(
                caseTitle = "Investigación por Aportes de Campaña (Caso Cócteles)",
                caseEntity = "Ministerio Público (Fiscalía de la Nación)",
                caseDate = "20/09/2018",
                status = "Investigación Formalizada",
                timeline = listOf(
                    CaseTimelineEvent(
                        date = "20/09/2018",
                        title = "Inicio de la Investigación",
                        description = "La Fiscalía inicia indagación por presuntos aportes ilícitos a la campaña presidencial de 2011 y 2016."
                    ),
                    CaseTimelineEvent(
                        date = "31/10/2018",
                        title = "Orden de Prisión Preventiva (Revocada)",
                        description = "Se dicta orden de prisión preventiva por 36 meses, posteriormente revocada por el TC."
                    ),
                    CaseTimelineEvent(
                        date = "10/06/2021",
                        title = "Acusación Formal",
                        description = "La fiscalía presenta acusación formal ante el Poder Judicial por el delito de crimen organizado y lavado de activos."
                    )
                ),
                officialDocuments = listOf(
                    OfficialDocument(title = "Carpeta Fiscal N° 123-2024", documentUrl = "https://fiscalia.gob.pe/carpeta-123-2024"),
                    OfficialDocument(title = "Resolución del Tribunal Constitucional (TC)", documentUrl = "https://tc.gob.pe/res-fujimori")
                ),
                involvedParties = listOf(
                    InvolvedParty(name = "Keiko Fujimori", role = "Investigada Principal"),
                    InvolvedParty(name = "Vicente Silva Checa", role = "Co-investigado"),
                    InvolvedParty(name = "Mark Vito Villanella", role = "Testigo/Co-investigado")
                ),
            )

            // --- CA: doc_antecedente_3_1 (Plagio) ---
            "doc_antecedente_3_1" -> InvestigationDetail(
                caseTitle = "Denuncia por Plagio Académico",
                caseEntity = "Comisión de Ética del Congreso / Universidad",
                caseDate = "20/01/2016",
                status = "Cerrado (Sin Sanción)",
                timeline = listOf(
                    CaseTimelineEvent(
                        date = "20/01/2016",
                        title = "Denuncia Pública",
                        description = "Medios de comunicación revelan presunto plagio en tesis doctoral obtenida en España."
                    ),
                    CaseTimelineEvent(
                        date = "05/02/2016",
                        title = "Investigación de Comisión de Ética",
                        description = "El Congreso inicia una investigación preliminar por la conducta del entonces congresista."
                    ),
                    CaseTimelineEvent(
                        date = "15/03/2016",
                        title = "Informe de Rectorado",
                        description = "La universidad emisora del título declara que, tras revisión, no procede la anulación del mismo."
                    )
                ),
                officialDocuments = listOf(
                    OfficialDocument(title = "Informe Final de Comisión de Ética", documentUrl = "https://congreso.gob.pe/informe-etica-acuña"),
                    OfficialDocument(title = "Comunicado Oficial de Universidad", documentUrl = "https://universidad.es/comunicado-acuña")
                ),
                involvedParties = listOf(
                    InvolvedParty(name = "César Acuña", role = "Denunciado"),
                    InvolvedParty(name = "Comisión de Ética", role = "Entidad Investigadora")
                ),

            )

            // --- MV: doc_antecedente_5_1 (Inhabilitación Política / Vacunagate) ---
            "doc_antecedente_5_1" -> InvestigationDetail(
                caseTitle = "Inhabilitación Política (Caso Vacunagate)",
                caseEntity = "Congreso de la República",
                caseDate = "16/04/2021",
                status = "Inhabilitado (10 años)",
                timeline = listOf(
                    CaseTimelineEvent(
                        date = "01/03/2021",
                        title = "Revelación Pública",
                        description = "Se revela que el expresidente recibió la vacuna Sinopharm de forma irregular antes del inicio de la campaña nacional."
                    ),
                    CaseTimelineEvent(
                        date = "16/04/2021",
                        title = "Decisión Final del Congreso",
                        description = "El pleno vota a favor de la inhabilitación por 10 años para ejercer cargos públicos."
                    ),
                    CaseTimelineEvent(
                        date = "10/10/2025",
                        title = "Apelación ante el TC",
                        description = "La defensa legal presenta recurso ante el Tribunal Constitucional buscando revertir la sanción."
                    )
                ),
                officialDocuments = listOf(
                    OfficialDocument(
                        title = "Resolución Legislativa del Congreso",
                        documentUrl = "https://congreso.gob.pe/rl-vizcarra"
                    ),
                    OfficialDocument(
                        title = "Recurso de Agravio Constitucional (TC)",
                        documentUrl = "https://tc.gob.pe/recurso-vizcarra"
                    )
                ),
                involvedParties = listOf(
                    InvolvedParty(name = "Martín Vizcarra", role = "Sancionado/Inhabilitado"),
                    InvolvedParty(name = "Pilar Mazzetti", role = "Ex-Ministra de Salud")
                ),
            )

            else -> null
        }
    }




    // --------------------------------------------------------------------
    // --- FILTROS ---
    // --------------------------------------------------------------------

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