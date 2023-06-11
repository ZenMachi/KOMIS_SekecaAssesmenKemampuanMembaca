package com.dokari4.sekeca.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "database")
data class Model(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "question")
    val question: String,
    @ColumnInfo(name = "answer")
    val answer: String? = null,
    @ColumnInfo(name = "score")
    val score: Double? = null,
    @ColumnInfo(name = "category")
    val category: String
    )
