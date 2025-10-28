package com.example.meucontadordeagua.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "consumo_agua_table")
data class ConsumoAgua(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val quantidadeMl: Int, // Ex: 250 (ml)
    val timestamp: Long = System.currentTimeMillis() // Data e hora que foi salvo
)