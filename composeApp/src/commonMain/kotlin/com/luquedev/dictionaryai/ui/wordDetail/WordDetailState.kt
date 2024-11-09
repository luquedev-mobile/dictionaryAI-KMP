package com.luquedev.dictionaryai.ui.wordDetail

import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.luquedev.dictionaryai.domain.Meaning
import com.luquedev.dictionaryai.framework.remote.common.Content
import com.luquedev.dictionaryai.framework.remote.common.Part
import com.luquedev.dictionaryai.framework.remote.wordDetail.WordDetailRequest
import com.luquedev.dictionaryai.theme.getColorScheme
import com.luquedev.dictionaryai.ui.common.readFile.ManagerJsonFile
import kotlinx.coroutines.flow.Flow

interface ITextToSpeech {
    fun speak(text: String, speakerRate: SpeakerModer)
}

class WordDetailState(
    var lastRequest: MutableState<() -> Unit>,
    private val events: Flow<WordDetailViewModel.WordDetailEvent>,
    private val textToSpeech: ITextToSpeech
) {
    @Composable
    fun GetWordDetailRequest(
        word: String,
        getContent: (WordDetailRequest?) -> Unit
    ) {
        ManagerJsonFile<WordDetailRequest>(
            getContent = { wordDetailRequest ->
                wordDetailRequest?.contents = mutableListOf(
                    Content(
                        parts = listOf(
                            Part(
                                text = "significados de '$word'",
                            )
                        ),
                        role = "user"
                    )
                )

                getContent(wordDetailRequest)
            },
            fileName = "wordDetailRequest.json"
        )
    }

    fun getTitleAnnotatedString(word: String) = buildAnnotatedString {
        withStyle(
            SpanStyle(
                fontSize = 24.sp
            )
        ) {
            append("Significados de ")
        }
        withStyle(
            SpanStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight(500)
            )
        ) {
            append(word)
        }
    }

    @Composable
    fun TextToSpeechEvent() {
        val lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current
        LaunchedEffect(lifecycleOwner) {
            lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                events.collect { event ->
                    when (event) {
                        is WordDetailViewModel.WordDetailEvent.Speak -> {
                            textToSpeech.speak(event.text, event.speakerModer)
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun getMeaningAnnotatedString(meaning: Meaning, index: Int) = buildAnnotatedString {
        meaning.partOfSpeech?.let {
            append("${index}. ${meaning.partOfSpeech} ")
        } ?: run {
            append("${index}. ")
        }
        withStyle(
            SpanStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight(1000),
                color = getColorScheme().primary
            )
        ) {
            append(meaning.mean)
        }
        meaning.explanation?.let {
            append(": ${meaning.explanation}")
        }
    }

    fun getExampleAnnotatedString(meaning: Meaning) = buildAnnotatedString {
        meaning.exampleEnglish?.let {
            withStyle(
                SpanStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500)
                )
            ) {
                append("· ${meaning.exampleEnglish}")
            }
        }
        meaning.exampleSpanish?.let {
            append(" (${meaning.exampleSpanish})")
        }
    }
}

@Composable
fun rememberWordDetailState(
    lazyGridState: LazyGridState = rememberLazyGridState(),
    lastRequest: MutableState<() -> Unit> = remember { mutableStateOf({}) },
    events: Flow<WordDetailViewModel.WordDetailEvent>,
    textToSpeech: ITextToSpeech
): WordDetailState {
    return remember(lazyGridState) {
        WordDetailState(lastRequest, events, textToSpeech)
    }
}