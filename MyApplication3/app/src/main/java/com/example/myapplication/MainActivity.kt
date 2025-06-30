package com.example.myapplication // Certifique-se de que este corresponde ao seu namespace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.myapplication.navigation.AppNavigation // Ajuste o caminho para navigation
import com.example.myapplication.ui.theme.MyApplicationTheme // Ajuste para o tema do seu projeto

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme { // Use o tema do seu projeto
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation() // Chama a função de navegação
                }
            }
        }
    }
}