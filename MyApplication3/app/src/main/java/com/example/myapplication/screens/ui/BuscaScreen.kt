package com.example.myapplication.screens.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.model.DadosMockados


@OptIn(ExperimentalMaterial3Api::class)
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