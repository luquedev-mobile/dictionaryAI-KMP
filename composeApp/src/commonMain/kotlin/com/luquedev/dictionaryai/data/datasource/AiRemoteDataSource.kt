package com.luquedev.dictionaryai.data.datasource

import com.luquedev.dictionaryai.Result
import com.luquedev.dictionaryai.domain.WordDetailItem
import com.luquedev.dictionaryai.framework.remote.wordDetail.WordDetailRequest
import kotlinx.coroutines.flow.Flow

interface AiRemoteDataSource {
    fun generateWordDetail(
        wordDetailRequest: WordDetailRequest
    ): Flow<Result<WordDetailItem>>
}
