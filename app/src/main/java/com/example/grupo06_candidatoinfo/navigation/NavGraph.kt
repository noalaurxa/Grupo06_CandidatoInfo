package com.example.grupo06_candidatoinfo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.grupo06_candidatoinfo.ui.screens.compare.CompareScreen
import com.example.grupo06_candidatoinfo.ui.screens.detail.DetailScreen
import com.example.grupo06_candidatoinfo.ui.screens.home.HomeScreen
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
    object Compare : Screen("compare")
}

/**
 * Configura el grafo de navegación de la aplicación
 */

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

        // Pantalla de comparación
        composable(route = Screen.Compare.route) {
            CompareScreen(navController = navController)
        }
    }
}