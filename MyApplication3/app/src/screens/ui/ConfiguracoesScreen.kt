package com.ufc.quixada.nutrilivre.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun ConfiguracoesScreen(navController: NavHostController) {
    var modoEscuroAtivado by remember { mutableStateOf(false) }
    var notificacoesAtivadas by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Configurações") }, actions = {
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Modo Escuro")
                Switch(
                    checked = modoEscuroAtivado,
                    onCheckedChange = { modoEscuroAtivado = it }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Notificações")
                Switch(
                    checked = notificacoesAtivadas,
                    onCheckedChange = { notificacoesAtivadas = it }
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = { /* TODO: Implementar limpar favoritos */ }) {
                Text("Limpar Favoritos")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { /* TODO: Implementar redefinir preferências */ }) {
                Text("Redefinir Preferências")
            }
        }
    }
}