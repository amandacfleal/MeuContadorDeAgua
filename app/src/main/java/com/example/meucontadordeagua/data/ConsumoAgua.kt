package com.example.meucontadordeagua.data // (Dentro do pacote 'data')

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "consumo_agua_table") // Nome da nossa tabela no banco
data class ConsumoAgua(

    @PrimaryKey(autoGenerate = true) // O ID será gerado automaticamente
    val id: Int = 0,

    val quantidadeMl: Int, // Ex: 250 (ml)

    val timestamp: Long = System.currentTimeMillis() // Data e hora que foi salvo
)