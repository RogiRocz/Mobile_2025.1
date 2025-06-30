package com.example.myapplication.screens.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.myapplication.model.DadosMockados

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalheScreen(navController: NavHostController, receitaId: Int?) {
    val receita = remember { DadosMockados.listaDeReceitas.find { it.id == receitaId } }
    var isFavorite by remember { mutableStateOf(receita?.isFavorita ?: false) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(receita?.nome ?: "Detalhe") }, actions = {
                // IconButton para o menu MoreVert (já estava correto)
                IconButton(onClick = { /* TODO: Implementar menu */ }) {
                    Icon(Icons.Filled.MoreVert, contentDescription = "Menu")
                }
            })
        },
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) { paddingValues ->
        // O `receita?.let` garante que o bloco interno só execute se a receita não for nula
        receita?.let { r ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                AsyncImage(
                    model = r.imagemUrl,
                    contentDescription = r.nome,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                // Ajuste da Tipografia Material 3:
                Text(text = r.nome, style = MaterialTheme.typography.headlineLarge) // Equivalente ao h4
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = r.descricaoCurta, style = MaterialTheme.typography.bodyLarge) // Equivalente ao body1
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Ingredientes:", style = MaterialTheme.typography.titleLarge) // Equivalente ao h6
                r.ingredientes.forEach { ingrediente ->
                    Text(text = "- $ingrediente")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Modo de Preparo:", style = MaterialTheme.typography.titleLarge) // Equivalente ao h6
                r.modoPreparo.forEachIndexed { index, passo ->
                    Text(text = "${index + 1}. $passo")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Button(onClick = {
                        r.isFavorita = !r.isFavorita
                        isFavorite = r.isFavorita
                        if (isFavorite) {
                            DadosMockados.listaDeFavoritosMock.add(r)
                        } else {
                            DadosMockados.listaDeFavoritosMock.remove(r)
                        }
                        // TODO: Atualizar a lista de favoritos se necessário
                    }) {
                        Icon(
                            Icons.Filled.Favorite,
                            contentDescription = "Favoritar",
                            // Ajuste da Cor no Material 3:
                            // Não existe 'MaterialTheme.colors.secondary' no MD3.
                            // Use MaterialTheme.colorScheme.secondary ou primary.
                            // LocalContentColor.current é uma boa opção para o não favorito.
                            tint = if (isFavorite) MaterialTheme.colorScheme.primary else LocalContentColor.current
                        )
                        Text(if (isFavorite) "Remover dos Favoritos" else "Adicionar aos Favoritos")
                    }
                    // Exemplo de botão de multimídia (opcional)
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = { /* TODO: Implementar reprodução de áudio/vídeo */ }, enabled = false) {
                        Text("Ouvir/Ver")
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text("Receitas Relacionadas:", style = MaterialTheme.typography.titleLarge) // Equivalente ao h6
                LazyRow {
                    items(DadosMockados.listaDeReceitas.take(3)) { relatedReceita ->
                        Card(
                            modifier = Modifier
                                .width(150.dp)
                                .padding(end = 8.dp),
                            // elevation no MD3 é shadowElevation ou tonalElevation
                            // shadowElevation = 2.dp, // Use esta linha se quiser uma sombra
                            onClick = { /* TODO: Navegar para o detalhe da receita relacionada */ } // Adicione um onClick
                        ) {
                            Column(modifier = Modifier.padding(8.dp)) {
                                AsyncImage(
                                    model = relatedReceita.imagemUrl,
                                    contentDescription = relatedReceita.nome,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(80.dp)
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                // Ajuste da Tipografia Material 3:
                                Text(relatedReceita.nome, style = MaterialTheme.typography.labelSmall) // Equivalente ao caption
                            }
                        }
                    }
                }
            }
        } ?: run {
            // Este `Box` centraliza a mensagem de "Receita não encontrada"
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("Receita não encontrada.", style = MaterialTheme.typography.headlineSmall)
            }
        }
    }
}