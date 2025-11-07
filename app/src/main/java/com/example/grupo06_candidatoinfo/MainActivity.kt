package com.example.grupo06_candidatoinfo

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.grupo06_candidatoinfo.data.remote.CandidateApiService
import com.example.grupo06_candidatoinfo.data.repository.CandidateRepository
import com.example.grupo06_candidatoinfo.data.repository.MockDataRepository
import com.example.grupo06_candidatoinfo.data.repository.RemoteCandidateRepository
import com.example.grupo06_candidatoinfo.navigation.NavGraph
import com.example.grupo06_candidatoinfo.ui.screens.home.HomeViewModelFactory
import com.example.grupo06_candidatoinfo.ui.theme.Grupo06_CandidatoInfoTheme
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.getValue

class MainActivity : ComponentActivity() {
    // **CONFIGURACIÓN DEL CONTENEDOR DE DEPENDENCIAS AQUÍ**
    private val candidateRepository: CandidateRepository by lazy {

        // 1. Configurar Retrofit
        // IMPORTANTE: Si usas un emulador, usa http://10.0.2.2:<PuertoDjango>
        // Si usas un dispositivo físico en la misma red, usa la IP local de tu PC.
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8000/api/v1/") // ¡Asegúrate que esta URL sea correcta!
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // 2. Crear el servicio API
        val apiService = retrofit.create(CandidateApiService::class.java)

        // 3. Devolver el Repositorio Remoto (¡Adiós MockDataRepository para las llamadas!)
        RemoteCandidateRepository(apiService, MockDataRepository)
    }

    // 4. Crear el Factory del ViewModel
    private val homeViewModelFactory by lazy {
        HomeViewModelFactory(candidateRepository)
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Grupo06_CandidatoInfoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavGraph(navController = navController)
                }
            }
        }
    }
}