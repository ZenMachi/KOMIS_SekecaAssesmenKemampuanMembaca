package com.dokari4.sekeca.data.local

import android.app.Application
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ScoreRepository(application: Application) {
    private val mScoreDao: ScoreDao
    private val executorService : ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = ScoreDatabase.getInstance(application)
        mScoreDao = db.dao
    }

    fun getTotalScore(): LiveData<Double> = mScoreDao.getTotalScore()

    fun insertScore(scoreEntity: ScoreEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            mScoreDao.insertScore(scoreEntity)
        }
    }
}