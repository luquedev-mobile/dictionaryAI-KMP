package com.luquedev.dictionaryai.framework.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "words")
data class DbWord (
    @PrimaryKey
    val word: String = "",
    val createdAt: Long = 0L//System.currentTimeMillis()
)