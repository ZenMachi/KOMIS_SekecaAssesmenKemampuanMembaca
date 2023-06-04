package com.dokari4.sekeca.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ScoreEntity::class], version = 1)
abstract class ScoreDatabase : RoomDatabase() {
    abstract val dao: ScoreDao

companion object {
    @Volatile
    private var INSTANCE:ScoreDatabase? = null

    @JvmStatic
    fun getInstance(context: Context): ScoreDatabase {
        if (INSTANCE == null) {
            synchronized(ScoreDatabase::class.java) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    ScoreDatabase::class.java,
                    "Score_Database"
                )
                    .build()
            }
        }
        return INSTANCE as ScoreDatabase
    }
}
}