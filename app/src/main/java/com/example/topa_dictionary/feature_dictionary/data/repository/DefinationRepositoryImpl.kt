package com.example.topa_dictionary.feature_dictionary.data.repository

import com.example.topa_dictionary.feature_dictionary.common_util.Resource
import com.example.topa_dictionary.feature_dictionary.data.remote.DictionaryApi
import com.example.topa_dictionary.feature_dictionary.data.remote.dto.DefinationResponseModelDto
import com.example.topa_dictionary.feature_dictionary.di.IoDispatcher
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DefinationRepositoryImpl @Inject constructor(
    private val dictionaryApi: DictionaryApi,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher

) : DefinationRepository {
    override suspend fun getDefination(word: String): Flow<Resource<List<DefinationResponseModelDto>>> =
        flow {
            emit(Resource.Loading())
            when (val response = dictionaryApi.getDefination(word = word)) {
                is NetworkResponse.Success -> {
                    val definationResponse = response.body
                    emit(Resource.Success(data = definationResponse))
                }

                is NetworkResponse.ServerError -> {
                    emit(
                        Resource.Error(response.body?.message ?: "Try again with a new word")
                    )
                }

                is NetworkResponse.NetworkError -> {
                    emit(
                        Resource.Error(message = "Check your internet connection")
                    )

                }

                is NetworkResponse.UnknownError -> {
                    emit(
                        Resource.Error(message = "Unknown error")
                    )
                }
            }
        }.flowOn(ioDispatcher)
}