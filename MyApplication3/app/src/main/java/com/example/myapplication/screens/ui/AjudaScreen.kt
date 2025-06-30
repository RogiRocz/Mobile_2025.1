package com.example.myapplication.screens.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.model.DadosMockados

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AjudaScreen(navController: NavHostController) {
    val perguntas = DadosMockados.listaDePerguntasFrequentes

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Ajuda e Suporte") },
                navigationIcon = { // Opcional: Adicionar um botão de voltar
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Voltar")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        // Ação para o menu de três pontos, por exemplo, mostrar um DropdownMenu
                    }) {
                        Icon(Icons.Filled.MoreVert, contentDescription = "Menu")
                    }
                }
            )
        },
        // Certifique-se de que você tem um composable BottomNavigationBar disponível
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 8.dp) // Use .dp para unidades de densidade
        ) {
            items(perguntas) { perguntaFrequente ->
                PerguntaFrequenteItem(pergunta = perguntaFrequente)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerguntaFrequenteItem(pergunta: String) {
    var expandido by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        // elevation = 2.dp,
        // onClick = { expandido = !expandido } // Alterna o estado de expansão ao clicar
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = pergunta,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    imageVector = if (expandido) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    contentDescription = if (expandido) "Colapsar" else "Expandir",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

        }
    }
}