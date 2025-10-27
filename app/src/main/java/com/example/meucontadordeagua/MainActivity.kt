package com.example.meucontadordeagua // (seu pacote)

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint // A anotação está correta
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // APAGUE AS LINHAS "enableEdgeToEdge()" E "ViewCompat.setOn..."

        setContentView(R.layout.activity_main) // Deixe apenas esta linha
    }
}