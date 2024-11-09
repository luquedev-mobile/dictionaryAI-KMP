package com.luquedev.dictionaryai

import com.luquedev.dictionaryai.database.getDatabaseBuilder
import org.koin.core.module.Module
import org.koin.dsl.module

actual val nativeModule: Module = module {
    single {
        getDatabaseBuilder(get())
    }
}