package com.example.myapplication.screens.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.myapplication.model.DadosMockados
import com.example.myapplication.model.Receita

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaInicial(navController: NavHostController) {
    // Certifique-se de que 'mutableStateListOf' está importado corretamente
    val receitas = remember {
        mutableStateListOf(*DadosMockados.listaDeReceitas.toTypedArray())
    }
    var expandedMenu by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("NutriLivre") },
                actions = {
                    IconButton(onClick = { expandedMenu = true }) {
                        Icon(Icons.Filled.MoreVert, contentDescription = "Menu")
                    }
                    DropdownMenu(expanded = expandedMenu, onDismissRequest = { expandedMenu = false }) {
                        // DropdownMenuItem do Material3
                        DropdownMenuItem(
                            text = { Text("Favoritos") },
                            onClick = {
                                navController.navigate(AppScreens.FavoritosScreen.route)
                                expandedMenu = false // Fechar o menu após a navegação
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Configurações") },
                            onClick = {
                                navController.navigate(AppScreens.ConfiguracoesScreen.route)
                                expandedMenu = false // Fechar o menu após a navegação
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Ajuda") },
                            onClick = {
                                navController.navigate(AppScreens.AjudaScreen.route)
                                expandedMenu = false // Fechar o menu após a navegação
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
                .padding(horizontal = 8.dp, vertical = 8.dp) // Use horizontal/vertical padding para consistência
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
    // Card do Material Design 3 tem um parâmetro 'onClick' direto
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onCardClick), // Modifier.clickable é do androidx.compose.foundation
        // A elevação no Material 3 é 'shadowElevation' ou 'tonalElevation'
        // Definindo shadowElevation para um valor similar ao 4.dp do MD2
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
            // Ajuste da Tipografia Material 3:
            // h6 no MD2 é geralmente titleLarge no MD3
            Text(text = receita.nome, style = MaterialTheme.typography.titleLarge)
            // body2 no MD2 é geralmente bodySmall ou bodyMedium no MD3
            Text(text = receita.descricaoCurta, style = MaterialTheme.typography.bodySmall)
        }
    }
}

// @OptIn(ExperimentalMaterial3Api::class) // Não é necessário aqui
@Composable
fun BottomNavigationBar(navController: NavHostController) {
    // Para uma Bottom Navigation bar completa com ícones e labels,
    // você usaria o BottomAppBar e NavigationBar do Material 3.
    // Como você usou uma Row com Buttons, mantive a estrutura, mas usei o Button do Material 3.
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