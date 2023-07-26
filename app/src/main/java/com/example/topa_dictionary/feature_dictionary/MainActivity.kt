package com.example.topa_dictionary.feature_dictionary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.topa_dictionary.feature_dictionary.presentation.HomeScreenUI
import com.example.topa_dictionary.feature_dictionary.presentation.ui_state.DefaultState
import com.example.topa_dictionary.ui.theme.Topa_DictionaryTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Topa_DictionaryTheme {

                HomeScreenUI()
            }
        }
    }
}

