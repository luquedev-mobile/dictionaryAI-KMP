package com.luquedev.dictionaryai.data

import com.luquedev.dictionaryai.Result
import com.luquedev.dictionaryai.data.datasource.AiRemoteDataSource
import com.luquedev.dictionaryai.domain.WordDetailItem
import com.luquedev.dictionaryai.framework.remote.wordDetail.WordDetailRequest
import kotlinx.coroutines.flow.Flow

class AiRepository(
    private val aiRemoteDataSource: AiRemoteDataSource
) {
    fun generateWordDetail(
        wordDetailRequest: WordDetailRequest
    ): Flow<Result<WordDetailItem>> = aiRemoteDataSource.generateWordDetail(wordDetailRequest)
}