package com.example.meucontadordeagua.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

// @Singleton garante que só teremos UM repositório no app
// @Inject constructor() diz ao Hilt como criar este repositório.
// O Hilt vê que ele precisa de um 'ConsumoAguaDao', e como já ensinamos
// o Hilt a criar o DAO (no AppModule), ele o "injeta" automaticamente.
@Singleton
class AguaRepository @Inject constructor(
    private val consumoAguaDao: ConsumoAguaDao
) {

    // Esta função será chamada pelo ViewModel quando o usuário adicionar água
    suspend fun adicionarConsumo(quantidadeMl: Int) {
        val novoConsumo = ConsumoAgua(quantidadeMl = quantidadeMl)
        consumoAguaDao.inserir(novoConsumo)
    }

    // Esta função permite que o ViewModel "assista" ao total de ML consumido hoje
    fun getConsumoDeHoje(): Flow<Int?> {
        return consumoAguaDao.getConsumoDeHoje()
    }

    // (Opcional, mas bom ter) Permite ao ViewModel "assistir" a lista de registros de hoje
    fun getRegistrosDeHoje(): Flow<List<ConsumoAgua>> {
        return consumoAguaDao.getRegistrosDeHoje()
    }
}