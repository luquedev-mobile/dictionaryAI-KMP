package com.luquedev.dictionaryai.usecases

import com.luquedev.dictionaryai.data.WordsRepository
import com.luquedev.dictionaryai.domain.Word
import kotlinx.coroutines.flow.Flow

class GetRecentWordsUseCase(
    private val wordsRepository: WordsRepository
) {
    operator fun invoke(): Flow<List<Word>> = wordsRepository.getRecentWords
}