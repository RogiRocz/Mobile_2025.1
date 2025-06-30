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

@Composable
fun AjudaScreen(navController: NavHostController) {
    val perguntas = DadosMockados.listaDePerguntasFrequentes

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Ajuda e Suporte") }, actions = {
                IconButton(onClick = { /* TODO: Implementar menu */ }) {
                    Icon(Icons.Filled.MoreVert, contentDescription = "Menu")
                }
            })
        },
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.