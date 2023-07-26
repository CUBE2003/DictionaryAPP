package com.example.topa_dictionary.feature_dictionary.presentation.ui_state

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DefaultState() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(122.dp)
            ,
//        contentAlignment = Alignment.Center


        ) {


            Text(
                text = "Hello There \uD83D\uDC4B",
                textAlign = TextAlign.Center,
                fontSize = 24.sp
                )

    }
}

