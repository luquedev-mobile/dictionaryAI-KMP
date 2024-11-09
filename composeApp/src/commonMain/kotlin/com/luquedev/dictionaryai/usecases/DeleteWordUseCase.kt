package com.luquedev.dictionaryai.usecases

import com.luquedev.dictionaryai.data.WordsRepository

class DeleteWordUseCase(
    private val wordsRepository: WordsRepository
) {
    suspend operator fun invoke(word: String) {
        wordsRepository.deleteWord(word)
    }
}