# CandidatoInfo – Transparencia Electoral Ciudadana

![Android](https://img.shields.io/badge/Android-7.0+-3DDC84?logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/Kotlin-1.9.0-7F52FF?logo=kotlin&logoColor=white)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-1.5.0-4285F4?logo=jetpackcompose&logoColor=white)

## 📋 Descripción del Proyecto
CandidatoInfo es una aplicación móvil desarrollada en Android que permite a los ciudadanos peruanos consultar información pública sobre candidatos al Congreso y la Presidencia del Perú. La aplicación promueve la transparencia electoral proporcionando acceso fácil y rápido a datos sobre denuncias, proyectos presentados, historial político y enlaces a fuentes oficiales.

## 🎯 Objetivo
Empoderar a los ciudadanos con información verificable y accesible sobre los candidatos políticos, facilitando la toma de decisiones informadas durante los procesos electorales.

## 💡 Valor del Proyecto

### Valor Social
CandidatoInfo contribuye directamente al fortalecimiento de la democracia peruana al:

- **Democratizar la información:** Centralizar datos dispersos en múltiples fuentes oficiales en una sola aplicación accesible
- **Promover la participación ciudadana informada:** Facilitar el acceso a información verificable sobre candidatos antes de ejercer el voto
- **Combatir la desinformación:** Proporcionar enlaces directos a fuentes oficiales (JNE, Congreso, Poder Judicial)
- **Fomentar la transparencia electoral:** Hacer visible el historial político, denuncias y proyectos de cada candidato
- **Empoderar al votante:** Permitir comparaciones objetivas entre candidatos basadas en datos públicos



## 👥 Equipo de Desarrollo

| Rol                | Responsabilidades                                 |
|--------------------|--------------------------------------------------|
| Líder Técnico      | Coordinación del proyecto, arquitectura y desarrollo |
| Diseñador UI/UX    | Prototipado en Figma, implementación de interfaces |
| Documentador/Tester| Pruebas, documentación y control de calidad        |

## 🎨 Prototipo de Diseño
📐 **Prototipo en Figma:** [Ver prototipo](https://www.figma.com/design/lklv3LkdhqFVMqlt7lJhxx/PM---C24A---G6?node-id=0-1&t=wjyQ55Vdl1y8qLHk-1)

El diseño incluye las siguientes pantallas principales:

- 🏠 Pantalla de inicio con búsqueda
- 👤 Perfil detallado del candidato
- ⚖️ Detalle de denuncias y documentos
- 🔄 Comparación entre candidatos

## ✨ Funcionalidades Principales

### Implementadas ✅

#### 🔍 Búsqueda Inteligente
- Búsqueda por nombre (con normalización de caracteres para acentos)
- Filtrado por cargo político
- Filtrado por partido político
- Contador de resultados en tiempo real

#### 👥 Gestión de Candidatos
- Lista de candidatos con información básica (foto, nombre, partido, cargo)
- Visualización detallada del perfil de cada candidato
- Diseño de cards modernas con elevación y bordes

#### ⚖️ Sistema de Comparación
- Selección múltiple de hasta 2 candidatos
- Indicador visual de candidatos seleccionados
- Botón dinámico de comparación
- Navegación a vista comparativa

#### 🎨 Interfaz de Usuario
- Material Design 3
- Paleta de colores institucional (púrpura y blanco)
- Gradientes visuales para mejor experiencia
- Animaciones y transiciones fluidas

## 🏗️ Arquitectura del Proyecto

```
app/
├── src/main/java/com/example/grupo06_candidatoinfo/
│   ├── data/
│   │   └── repository/
│   │       └── MockDataRepository.kt      # Datos simulados
│   ├── model/
│   │   └── Candidate.kt                   # Modelo de datos
│   ├── navigation/
│   │   └── AppNavigation.kt               # Sistema de navegación
│   └── ui/
│       └── screens/
│           ├── home/
│           │   └── HomeScreen.kt          # Pantalla principal
│           ├── profile/
│           │   └── ProfileScreen.kt       # Perfil del candidato
│           └── compare/
│               └── CompareScreen.kt       # Comparación
```

## 🛠️ Tecnologías Utilizadas

| Tecnología         | Versión | Propósito                  |
|--------------------|---------|----------------------------|
| Kotlin             | 1.9.0   | Lenguaje de programación   |
| Jetpack Compose    | 1.5.0   | UI moderna y declarativa   |
| Navigation Compose | 2.7.0   | Navegación entre pantallas |
| Material 3         | 1.1.0   | Componentes de diseño      |
| Coil               | 2.4.0   | Carga de imágenes          |
| Coroutines         | 1.7.0   | Programación asíncrona     |

## 📱 Capturas de Pantalla


### 🏠 Pantalla de Inicio
La pantalla principal muestra la búsqueda y lista de candidatos con opciones de filtrado.

<div align="center">
  <img src="https://media.discordapp.net/attachments/1428588526571749476/1431179226068942889/pantalla_inicio.png?ex=68fc7905&is=68fb2785&hm=12fa006d2bf85912328754dc54a98d4c5e7b1a1eaadd7ffdeee912485417c71b&=&format=webp&quality=lossless&width=473&height=1050" alt="Pantalla de Inicio" width="250" style="border: 2px solid #ccc; border-radius: 10px; padding: 10px; display: inline-block; background-color: #f9f9f9;">
  <p><em>Vista principal con búsqueda y lista de candidatos</em></p>
</div>

**Características visibles:**
- Barra de búsqueda inteligente
- Filtros por cargo y partido
- Lista de candidatos con cards
- Sistema de selección para comparación
- Contador de resultados

---

### 👤 Perfil del Candidato
Vista detallada con toda la información relevante del candidato seleccionado.

<div align="center">
  <img src="https://media.discordapp.net/attachments/1428588526571749476/1431179826320113775/Screenshot_20251024_021727.png?ex=68fc7994&is=68fb2814&hm=06a991a6bef729bd0bb753a7f449469ea93edaca071cf4b318d9cc28d2cd8970&=&format=webp&quality=lossless&width=473&height=1050" alt="Perfil del Candidato" width="250" style="border: 2px solid #ccc; border-radius: 10px; padding: 10px; display: inline-block; background-color: #f9f9f9;">
  <p><em>Perfil completo con información detallada</em></p>
</div>

**Características visibles:**
- Foto y datos biográficos
- Historial político
- Lista de denuncias
- Proyectos presentados
- Enlaces a fuentes oficiales

---

### 🔄 Vista de Comparación
Comparación lado a lado de dos candidatos seleccionados.

<div align="center">
  <img src="https://media.discordapp.net/attachments/1428588526571749476/1431180072118784020/Screenshot_20251024_021832.png?ex=68fc79cf&is=68fb284f&hm=2fd859acaafb1bc6bccb9ba1f9e35eebfb49ceca58c57b31b88116c601894b49&=&format=webp&quality=lossless&width=473&height=1050" alt="Comparación de Candidatos" width="250" style="border: 2px solid #ccc; border-radius: 10px; padding: 10px; display: inline-block; background-color: #f9f9f9;">
  <p><em>Vista comparativa entre dos candidatos</em></p>
</div>

**Características visibles:**
- Comparación de datos biográficos
- Historial político lado a lado
- Comparación de propuestas
- Análisis de antecedentes

---

## 🚀 Instalación y Configuración

### Requisitos Previos
- Android Studio Hedgehog (2023.1.1) o superior
- JDK 11 o superior
- Dispositivo Android 7.0 (API 24) o superior

### Pasos de Instalación

1. **Clonar el repositorio**
   ```bash
   git clone https://github.com/noalaurxa/Grupo06_CandidatoInfo.git
   cd Grupo06_CandidatoInfo
   ```

2. **Abrir en Android Studio**
   - File → Open → Seleccionar la carpeta del proyecto

3. **Sincronizar Gradle**
   - Android Studio sincronizará automáticamente las dependencias

4. **Ejecutar la aplicación**
   - Conectar un dispositivo Android o iniciar un emulador
   - Hacer clic en el botón "Run" (▶️)

## 📊 Fuentes de Datos
La aplicación utiliza información pública de las siguientes fuentes oficiales:

- 🏛️ [Jurado Nacional de Elecciones (JNE)](https://www.jne.gob.pe)
- 📜 [Congreso de la República del Perú](https://www.congreso.gob.pe)
- ⚖️ [Poder Judicial del Perú](https://www.pj.gob.pe)
- 📋 [Oficina Nacional de Procesos Electorales (ONPE)](https://www.onpe.gob.pe)


## 🧪 Pruebas

### Pruebas Realizadas
- ✅ Navegación entre pantallas
- ✅ Búsqueda y filtrado de candidatos
- ✅ Selección múltiple de candidatos
- ✅ Comparación de candidatos

---

<div align="center">
  <strong>Desarrollado para la transparencia electoral del Perú</strong>
</div>