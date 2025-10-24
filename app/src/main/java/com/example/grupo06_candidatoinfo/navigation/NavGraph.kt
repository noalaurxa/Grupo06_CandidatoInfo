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
import com.example.grupo06_candidatoinfo.ui.screens.home.HomeScreen
// --- IMPORTACIONES DE PANTALLAS ---
import com.example.grupo06_candidatoinfo.ui.screens.profile.ProfileScreen
import com.example.grupo06_candidatoinfo.ui.screens.detail.InvestigationDetail
import com.example.grupo06_candidatoinfo.ui.screens.detail.NewsDetail

/**
 * Sealed class que define las rutas de navegación de la aplicación
 */
sealed class Screen(val route: String) {
    object Home: Screen("home")
    object Profile: Screen("profile/{candidateId}") {
        fun createRoute(candidateId: String) = "profile/$candidateId"
    }
    object NewsDetail: Screen("news_detail/{documentId}") {
        fun createRoute(documentId: String) = "news_detail/$documentId"
    }
    object InvestigationDetail: Screen("investigation_detail/{documentId}") {
        fun createRoute(documentId: String) = "investigation_detail/$documentId"
    }

    // --- RUTA COMPARE ---
    object Compare : Screen("compare?ids={ids}") {
        fun createRoute(ids: String) = "compare?ids=$ids"
    }
}

/**
 * Configura el grafo de navegación de la aplicación
 */
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph(navController: NavHostController) {
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

        // 2. Detalle de Noticias y Actividades
        composable(
            route = Screen.NewsDetail.route,
            arguments = listOf(
                navArgument("documentId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val documentId = backStackEntry.arguments?.getString("documentId")
            NewsDetail(
                navController = navController,
                documentId = documentId
            )
        }

        // 3. Detalle de Investigación por Lavado de Activos
        composable(
            route = Screen.InvestigationDetail.route,
            arguments = listOf(
                navArgument("documentId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val documentId = backStackEntry.arguments?.getString("documentId")
            InvestigationDetail(
                navController = navController,
                documentId = documentId
            )
        }

        // Pantalla de Comparación
        composable(
            route = Screen.Compare.route,
            arguments = listOf(
                navArgument("ids") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )
        ) { backStackEntry ->
            val candidateIds = backStackEntry.arguments?.getString("ids")
            CompareScreen(
                navController = navController,
                candidateIds = candidateIds
            )
        }
    }
}