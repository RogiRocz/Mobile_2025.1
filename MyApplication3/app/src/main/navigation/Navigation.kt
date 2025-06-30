package com.example.myapplication.navigation // Certifique-se de usar o namespace correto do seu projeto

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.ui.screens.* // Ajuste o import do pacote de telas

sealed class AppScreens(val route: String) {
    object TelaInicialScreen : AppScreens("tela_inicial")
    object DetalheScreen : AppScreens("detalhe/{receitaId}") {
        fun createRoute(receitaId: Int) = "detalhe/$receitaId"
    }
    object FavoritosScreen : AppScreens("favoritos")
    object ConfiguracoesScreen : AppScreens("configuracoes")
    object BuscaScreen : AppScreens("busca")
    object AjudaScreen : AppScreens("ajuda")
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.TelaInicialScreen.route) {
        composable(AppScreens.TelaInicialScreen.route) {
            TelaInicial(navController = navController)
        }
        composable(
            route = AppScreens.DetalheScreen.route,
            arguments = listOf(navArgument("receitaId") { type = NavType.IntType })
        ) { entry ->
            DetalheScreen(
                navController = navController,
                receitaId = entry.arguments?.getInt("receitaId")
            )
        }
        composable(AppScreens.FavoritosScreen.route) {
            FavoritosScreen(navController = navController)
        }
        composable(AppScreens.ConfiguracoesScreen.route) {
            ConfiguracoesScreen(navController = navController)
        }
        composable(AppScreens.BuscaScreen.route) {
            BuscaScreen(navController = navController)
        }
        composable(AppScreens.AjudaScreen.route) {
            AjudaScreen(navController = navController)
        }
    }
}