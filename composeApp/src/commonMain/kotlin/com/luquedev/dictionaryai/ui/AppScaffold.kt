package com.luquedev.dictionaryai.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.luquedev.dictionaryai.theme.DictionaryAITheme
import com.luquedev.dictionaryai.theme.getColorScheme
import com.luquedev.dictionaryai.ui.common.topBar.TopAppBarOvalShape
import com.luquedev.dictionaryai.ui.common.topBar.TopBar
import com.luquedev.dictionaryai.ui.search.SearchScreen
import com.luquedev.dictionaryai.ui.wordDetail.ITextToSpeech
import com.luquedev.dictionaryai.ui.wordDetail.WordDetail

sealed class NavScreen(val route: String) {
    data object Search : NavScreen("search")
    data object WordDetail : NavScreen("wordDetail/{${NavArgs.Word.key}}") {
        fun createRoute(word: String) = "wordDetail/$word"
    }
}

enum class NavArgs(val key: String) {
    Word("word")
}

@Composable
expect fun PlatformStatusBarColor(statusBarColor: Color)

@Composable
fun AppScaffold(
    textToSpeech: ITextToSpeech
) {
    val navController = rememberNavController()
    var showBottomBar by remember { mutableStateOf(true) }
    var topAppBar by remember { mutableStateOf<@Composable () -> Unit>({}) }

    PlatformStatusBarColor(getColorScheme().primary)

    DictionaryAITheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Scaffold(
                topBar = {
                    topAppBar()
                },
                contentWindowInsets = WindowInsets.safeDrawing,
            ) { paddingValues ->
                Column(
                    modifier = Modifier
                        .padding(paddingValues)
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = NavScreen.Search.route
                    ) {
                        composable(NavScreen.Search.route) {
                            topAppBar = { TopAppBarOvalShape() }
                            SearchScreen(
                                onSearch = {
                                    navController.navigate(NavScreen.WordDetail.createRoute(it))
                                }
                            )
                        }

                        composable(
                            route = NavScreen.WordDetail.route,
                            arguments = listOf(navArgument(NavArgs.Word.key) {
                                type = NavType.StringType
                            })
                        ) { backStackEntry ->
                            val word =
                                requireNotNull(backStackEntry.arguments?.getString(NavArgs.Word.key))
                            topAppBar = {
                                TopBar(title = "Detalle de la palabra") {
                                    navController.popBackStack()
                                }
                            }
                            WordDetail(
                                word = word,
                                textToSpeech = textToSpeech
                            )
                        }
                    }
                }
            }
        }
    }
}