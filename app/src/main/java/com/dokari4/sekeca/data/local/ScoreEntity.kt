package com.dokari4.sekeca.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "list_score")
data class ScoreEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 1,
    val score: Long
)
