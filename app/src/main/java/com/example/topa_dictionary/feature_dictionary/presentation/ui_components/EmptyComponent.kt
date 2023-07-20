package com.example.topa_dictionary.feature_dictionary.presentation.ui_components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.topa_dictionary.feature_dictionary.data.remote.dto.DefinationResponseModelDto

@Composable
fun EmptyComponent(
    isLoading: Boolean,
    definition: List<DefinationResponseModelDto>?

) {
    if (!isLoading && definition.isNullOrEmpty()) {
        Text(
            text = "Sorry definition not found...",
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
    }


}