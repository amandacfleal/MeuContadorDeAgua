package com.example.meucontadordeagua.data

import androidx.room.Database
import androidx.room.RoomDatabase

// Diz ao Room que este é o "prédio", versão 1
@Database(entities = [ConsumoAgua::class], version = 1, exportSchema = false)
abstract class AguaDatabase : RoomDatabase() {

    // Informa ao banco qual "porteiro" (DAO) ele deve usar
    abstract fun consumoAguaDao(): ConsumoAguaDao

}