package com.ufc.quixada.nutrilivre.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.ufc.quixada.nutrilivre.data.DadosMockados
import com.ufc.quixada.nutrilivre.data.Receita
import com.ufc.quixada.nutrilivre.navigation.AppScreens

@Composable
fun TelaInicial(navController: NavHostController) {
    val receitas = remember { mutableStateListOf(*DadosMockados.listaDeReceitas.toTypedArray()) }
    var expandedMenu by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("NutriLivre") }, actions = {
                IconButton(onClick = { expandedMenu = true }) {
                    Icon(Icons.Filled.MoreVert, contentDescription = "Menu")
                }
                DropdownMenu(expanded = expandedMenu, onDismissRequest = { expandedMenu = false }) {
                    DropdownMenuItem(onClick = { navController.navigate(AppScreens.FavoritosScreen.route) }) {
                        Text("Favoritos")
                    }
                    DropdownMenuItem(onClick = { navController.navigate(AppScreens.ConfiguracoesScreen.route) }) {
                        Text("Configurações")
                    }
                    DropdownMenuItem(onClick = { navController.navigate(AppScreens.AjudaScreen.route) }) {
                        Text("Ajuda")
                    }
                }
            })
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(8.dp)
        ) {
            items(receitas) { receita ->
                ReceitaCard(receita = receita) {
                    navController.navigate(AppScreens.DetalheScreen.createRoute(receita.id))
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun ReceitaCard(receita: Receita, onCardClick: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth().clickable(onClick = onCardClick), elevation = 4.dp) {
        Column(modifier = Modifier.padding(16.dp)) {
            AsyncImage(
                model = receita.imagemUrl,
                contentDescription = receita.nome,
                modifier = Modifier.fillMaxWidth().height(120.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = receita.nome, style = MaterialTheme.typography.h6)
            Text(text = receita.descricaoCurta, style = MaterialTheme.typography.body2)
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(onClick = { navController.navigate(AppScreens.TelaInicialScreen.route) }) {
            Text("Receitas")
        }
        Button(onClick = { /* Navegar para outra tela principal se houver */ }) {
            Text("Outro")
        }
    }
}