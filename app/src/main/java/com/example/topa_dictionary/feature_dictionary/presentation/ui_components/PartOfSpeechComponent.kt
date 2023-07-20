package com.example.topa_dictionary.feature_dictionary.presentation.ui_components

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun PartsOfSpeechComponent(
    headerText: String,
    size: Int,

) {
    Button(
        onClick = {},
        elevation = ButtonDefaults.buttonElevation(0.dp),

        modifier = Modifier.semantics { contentDescription = "PartsOfSpeech" }
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontSize = 18.sp,


                    )
                ) {
                    append("$headerText ")
                }
                withStyle(
                    style = SpanStyle(


                    )
                ) {
                    append("($size)")
                }
            }
        )
    }
}