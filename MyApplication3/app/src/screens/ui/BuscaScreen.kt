package com.ufc.quixada.nutrilivre.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ufc.quixada.nutrilivre.data.DadosMockados
import com.ufc.quixada.nutrilivre.data.Receita

@Composable
fun BuscaScreen(navController: NavHostController) {
    var searchText by remember { mutableStateOf("") }
    val receitas = remember { DadosMockados.listaDeReceitas }
    val filteredReceitas = remember(searchText) {
        if (searchText.isBlank()) {
            receitas
        } else {
            receitas.filter {
                it.nome.contains(searchText, ignoreCase = true) ||
                        it.ingredientes.any { it.contains(searchText, ignoreCase = true) }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Buscar Receitas") }, actions = {
                IconButton(onClick = { /* TODO: Implementar menu */ }) {
                    Icon(Icons.Filled.MoreVert, contentDescription = "Menu")
                }
            })
        },
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it },
                label = { Text("Buscar Receitas") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn {
                items(filteredReceitas) { receita ->
                    ReceitaCard(receita = receita) {
                        navController.navigate(AppScreens.DetalheScreen.createRoute(receita.id))
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}