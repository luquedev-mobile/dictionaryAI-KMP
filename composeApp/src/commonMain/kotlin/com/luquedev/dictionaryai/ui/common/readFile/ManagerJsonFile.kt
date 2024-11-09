package com.luquedev.dictionaryai.ui.common.readFile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.luquedev.dictionaryai.ui.common.error.ErrorScreen
import dictionaryai.composeapp.generated.resources.Res
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
@Composable
inline fun <reified T> ManagerJsonFile(
    fileName: String,
    crossinline getContent: (T?) -> Unit
) {
    var errorToLoadContent by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        try {
            val jsonStringRequest = Res.readBytes("files/$fileName").decodeToString()

            val request =
                Json.decodeFromString<T>(jsonStringRequest)

            getContent(request)
        } catch (_: Exception) {
            errorToLoadContent = true
        }
    }

    if (errorToLoadContent) {
        ErrorScreen(
            subtitle = "Contacte con el administrador de la aplicación",
            mustShowRetryButton = false
        )
    }
}