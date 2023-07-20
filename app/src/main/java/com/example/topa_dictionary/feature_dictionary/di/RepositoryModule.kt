package com.example.topa_dictionary.feature_dictionary.di

import com.example.topa_dictionary.feature_dictionary.data.remote.DictionaryApi
import com.example.topa_dictionary.feature_dictionary.data.repository.DefinationRepository
import com.example.topa_dictionary.feature_dictionary.data.repository.DefinationRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideDefinationRepository(
        dictionaryApi: DictionaryApi,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): DefinationRepository {
        return DefinationRepositoryImpl(
            dictionaryApi = dictionaryApi,
            ioDispatcher = ioDispatcher
        )
    }
}