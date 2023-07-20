package com.example.topa_dictionary.feature_dictionary.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.topa_dictionary.feature_dictionary.common_util.UiEvents
import com.example.topa_dictionary.feature_dictionary.data.remote.dto.MeaningDto
import com.example.topa_dictionary.feature_dictionary.presentation.ui_components.EmptyComponent
import com.example.topa_dictionary.feature_dictionary.presentation.ui_components.LoadingComponent
import com.example.topa_dictionary.feature_dictionary.presentation.ui_components.PartsOfSpeechDefinitioncomponent
import com.example.topa_dictionary.feature_dictionary.presentation.ui_components.PronounciationComponent
import com.example.topa_dictionary.feature_dictionary.presentation.ui_components.SearchTextFieldComponent
import com.example.topa_dictionary.feature_dictionary.presentation.ui_state.DefinationUiState
import com.example.topa_dictionary.ui.theme.Topa_DictionaryTheme
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenUI(
    definationViewModel: DefinationViewModel = hiltViewModel()
) {

    val snackbarHostState = remember {
        SnackbarHostState()
    }
    LaunchedEffect(key1 = true) {
        definationViewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvents.SnackBarEvent -> {
                    snackbarHostState.showSnackbar(event.message)
                }
            }
        }
    }
    val definitionUiState = definationViewModel.definitionUiState.collectAsState().value
    val typedWord = definationViewModel.typedWord.value

    val definitions =
        if (definitionUiState.defination?.isNotEmpty() == true) definitionUiState.defination[0].meanings
            ?: emptyList()
        else emptyList()

    Topa_DictionaryTheme {

        Scaffold(
            snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
        ) { paddingValues ->
            HomeContent(
                definitionUiState = definitionUiState,
                typedWord = typedWord,
                setWordToBeSearched = { word ->
                    definationViewModel.setTypedWord(typedWord = word)
                },
                searchedWord = {
                    definationViewModel.getDefinition()
                },
                meanings = definitions,
                paddingValues = paddingValues
            )
        }


    }


}

@Composable
fun HomeContent(
    definitionUiState: DefinationUiState,
    typedWord: String,
    setWordToBeSearched: (String) -> Unit,
    searchedWord: () -> Unit,
    meanings: List<MeaningDto>,
    paddingValues: PaddingValues = PaddingValues(0.dp)
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        LazyColumn(contentPadding = PaddingValues(14.dp)) {
            item {
                SearchTextFieldComponent(
                    setWordtoBeSearched = setWordToBeSearched,
                    searchWord = searchedWord,
                    typedWord = typedWord
                )
            }

            if (definitionUiState.isLoading || definitionUiState.defination?.isEmpty() == true) {

                item {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        LoadingComponent(
                            isLoading = definitionUiState.isLoading
                        )
                        EmptyComponent(
                            isLoading = definitionUiState.isLoading,
                            definition = definitionUiState.defination
                        )
                    }
                }

            }

            if (!definitionUiState.isLoading && !definitionUiState.defination.isNullOrEmpty()) {
                item {
                    Spacer(modifier = Modifier.height(16.dp))

                    PronounciationComponent(
                        word = definitionUiState.defination[0].word ?: "",
                        phonetic = definitionUiState.defination[0].phonetic ?: "---"
                    )
                }

                items(meanings) { meaning ->
                    Spacer(modifier = Modifier.height(10.dp))

                    PartsOfSpeechDefinitioncomponent(
                        partOfSpeech = meaning.partOfSpeech ?: "",
                        definitions = meaning.definitions ?: emptyList()
                    )

                }

            }


        }
    }

}