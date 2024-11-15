package com.luquedev.dictionaryai.ui.common.speaker

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.unit.dp
import com.luquedev.dictionaryai.ui.wordDetail.SpeakerModer
import dictionaryai.composeapp.generated.resources.Res
import dictionaryai.composeapp.generated.resources.ic_face_sad
import dictionaryai.composeapp.generated.resources.ic_sound
import dictionaryai.composeapp.generated.resources.ic_sound_slow
import dictionaryai.composeapp.generated.resources.ic_x
import org.jetbrains.compose.resources.painterResource

@Composable
fun Speaker(
    speak: (SpeakerModer) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(Res.drawable.ic_sound),
            contentDescription = "",
            modifier = Modifier
                .clickable {
                    speak(SpeakerModer.Normal)
                }
        )
        Spacer(modifier = Modifier.width(16.dp))
        Icon(
            painter = painterResource(Res.drawable.ic_sound_slow),
            contentDescription = "",
            modifier = Modifier
                .clickable {
                    speak(SpeakerModer.Slow)
                }
                .size(20.dp)
        )
    }
}