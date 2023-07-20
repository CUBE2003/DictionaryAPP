package com.example.topa_dictionary.feature_dictionary.presentation.ui_state

import com.example.topa_dictionary.feature_dictionary.data.remote.dto.DefinationResponseModelDto

data class DefinationUiState(
    val defination: List<DefinationResponseModelDto>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
