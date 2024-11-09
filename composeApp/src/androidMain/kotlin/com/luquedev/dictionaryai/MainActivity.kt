package com.luquedev.dictionaryai

import android.content.Context
import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.luquedev.dictionaryai.database.getDatabaseBuilder
import com.luquedev.dictionaryai.ui.wordDetail.ITextToSpeech
import com.luquedev.dictionaryai.ui.wordDetail.SpeakerModer
import java.util.Locale

class TextToSpeech(
    context: Context
) : ITextToSpeech {
    private val textToSpeech = TextToSpeech(context, null)

    override fun speak(text: String, speakerRate: SpeakerModer) {
        textToSpeech.setSpeechRate(speakerRate.speechRate)
        @Suppress("DEPRECATION")
        if (textToSpeech.language.isO3Language != Locale.UK.isO3Language) {
            textToSpeech.language = Locale.UK
        }
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }

}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val textToSpeech = TextToSpeech(this)

        setContent {
            App(
                textToSpeech
            )
        }
    }
}
