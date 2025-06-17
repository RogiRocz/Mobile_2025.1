package com.example.zooapp.models

import com.example.zooapp.R

data class Animal(val id: Int,
                  val name: String,
                  val species: String,
                  val imageRes: Int,
                  val soundEffectResource: Int,
                  val description: String,
                  val curiosities: String,
                  var isFavorite: Boolean = false)

val animalList = listOf(
    Animal(
        id = 1,
        name = "Leão",
        species = "Panthera leo",
        imageRes = R.drawable.leao_imagem,
        description = "O leão é um felino conhecido como o \"rei da selva\" por sua força e majestade.",
        curiosities = "Os leões são os únicos felinos que vivem em grupos chamados \"manadas\".",
        soundEffectResource = R.raw.leao_afeminado_som
    ),
    Animal(
        id = 2,
        name = "Pinguim",
        species = "Várias espécies (ex: Aptenodytes patagonicus)",
        imageRes = R.drawable.pinguim_imagem,
        description = "Os pinguins são aves marinhas não voadoras, adaptadas para viver em ambientes aquáticos, especialmente em regiões frias.",
        curiosities = "Pinguins são excelentes nadadores e podem mergulhar a grandes profundidades.",
        soundEffectResource = R.raw.pinguim_som
    )
)