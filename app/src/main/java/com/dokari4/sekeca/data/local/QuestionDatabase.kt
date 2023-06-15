package com.dokari4.sekeca.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dokari4.sekeca.data.PreloadData

@Database(entities = [Model::class, User::class], version = 1)
abstract class QuestionDatabase : RoomDatabase() {
    abstract val dao: QuestionDao

companion object {
    @Volatile
    private var INSTANCE:QuestionDatabase? = null

    @JvmStatic
    fun getInstance(context: Context): QuestionDatabase {
        if (INSTANCE == null) {
            synchronized(QuestionDatabase::class.java) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    QuestionDatabase::class.java,
                    "Question_Database"
                )
                    .addCallback(PreloadData(context))
                    .build()
            }
        }
        return INSTANCE as QuestionDatabase
    }
}
}