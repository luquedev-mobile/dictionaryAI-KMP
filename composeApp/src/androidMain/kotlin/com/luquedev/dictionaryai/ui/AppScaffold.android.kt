package com.luquedev.dictionaryai.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.luquedev.dictionaryai.theme.getColorScheme

@Composable
actual fun PlatformStatusBarColor(statusBarColor: Color) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(getColorScheme().primary)
}