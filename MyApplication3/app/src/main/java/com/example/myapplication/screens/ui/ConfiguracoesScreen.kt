package com.example.myapplication.screens.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
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