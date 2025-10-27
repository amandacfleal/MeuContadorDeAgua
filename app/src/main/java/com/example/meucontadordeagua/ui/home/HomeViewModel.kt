package com.example.meucontadordeagua.ui.home // (Dentro do pacote 'ui.home')

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meucontadordeagua.data.AguaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

// @HiltViewModel faz o Hilt injetar o que precisamos (o Repositório)
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: AguaRepository
) : ViewModel() {

    // Este é o LiveData (na versão moderna, StateFlow) que a vaga pede.
    // A UI (Fragment) vai "ouvir" esta variável.
    val consumoHoje: StateFlow<Int> = repository.getConsumoDeHoje()
        .map { consumo ->
            // Se o valor do banco for nulo (nada consumido), retorna 0
            consumo ?: 0
        }
        .stateIn(
            scope = viewModelScope, // Onde o Flow vai "viver"
            started = SharingStarted.WhileSubscribed(5000L), // Como ele vai iniciar
            initialValue = 0 // Valor inicial antes do banco responder
        )

    // Esta é a ação que a UI vai chamar.
    // Ela não mexe na UI, só avisa o repositório.
    fun adicionarAgua(quantidadeMl: Int) {
        // Usamos viewModelScope para lançar uma Coroutine (tarefa em segundo plano)
        viewModelScope.launch {
            repository.adicionarConsumo(quantidadeMl)
        }
    }
}