package com.dokari4.sekeca.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ScoreDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertScore(scoreEntity: ScoreEntity)

//    @Query("SELECT SUM(score) FROM ")
}