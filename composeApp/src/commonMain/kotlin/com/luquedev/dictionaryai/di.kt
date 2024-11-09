package com.luquedev.dictionaryai

import androidx.room.RoomDatabase
import com.luquedev.dictionaryai.data.AiRepository
import com.luquedev.dictionaryai.data.WordsRepository
import com.luquedev.dictionaryai.data.datasource.AiRemoteDataSource
import com.luquedev.dictionaryai.data.datasource.WordsLocalDataSource
import com.luquedev.dictionaryai.framework.AiServerDataSource
import com.luquedev.dictionaryai.framework.WordsRoomDataSource
import com.luquedev.dictionaryai.framework.database.DictionaryDatabase
import com.luquedev.dictionaryai.framework.database.WordsDao
import com.luquedev.dictionaryai.framework.database.getRoomDatabase
import com.luquedev.dictionaryai.ui.search.SearchViewModel
import com.luquedev.dictionaryai.ui.wordDetail.WordDetailViewModel
import com.luquedev.dictionaryai.usecases.DeleteWordUseCase
import com.luquedev.dictionaryai.usecases.FetchGenerateWordDetailUseCase
import com.luquedev.dictionaryai.usecases.GetRecentWordsUseCase
import com.luquedev.dictionaryai.usecases.InsertWordUseCase
import com.luquedev.dictionaryai.usecases.SearchWordsUseCase
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.core.qualifier.named
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    single(named("apiKey")) { BuildConfig.API_KEY }
    single<WordsDao> {
        val dbBuilder = getRoomDatabase(get<RoomDatabase.Builder<DictionaryDatabase>>())
        dbBuilder.wordsDao()
    }
}

val diSearchModule = module {
    factoryOf(::WordsRoomDataSource) bind WordsLocalDataSource::class
    factoryOf(::WordsRepository)
    factoryOf(::DeleteWordUseCase)
    factoryOf(::GetRecentWordsUseCase)
    factoryOf(::InsertWordUseCase)
    factoryOf(::SearchWordsUseCase)
    viewModelOf(::SearchViewModel)
}

val diWordDetailModule = module {
    factoryOf(::AiServerDataSource) bind AiRemoteDataSource::class
    factoryOf(::AiRepository)
    factoryOf(::FetchGenerateWordDetailUseCase)
    viewModelOf(::WordDetailViewModel)
}

val serviceModule = module {
    single<HttpClient> {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }
            install(DefaultRequest) {
                url {
                    protocol = URLProtocol.HTTPS
                    host = "generativelanguage.googleapis.com"
                    parameters.append("key", BuildConfig.API_KEY)
                }
            }
        }
    }
}

expect val nativeModule: Module

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(appModule, diSearchModule, diWordDetailModule, serviceModule, nativeModule)
    }
}