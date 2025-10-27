package com.example.meucontadordeagua.di // (Dentro do pacote 'di')

import android.content.Context
import androidx.room.Room
import com.example.meucontadordeagua.data.AguaDatabase
import com.example.meucontadordeagua.data.ConsumoAguaDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module // Indica ao Hilt que este é um módulo
@InstallIn(SingletonComponent::class) // As dependências aqui viverão o tempo que o app viver
object AppModule {

    // 1. Ensina o Hilt a criar o Banco de Dados (AguaDatabase)
    @Provides
    @Singleton // Garante que só existirá UMA instância do banco no app inteiro
    fun provideAguaDatabase(@ApplicationContext context: Context): AguaDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AguaDatabase::class.java,
            "agua_database" // Nome do arquivo do banco de dados
        )
            .fallbackToDestructiveMigration() // (Usado em desenvolvimento)
            .build()
    }

    // 2. Ensina o Hilt a criar o DAO (ConsumoAguaDao)
    // O Hilt já sabe criar o 'AguaDatabase' (graças à função acima),
    // então ele usa essa informação para criar o DAO.
    @Provides
    @Singleton
    fun provideConsumoAguaDao(database: AguaDatabase): ConsumoAguaDao {
        return database.consumoAguaDao()
    }

}