# CandidatoInfo

Proyecto de aplicación móvil para centralizar y facilitar la consulta de información pública sobre los candidatos al Congreso y la Presidencia del Perú, de cara a las Elecciones Generales 2026.

## 🎯 Objetivo General

Desarrollar una aplicación móvil que permita a los ciudadanos consultar información pública, verificada y centralizada sobre los candidatos. El objetivo es fomentar un voto informado proveyendo acceso fácil a datos clave como:

* Perfil y trayectoria política/profesional.
* Declaraciones juradas de bienes e ingresos.
* Propuestas y planes de gobierno.
* Investigaciones fiscales y denuncias.
* Proyectos de ley presentados (en caso de reelección).
* Noticias y actualidad.

## 🎨 Prototipo en Figma (Entregable Día 1)
**[➡ Ver el Prototipo Interactivo en Figma](https://www.figma.com/design/lklv3LkdhqFVMqlt7lJhxx/PM---C24A---G6?node-id=0-1&t=2tjDUFwPQ5I3ID2V-1)**

### Vistas Principales del Prototipo:

* *Inicio y Búsqueda:* Lista y filtra candidatos por cargo (Presidencia, Congreso) y partido.
* *Detalle del Candidato (Pestañas):*
    * *General:* Información personal, declaración jurada de bienes y resumen del plan de gobierno.
    * *Trayectoria:* Experiencia laboral (cargos públicos/privados) y formación académica (con verificación SUNEDU).
    * *Investigación Fiscal:* Detalle de casos (p.ej., Caso Odebrecht), cronología, involucrados y cobertura mediática.
    * *Actualidad:* Últimas noticias y actividades del candidato.


## ✨ Características Principales (Features)

* *Búsqueda Centralizada:* Encuentra cualquier candidato en segundos.
* *Perfil Unificado:* Toda la información clave en un solo lugar.
* *Verificación de Datos:* Enlaces directos a fuentes oficiales (PJ, MP, JNE, SUNEDU) para validación.
* *Historial y Trayectoria:* Conoce la experiencia laboral y académica del candidato.
* *Transparencia Fiscal:* Acceso a declaraciones juradas e investigaciones en curso.
* *Planes de Gobierno:* Resumen de propuestas en temas clave (Economía, Salud, Educación).

## 📊 Fuentes de Datos (Investigación Inicial - RF01)

La información de la aplicación se obtendrá y contrastará de las siguientes fuentes públicas y oficiales:

* *JNE - Plataforma Electoral del Jurado Nacional de Elecciones:* Hojas de vida, declaraciones juradas, planes de gobierno.
* *Congreso de la República:* Proyectos de ley, historial de votaciones.
* *Poder Judicial (PJ):* Consulta de expedientes y sentencias.
* *Ministerio Público - Fiscalía de la Nación:* Estado de investigaciones y denuncias.
* *SUNEDU (Superintendencia Nacional de Educación Superior Universitaria):* Verificación de grados y títulos.
* *Contraloría General de la República:* Informes y sanciones.

## 🧑‍💻 Equipo y Roles (Definición Inicial - RF05)

* *Líder Técnico:* [Naudy Noa]
* *Diseñador UI/UX:* [Italo Mendoza]
* *Documentador / Data Researcher:* [jhamile macavilca]



## 🛠 Stack Tecnológico (Planeado)

* *Diseño y Prototipado:* Figma
* *App Móvil (Frontend):* *Kotlin (Android Nativo)*
* *Arquitectura (sugerida):* MVVM, Clean Architecture.
* *Librerías (sugeridas):* Jetpack Compose (para UI), Coroutines/Flow (Asincronía), Room (Base de datos local).

## 🚀 Plan de Proyecto (Resumen)

* *Día 1:* Investigación, definición de alcance, prototipado en Figma y configuración del repositorio. (✔ Completado)
* *Día 2:* Definición de la arquitectura (MVVM/Clean), configuración del entorno de desarrollo (Android Studio) y diseño del modelo de datos local (Room).
* *Día 3-5:* Desarrollo de la capa de datos (Repositorios, integración de API/fuentes).
* *Día 6-9:* Desarrollo de la App Móvil en Kotlin (Frontend) basado en el prototipo (Vistas con Jetpack Compose, ViewModels).
* *Día 10:* Pruebas (Testing Unitario/UI), despliegue (APK) y documentación final.

---

## 🚀 Progreso y Estado del Proyecto

### Día 2: Configuración y Estructura Base (✅ ¡Completado!)

El proyecto base ha sido configurado y la estructura inicial está lista para comenzar a desarrollar las funcionalidades de la aplicación.

**Actividades Finalizadas:**

* **Creación del Proyecto:** Se inició el proyecto en Android Studio con **Kotlin** y **Jetpack Compose**.
* **Estructura Base:** Se definió la estructura de paquetes (ui, model, navigation, data, util).
* **Control de Versiones:** Repositorio inicializado y ramas de trabajo configuradas por integrante.
* **Navegación:** Pantallas base vacías creadas con **Navigation Compose**.
* **Dependencias:** Configuradas las dependencias iniciales (ej. Retrofit/Ktor si aplica) para manejo de datos.

**Entregable del Día:**
✔️ Proyecto base funcional con navegación entre las pantallas principales.
✔️ Estructura de paquetes definida y repositorio actualizado.
