package com.luquedev.dictionaryai.data.datasource

import com.luquedev.dictionaryai.domain.Word
import kotlinx.coroutines.flow.Flow

interface WordsLocalDataSource {
    val getRecentWords: Flow<List<Word>>
    suspend fun insertWord(word: String)
    fun searchWords(query: String): Flow<List<Word>>

    suspend fun deleteWord(word: String)
}