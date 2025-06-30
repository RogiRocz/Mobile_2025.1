package com.ufc.quixada.nutrilivre.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.ufc.quixada.nutrilivre.data.DadosMockados
import com.ufc.quixada.nutrilivre.data.Receita

@Composable
fun DetalheScreen(navController: NavHostController, receitaId: Int?) {
    val receita = remember { DadosMockados.listaDeReceitas.find { it.id == receitaId } }
    var isFavorite by remember { mutableStateOf(receita?.isFavorita ?: false) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(receita?.nome ?: "Detalhe") }, actions = {
                IconButton(onClick = { /* TODO: Implementar menu */ }) {
                    Icon(Icons.Filled.MoreVert, contentDescription = "Menu")
                }
            })
        },
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) { paddingValues ->
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
                    modifier = Modifier.fillMaxWidth().height(200.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = r.nome, style = MaterialTheme.typography.h4)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = r.descricaoCurta, style = MaterialTheme.typography.body1)
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Ingredientes:", style = MaterialTheme.typography.h6)
                r.ingredientes.forEach { ingrediente ->
                    Text(text = "- $ingrediente")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Modo de Preparo:", style = MaterialTheme.typography.h6)
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
                        Icon(Icons.Filled.Favorite, contentDescription = "Favoritar", tint = if (isFavorite) MaterialTheme.colors.secondary else LocalContentColor.current)
                        Text(if (isFavorite) "Remover dos Favoritos" else "Adicionar aos Favoritos")
                    }
                    // Exemplo de botão de multimídia (opcional)
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = { /* TODO: Implementar reprodução de áudio/vídeo */ }, enabled = false) {
                        Text("Ouvir/Ver")
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text("Receitas Relacionadas:", style = MaterialTheme.typography.h6)
                LazyRow {
                    items(DadosMockados.listaDeReceitas.take(3)) { relatedReceita ->
                        Card(modifier = Modifier.width(150.dp).padding(end = 8.dp), elevation = 2.dp) {
                            Column(modifier = Modifier.padding(8.dp)) {
                                AsyncImage(
                                    model = relatedReceita.imagemUrl,
                                    contentDescription = relatedReceita.nome,
                                    modifier = Modifier.fillMaxWidth().height(80.dp)
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(relatedReceita.nome, style = MaterialTheme.typography.caption)
                            }
                        }
                    }
                }
            }
        } ?: run {
            Text("Receita não encontrada.")
        }
    }
}