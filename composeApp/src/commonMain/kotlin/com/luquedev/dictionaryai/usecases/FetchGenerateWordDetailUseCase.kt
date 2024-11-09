package com.luquedev.dictionaryai.usecases

import com.luquedev.dictionaryai.Result
import com.luquedev.dictionaryai.data.AiRepository
import com.luquedev.dictionaryai.domain.WordDetailItem
import com.luquedev.dictionaryai.framework.remote.wordDetail.WordDetailRequest
import kotlinx.coroutines.flow.Flow

class FetchGenerateWordDetailUseCase(private val aiRepository: AiRepository) {
    operator fun invoke(request: WordDetailRequest): Flow<Result<WordDetailItem>> = aiRepository.generateWordDetail(request)
}