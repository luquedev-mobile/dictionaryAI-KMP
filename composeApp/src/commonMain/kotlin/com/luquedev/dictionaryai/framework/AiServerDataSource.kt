package com.luquedev.dictionaryai.framework

import com.luquedev.dictionaryai.Result
import com.luquedev.dictionaryai.data.datasource.AiRemoteDataSource
import com.luquedev.dictionaryai.domain.Meaning
import com.luquedev.dictionaryai.domain.WordDetailItem
import com.luquedev.dictionaryai.framework.remote.RemoteMeaning
import com.luquedev.dictionaryai.framework.remote.RemoteWordDetailItem
import com.luquedev.dictionaryai.framework.remote.generateContent.GenerateContentResponse
import com.luquedev.dictionaryai.framework.remote.wordDetail.WordDetailRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json

class AiServerDataSource(
    private val aiService: HttpClient
) : AiRemoteDataSource {
    override fun generateWordDetail(wordDetailRequest: WordDetailRequest): Flow<Result<WordDetailItem>> = flow {
        safetyCall<GenerateContentResponse, WordDetailItem>(
            apiCall = {
                aiService
                    .post("/v1beta/models/gemini-1.5-flash-latest:generateContent") {
                        setBody(wordDetailRequest)
                        contentType(ContentType.Application.Json)
                    }
                    .body()
            },
            transform = {
                it.convertTextToSpecificType<RemoteWordDetailItem>().toResultDomain()
            }
        )
    }
}

private fun Result<RemoteWordDetailItem>.toResultDomain(): Result<WordDetailItem> {
    return map { it.toDomain() }
}

private fun RemoteWordDetailItem.toDomain(): WordDetailItem {
    return WordDetailItem(
        meanings = this.meanings?.map { it.toDomain() },
        word = this.word
    )
}

private fun RemoteMeaning.toDomain(): Meaning {
    return Meaning(
        exampleEnglish = this.exampleEnglish,
        exampleSpanish = this.exampleSpanish,
        explanation = this.explanation,
        mean = this.mean,
    )
}

suspend inline fun <reified T, R> FlowCollector<Result<R>>.safetyCall(
    apiCall: () -> HttpResponse,
    noinline transform: ((T) -> Result<R>)? = null
) {
    try {
        val response = apiCall()
        val data = response.body<T>()

        if (transform != null) {
            emit(transform(data))
        } else {
            @Suppress("UNCHECKED_CAST")
            emit(Result.Success(data as R))
        }

    } catch (e: Exception) {
        println("error: $e")
        emit(Result.Error(e))
    }
}

private inline fun <reified T> GenerateContentResponse.convertTextToSpecificType(): Result<T> {
    return try {
        Result.Success(
            Json.decodeFromString<T>(
                this.candidates[0].content.parts[0].text
            )
        )
    } catch (e: Exception) {
        println("error: $e")
        Result.Error(e)
    }
}



