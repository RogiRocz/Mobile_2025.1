package com.example.zooapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.zooapp.components.AnimalListItem
import com.example.zooapp.models.Animal
import com.example.zooapp.models.animalList

@Composable
fun HomeScreen(onAnimalSelected: (Animal) -> Unit) {
    var animalName by remember { mutableStateOf("") }
    val filteredAnimals = remember(animalName) {
        animalList.filter { it.name.contains(animalName, ignoreCase = true) }
    }

    Column {
        TextField(
            value = animalName,
            onValueChange = { animalName = it },
            label = { Text("Pesquisar") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            items(filteredAnimals) { animal ->
                AnimalListItem(animal, onAnimalSelected)
            }
        }
    }
}