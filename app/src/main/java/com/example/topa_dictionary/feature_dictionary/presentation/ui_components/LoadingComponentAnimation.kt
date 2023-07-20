package com.example.topa_dictionary.feature_dictionary.presentation.ui_components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun LoadingComponent(
    isLoading: Boolean
) {
//    Box(
//        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
//    ) {
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
//        ) {
//
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            Text(
//                text = "Please wait while we get the definition...",
//                fontSize = 20.sp,
//                textAlign = TextAlign.Center
//            )
//
//        }
//    }
    if (isLoading){
        Text(
            text = "Please wait while we get the definition...",
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )

    }

}

