plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.grupo06_candidatoinfo"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.example.grupo06_candidatoinfo"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
        freeCompilerArgs += "-Xopt-in=androidx.compose.animation.ExperimentalAnimationApi"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    // AndroidX Core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    // Compose BOM
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

    // Navigation Compose
    implementation("androidx.navigation:navigation-compose:2.9.5")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:3.0.0")
    implementation("com.squareup.retrofit2:converter-gson:3.0.0")

    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.3")


    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)

    // Debug
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    // Coil – carga y visualización de imágenes
    implementation("io.coil-kt:coil-compose:2.7.0")

    //
    implementation("androidx.compose.material:material-icons-extended-android:1.6.8")

    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")
    implementation("androidx.compose.animation:animation:1.7.3")
    implementation("androidx.compose.material3:material3:1.3.0")
    implementation(platform("androidx.compose:compose-bom:2024.09.01"))
    implementation("androidx.compose.animation:animation")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material-icons-extended:1.6.0")

// Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    // Convertidor JSON para Kotlin (Gson o kotlinx.serialization, aquí usaremos Gson)
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Coroutines para manejo asíncrono
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")


}