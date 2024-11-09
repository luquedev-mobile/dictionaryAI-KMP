package com.luquedev.dictionaryai

import androidx.compose.ui.window.ComposeUIViewController
import com.luquedev.dictionaryai.ui.wordDetail.ITextToSpeech
import com.luquedev.dictionaryai.ui.wordDetail.SpeakerModer
import platform.AVFAudio.AVSpeechSynthesisVoice
import platform.AVFAudio.AVSpeechSynthesizer
import platform.AVFAudio.AVSpeechUtterance

class TextToSpeech : ITextToSpeech {
    private val synthesizer = AVSpeechSynthesizer()

    override fun speak(text: String, speakerRate: SpeakerModer) {
        val utterance = AVSpeechUtterance(string = text).apply {
            voice = AVSpeechSynthesisVoice.voiceWithLanguage("en-GB")
            rate = speakerRate.speechRate
        }
        synthesizer.speakUtterance(utterance)
    }
}

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App(
        TextToSpeech()
    )
}
