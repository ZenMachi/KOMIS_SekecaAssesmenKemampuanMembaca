package com.dokari4.sekeca.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface QuestionDao {

    @Insert
    suspend fun insertUser(user: User)

    @Query("update user set scoreTotal = :score where nama = :nama")
    suspend fun updateUserScore(nama: String, score: Double)

    @Query("select * from user")
    fun getUser(): LiveData<MutableList<User>>

    @Insert
    suspend fun insertData(model: Model)

    @Update
    suspend fun updateData(model: Model)

    @Query("update `database` set score = null, answer = null")
    suspend fun resetScoreAndAnswer()

    @Query("SELECT SUM(score) FROM `database`")
    fun getTotalScore(): LiveData<Double>

    @Query("select * from `database` where category like :category")
    fun getQuestionCategory(category: String): LiveData<MutableList<Model>>
}