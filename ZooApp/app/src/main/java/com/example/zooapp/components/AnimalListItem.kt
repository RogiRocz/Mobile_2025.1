package com.example.zooapp.components

import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.zooapp.models.Animal
import com.example.zooapp.models.animalList
import com.example.zooapp.ui.theme.Aquamarine

@Composable
fun AnimalListItem(animal: Animal, onAnimalSelected: (Animal) -> Unit) {
    val context = LocalContext.current
    val mediaPlayer = remember(context, animal.soundEffectResource) {
        animal.soundEffectResource.let { MediaPlayer.create(context, it) }
    }

    fun playSound() {
        mediaPlayer?.start()
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(2.dp, Aquamarine, RoundedCornerShape(percent = 5))
            .padding(8.dp)
            .clip(RoundedCornerShape(percent = 5)),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFCFDBD5)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(16.dp, 280.dp)
                .padding(4.dp)
        ) {

            Image(
                painter = painterResource(id = animal.imageRes),
                contentDescription = "${animal.name} image",
                modifier = Modifier
                    .size(160.dp)
                    .clip(CircleShape)
                    .align(Alignment.CenterVertically)
                    .border(4.dp, color = Aquamarine, shape = CircleShape)
            ) // Imagem do animal
            Spacer(modifier = Modifier.padding(8.dp))
            Button(
                onClick = { playSound() },
                modifier = Modifier.align(Alignment.CenterVertically),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Aquamarine,
                    contentColor = Color.White
                )
            ) { // Som do animal
                Text(text = "Escute o animal")
            }

        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) { // Vai conter as info do animal
            Text(
                text = animal.name,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
            )
            Text(
                text = animal.species,
                style = TextStyle(
                    fontStyle = FontStyle.Italic
                )
            )
            Text(text = animal.description, modifier = Modifier.padding(start = 0.dp, 4.dp))
            Text(text = animal.curiosities, modifier = Modifier.padding(start = 0.dp, 4.dp))
            Button(onClick = {onAnimalSelected(animal)}) {
                Text(text = "Mais sobre")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AnimalListItemPreview() {
    var animal = animalList.first()
    AnimalListItem(animal = animal) { }
}