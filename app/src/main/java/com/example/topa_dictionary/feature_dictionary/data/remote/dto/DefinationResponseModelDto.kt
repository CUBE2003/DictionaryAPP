package com.example.topa_dictionary.feature_dictionary.data.remote.dto

data class DefinationResponseModelDto(

    val meanings: List<MeaningDto>?=null,
    val phonetic: String?=null,
    val phonetics: List<PhoneticDto>?=null,
    val word: String?=null
)