package com.example.grupo06_candidatoinfo.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.grupo06_candidatoinfo.ui.screens.compare.CompareScreen
import com.example.grupo06_candidatoinfo.ui.screens.detail.DetailScreen
import com.example.grupo06_candidatoinfo.ui.screens.home.HomeScreen
// --- IMPORTACIÓN AÑADIDA ---
import com.example.grupo06_candidatoinfo.ui.screens.profile.ProfileScreen

/**
 * Sealed class que define las rutas de navegación de la aplicación
 */
sealed class Screen(val route: String) {
    object Home: Screen("home")
    object Profile: Screen("profile/{candidateId}") {
        fun createRoute(candidateId: String) = "profile/$candidateId"
    }
    object Detail: Screen("detail/{documentId}") {
        fun createRoute(documentId: String) = "detail/$documentId"
    }

    // --- RUTA ACTUALIZADA para argumento opcional ---
    object Compare : Screen("compare?ids={ids}") {
        // Función para crear la ruta, pasando una cadena de IDs (e.g., "1,2,3")
        fun createRoute(ids: String) = "compare?ids=$ids"
    }
}

/**
 * Configura el grafo de navegación de la aplicación
 */

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {

        // Pantalla de inicio
        composable(route = Screen.Home.route){
            HomeScreen(navController = navController)
        }

        // Pantalla de perfil del candidato
        composable(
            route = Screen.Profile.route,
            arguments = listOf(
                navArgument("candidateId") {
                    type = NavType.StringType
                }
            )
        ){ backStackEntry ->
            val candidateId = backStackEntry.arguments?.getString("candidateId")
            ProfileScreen(
                navController = navController,
                candidateId = candidateId
            )
        }

        // Pantalla de detalle de documento/denuncia
        composable(
            route = Screen.Detail.route,
            arguments = listOf(
                navArgument("documentId") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val documentId = backStackEntry.arguments?.getString("documentId")
            DetailScreen(
                navController = navController,
                documentId = documentId
            )
        }

        // --- PANTALLA DE COMPARACIÓN (ACTUALIZADA con argumento opcional 'ids') ---
        composable(
            route = Screen.Compare.route, // Usamos la ruta con el query parameter
            arguments = listOf(
                navArgument("ids") { // Definimos el argumento opcional
                    type = NavType.StringType
                    nullable = true // Importante: indica que el parámetro es opcional
                    defaultValue = null // Establece un valor por defecto
                }
            )
        ) { backStackEntry ->
            // Extrae la cadena de IDs y pásala a la pantalla. Será null si no se proporciona.
            val candidateIds = backStackEntry.arguments?.getString("ids")
            CompareScreen(
                navController = navController,
                candidateIds = candidateIds // Pasa el string de IDs
            )
        }
    }
}