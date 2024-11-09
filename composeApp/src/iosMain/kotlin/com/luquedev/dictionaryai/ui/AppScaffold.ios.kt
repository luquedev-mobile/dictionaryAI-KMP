package com.luquedev.dictionaryai.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.useContents
import platform.CoreGraphics.CGRectMake
import platform.UIKit.UIApplication
import platform.UIKit.UIColor
import platform.UIKit.UIScreen
import platform.UIKit.UIView

@OptIn(ExperimentalForeignApi::class)
@Composable
private fun statusBarView(): UIView = remember {
    // Obtiene la ventana clave
    val keyWindow = UIApplication.sharedApplication.keyWindow ?: return@remember UIView()

    // Obtiene los insets seguros y el ancho de pantalla
    val topInset = keyWindow.safeAreaInsets.useContents { this.top }
    val screenWidth = UIScreen.mainScreen.bounds.useContents { this.size.width }

    // Identificador único para la vista de la barra de estado
    val tag = 3848245L

    // Intenta obtener una vista de barra de estado existente por tag
    val statusBarView = keyWindow.viewWithTag(tag) ?: UIView(frame = CGRectMake(0.0, 0.0, screenWidth, topInset)).apply {
        this.tag = tag
        this.layer.zPosition = 999999.0
        keyWindow.addSubview(this)
    }

    statusBarView // Retorna la vista
}

@Composable
actual fun PlatformStatusBarColor(statusBarColor: Color) {
    val statusBar = statusBarView()
    SideEffect {
        statusBar.backgroundColor = statusBarColor.toUIColor()
    }
}

private fun Color.toUIColor(): UIColor = UIColor(
    red = this.red.toDouble(),
    green = this.green.toDouble(),
    blue = this.blue.toDouble(),
    alpha = this.alpha.toDouble()
)