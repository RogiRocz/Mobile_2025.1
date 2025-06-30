package com.ufc.quixada.nutrilivre.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ufc.quixada.nutrilivre.data.DadosMockados
import com.ufc.quixada.nutrilivre.navigation.AppScreens

@Composable
fun FavoritosScreen(navController: NavHostController) {
    val favoritos = DadosMockados.listaDeFavoritosMock

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Favoritos") }, actions = {
                IconButton(onClick = { /* TODO: Implementar menu */ }) {
                    Icon(Icons.Filled.MoreVert, contentDescription = "Menu")
                }
            })
        },
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) { paddingValues ->
        if (favoritos.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize().padding(paddingValues), contentAlignment = androidx.compose.ui.Alignment.Center) {
                Text("Nenhuma receita favorita adicionada ainda.")
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(8.dp)
            ) {
                items(favoritos) { receita ->
                    ReceitaCard(receita = receita) {
                        navController.navigate(AppScreens.DetalheScreen.createRoute(receita.id))
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}