package com.luquedev.dictionaryai

import androidx.compose.runtime.Composable
import com.luquedev.dictionaryai.ui.AppScaffold
import com.luquedev.dictionaryai.ui.wordDetail.ITextToSpeech
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(
    textToSpeech: ITextToSpeech
) {
    AppScaffold(textToSpeech)
}