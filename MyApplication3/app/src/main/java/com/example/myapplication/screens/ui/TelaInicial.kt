package com.example.myapplication.screens.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.myapplication.model.DadosMockados
import com.example.myapplication.model.Receita
import com.example.myapplication.ui.theme.GreenTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaInicial(navController: NavHostController) {
    val receitas = remember {
        mutableStateListOf(*DadosMockados.listaDeReceitas.toTypedArray())
    }
    var expandedMenu by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(
                    text = "NutriLivre",
                    color = Color.White
                ) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = GreenTheme
                ),
                actions = {
                    IconButton(onClick = { expandedMenu = true }) {
                        Icon(Icons.Filled.Menu, contentDescription = "Menu")
                    }
                    DropdownMenu(expanded = expandedMenu, onDismissRequest = { expandedMenu = false }) {
                        DropdownMenuItem(
                            text = { Text("Favoritos") },
                            onClick = {
                                navController.navigate(AppScreens.FavoritosScreen.route)
                                expandedMenu = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Configurações") },
                            onClick = {
                                navController.navigate(AppScreens.ConfiguracoesScreen.route)
                                expandedMenu = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Ajuda") },
                            onClick = {
                                navController.navigate(AppScreens.AjudaScreen.route)
                                expandedMenu = false
                            }
                        )
                    }
                }
            )
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 8.dp, vertical = 8.dp)
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
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onCardClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            AsyncImage(
                model = receita.imagemUrl,
                contentDescription = receita.nome,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = receita.nome, style = MaterialTheme.typography.titleLarge)
            Text(text = receita.descricaoCurta, style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = GreenTheme)
            .padding(8.dp)
            .navigationBarsPadding(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Button(onClick = { navController.navigate(AppScreens.TelaInicialScreen.route) }) {
            Text("Receitas")
        }
        Button(onClick = { navController.navigate(AppScreens.FavoritosScreen.route)}) {
            Text("Favoritos")
        }
    }
}