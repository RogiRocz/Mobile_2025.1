package com.example.zooapp.components

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.zooapp.models.Animal
import com.example.zooapp.models.AnimalList

@Composable
fun AnimalListItem(animal: Animal, onAnimalSelected: (Animal) -> Unit){
    val context = LocalContext.current
    val mediaPlayer = remember(context, animal.soundEffectResource) {
        animal.soundEffectResource?.let { MediaPlayer.create(context, it) }
    }

    fun playSound() {
        mediaPlayer?.start()
    }

    Card (
        modifier = Modifier.fillMaxWidth()
            .padding(10.dp)
    ){
        Row(
            modifier = Modifier.fillMaxWidth()
                .height(50.dp)
        ) {
            Image(painter = painterResource(id = animal.imageRes), contentDescription = "${animal.name} image") // Imagem do animal
            Button(onClick = { playSound() },
                modifier = Modifier.padding(10.dp)

            ) { // Som do animal
                Text(text = "Escute o animal")
            }
        }
        Column { // Vai conter as info do animal
            Row { // Nome e especie
                Text(text = animal.name)
                Text(text = animal.species)
            }
            Row { // Descricao e curiosidade
                Text(text = animal.description)
                Text(text = animal.curiosities)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AnimalListItemPreview(){
    var animal = AnimalList.first()
    AnimalListItem(animal = animal) { }
}