package com.dokari4.sekeca.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface QuestionDao {

    @Insert
    suspend fun insertData(model: Model)

    @Update
    suspend fun updateData(model: Model)

    @Query("SELECT SUM(score) FROM `database`")
    fun getTotalScore(): LiveData<Double>

    @Query("select * from `database` where category like :category")
    fun getQuestionCategory(category: String): LiveData<MutableList<Model>>
}