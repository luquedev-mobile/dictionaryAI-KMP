package com.luquedev.dictionaryai.usecases

import com.luquedev.dictionaryai.data.WordsRepository
import com.luquedev.dictionaryai.domain.Word
import kotlinx.coroutines.flow.Flow

class SearchWordsUseCase(
    private val wordsRepository: WordsRepository
) {
    operator fun invoke(query: String): Flow<List<Word>> = wordsRepository.searchWords(query)
}