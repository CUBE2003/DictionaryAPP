package com.example.topa_dictionary.feature_dictionary.data.remote

import com.example.topa_dictionary.feature_dictionary.data.remote.dto.DefinationResponseModelDto
import com.example.topa_dictionary.feature_dictionary.data.remote.util.ErrorResponse
import com.haroldadmin.cnradapter.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryApi {
    @GET("api/v2/entries/en/{word}")
    suspend fun getDefination(
        @Path("word") word: String
    ): NetworkResponse<List<DefinationResponseModelDto>, ErrorResponse>


}
