package com.example.topa_dictionary.feature_dictionary.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.topa_dictionary.feature_dictionary.common_util.Resource
import com.example.topa_dictionary.feature_dictionary.common_util.UiEvents
import com.example.topa_dictionary.feature_dictionary.data.repository.DefinationRepository
import com.example.topa_dictionary.feature_dictionary.presentation.ui_state.DefinationUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DefinationViewModel @Inject constructor(
    private val definationRepository: DefinationRepository
) : ViewModel() {

    private val _definitionUiState = MutableStateFlow(DefinationUiState())
    val definitionUiState: StateFlow<DefinationUiState> = _definitionUiState.asStateFlow()

    private val _typedWord = mutableStateOf("")
    val typedWord: State<String> = _typedWord

    fun setTypedWord(typedWord:String){
        _typedWord.value=typedWord
    }

    private val _eventFlow = MutableSharedFlow<UiEvents>()
    val eventFlow: SharedFlow<UiEvents> = _eventFlow.asSharedFlow()

    fun getDefinition() {
        _definitionUiState.value =
            definitionUiState.value.copy(
                isLoading = true
            )

        val word = typedWord.value

        if (word.isNotEmpty()) {
            viewModelScope.launch {
                definationRepository.getDefination(word = word).collect { response ->
                    when (response) {
                        is Resource.Error -> {
                            _definitionUiState.value = definitionUiState.value.copy(
                                isLoading = false,
                                defination = emptyList()
                            )

                            _eventFlow.emit(
                                UiEvents.SnackBarEvent(
                                    message = response.message ?: "Something went wrong!"
                                )
                            )
                        }

                        is Resource.Success -> {
                            _definitionUiState.value = definitionUiState.value.copy(
                                isLoading = false,
                                defination = response.data
                            )
                        }

                        else -> {
                            definitionUiState
                        }
                    }
                }
            }
        } else {
            showErrorMessage()
        }
    }

    private fun showErrorMessage() {
        viewModelScope.launch {
            _definitionUiState.value =
                definitionUiState.value.copy(
                    isLoading = false
                )

            _eventFlow.emit(
                UiEvents.SnackBarEvent(
                    message = "Please enter a word"
                )
            )
        }
    }

}

