package com.example.topa_dictionary.feature_dictionary.presentation.ui_components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.topa_dictionary.feature_dictionary.data.remote.dto.DefinitionDto

@Composable
fun PartsOfSpeechDefinitioncomponent(
    partOfSpeech: String,
    definitions: List<DefinitionDto>?
) {

    Column {

        PartsOfSpeechComponent(
            headerText = partOfSpeech,
            size = definitions?.size ?: 0,

            )

        definitions?.forEachIndexed { index, meaning ->
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(

                            color = Color.White
                        )
                    ) {
                        append("${index + 1}. ")
                    }
                    withStyle(
                        style = SpanStyle(
                            fontFamily = FontFamily.Default
                        )
                    ) {
                        append(meaning.definition ?: "")
                    }
                },
                modifier = Modifier
                    .padding(top = 5.dp)
                    .semantics { contentDescription = "definition" }
            )
        }
    }

}

