package com.example.meucontadordeagua.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao // Diz ao Room que esta é a interface "porteiro"
interface ConsumoAguaDao {

    // Comando para inserir um novo consumo
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserir(consumo: ConsumoAgua) // 'suspend' é para Coroutines

    // Comando para buscar o total de ML consumido HOJE
    // Estamos usando Flow, que é a forma moderna (similar ao LiveData)
    @Query("SELECT SUM(quantidadeMl) FROM consumo_agua_table WHERE DATE(timestamp / 1000, 'unixepoch') = DATE('now', 'localtime')")
    fun getConsumoDeHoje(): Flow<Int?> // Retorna o total de ML (ex: 750)

    // Comando para buscar todos os registros de hoje (para um histórico diário, se quisermos)
    @Query("SELECT * FROM consumo_agua_table WHERE DATE(timestamp / 1000, 'unixepoch') = DATE('now', 'localtime') ORDER BY timestamp DESC")
    fun getRegistrosDeHoje(): Flow<List<ConsumoAgua>>
}