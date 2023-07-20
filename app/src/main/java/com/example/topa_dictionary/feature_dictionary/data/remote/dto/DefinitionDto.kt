package com.example.topa_dictionary.feature_dictionary.data.remote.dto

data class DefinitionDto(
    val antonyms: List<Any>?=null,
    val definition: String?=null,
    val example: String?=null,
    val synonyms: List<String>?=null
)