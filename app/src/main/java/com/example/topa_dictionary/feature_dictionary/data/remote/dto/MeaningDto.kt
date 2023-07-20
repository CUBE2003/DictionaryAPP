package com.example.topa_dictionary.feature_dictionary.data.remote.dto

data class MeaningDto(
    val definitions: List<DefinitionDto>?=null,
    val partOfSpeech: String?=null,
)