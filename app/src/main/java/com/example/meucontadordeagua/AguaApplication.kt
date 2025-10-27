package com.example.meucontadordeagua // (Confirme se é o mesmo nome do seu pacote)

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AguaApplication : Application() {
    // Por enquanto, ela fica vazia.
    // A anotação @HiltAndroidApp faz toda a mágica.
}