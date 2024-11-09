package com.luquedev.dictionaryai.usecases

import com.luquedev.dictionaryai.data.WordsRepository

class InsertWordUseCase(
    private val wordsRepository: WordsRepository
) {
    suspend operator fun invoke(word: String) = wordsRepository.insertWord(word)
}