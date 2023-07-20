package com.example.topa_dictionary.feature_dictionary.presentation.ui_components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import com.example.topa_dictionary.ui.theme.Shapes

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchTextFieldComponent(
    setWordtoBeSearched: (String) -> Unit,
    searchWord: () -> Unit,
    typedWord: String
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current


    OutlinedTextField(
        value = typedWord,
        onValueChange = { wordEntered ->
            setWordtoBeSearched(wordEntered)
        },
        modifier = Modifier
            .fillMaxWidth()
            .semantics { contentDescription = "searchTextField" },
        placeholder = {
            Text(text = "Search Here...")
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Search,
                contentDescription = "Search"
            )
        },
        trailingIcon = {
            if (typedWord.isNotEmpty()) {
                Icon(
                    imageVector = Icons.Outlined.Clear,
                    contentDescription = "Clear Icon",
                    modifier = Modifier.clickable {
                        setWordtoBeSearched("")
                    }
                )
            }
        },
        shape = Shapes.small,
        colors = TextFieldDefaults
            .textFieldColors(
                containerColor = Color.Gray,
                unfocusedIndicatorColor = Color(0XFFEBE7E7),
                focusedIndicatorColor = Color(0XFF4C7AF2)
            ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = {
                searchWord()
                keyboardController?.hide()
                focusManager.clearFocus()
            }
        )


    )

}