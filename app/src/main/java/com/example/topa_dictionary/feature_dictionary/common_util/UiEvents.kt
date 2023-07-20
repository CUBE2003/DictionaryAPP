package com.example.topa_dictionary.feature_dictionary.common_util

sealed class UiEvents{
    data class SnackBarEvent(val message :String): UiEvents()
}
