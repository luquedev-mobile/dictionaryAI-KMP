package com.luquedev.dictionaryai.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.luquedev.dictionaryai.framework.database.DATABASE_NAME
import com.luquedev.dictionaryai.framework.database.DictionaryDatabase

fun getDatabaseBuilder(ctx: Context): RoomDatabase.Builder<DictionaryDatabase> {
    val appContext = ctx.applicationContext
    val dbFile = appContext.getDatabasePath(DATABASE_NAME)
    return Room.databaseBuilder(
        context = appContext,
        DictionaryDatabase::class.java,
        name = dbFile.absolutePath
    )
}


