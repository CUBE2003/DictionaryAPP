package com.example.topa_dictionary.feature_dictionary.data.repository

import com.example.topa_dictionary.feature_dictionary.common_util.Resource
import com.example.topa_dictionary.feature_dictionary.data.remote.dto.DefinationResponseModelDto
import kotlinx.coroutines.flow.Flow

interface DefinationRepository {
    suspend fun getDefination(word:String): Flow<Resource<List<DefinationResponseModelDto>>>
}