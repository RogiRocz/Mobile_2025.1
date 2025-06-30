package com.example.myapplication.model

data class Receita(
    val id: Int,
    val nome: String,
    val descricaoCurta: String,
    val imagemUrl: String,
    val ingredientes: List<String>,
    val modoPreparo: List<String>,
    val tempoPreparo: String,
    val porcoes: Int,
    var isFavorita: Boolean = false
)

object DadosMockados {
    val listaDeReceitas = listOf(
        Receita(
            id = 1,
            nome = "Salada de Quinoa com Vegetais",
            descricaoCurta = "Uma salada nutritiva e refrescante.",
            imagemUrl = "https://via.placeholder.com/300/FFC107/000000?Text=Salada",
            ingredientes = listOf("1 xícara de quinoa cozida", "1 pepino picado", "1 tomate picado"),
            modoPreparo = listOf("Misture os vegetais.", "Adicione a quinoa."),
            tempoPreparo = "20 min",
            porcoes = 2
        ),
        Receita(
            id = 2,
            nome = "Frango Assado com Batata Doce",
            descricaoCurta = "Um prato clássico e saudável.",
            imagemUrl = "https://via.placeholder.com/300/4CAF50/FFFFFF?Text=Frango",
            ingredientes = listOf("2 filés de frango", "2 batatas doces"),
            modoPreparo = listOf("Tempere o frango.", "Asse com as batatas."),
            tempoPreparo = "50 min",
            porcoes = 2
        ),
        Receita(
            id = 3,
            nome = "Smoothie de Frutas Vermelhas",
            descricaoCurta = "Delicioso e cheio de antioxidantes.",
            imagemUrl = "https://via.placeholder.com/300/F44336/FFFFFF?Text=Smoothie",
            ingredientes = listOf("Frutas vermelhas congeladas", "Banana", "Iogurte"),
            modoPreparo = listOf("Bata tudo no liquidificador."),
            tempoPreparo = "5 min",
            porcoes = 1
        )
    )

    val listaDeFavoritosMock = mutableListOf<Receita>()
    val listaDePerguntasFrequentes = listOf(
        "Como adicionar uma receita aos favoritos?",
        "Onde encontro as configurações do app?",
        "Como pesquisar por uma receita?"
    )
}