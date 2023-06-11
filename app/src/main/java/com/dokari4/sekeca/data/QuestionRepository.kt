package com.dokari4.sekeca.data

import android.app.Application
import androidx.lifecycle.LiveData
import com.dokari4.sekeca.data.local.Model
import com.dokari4.sekeca.data.local.QuestionDao
import com.dokari4.sekeca.data.local.QuestionDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class QuestionRepository(application: Application) {
    private val mQuestionDao: QuestionDao
    private val executorService : ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = QuestionDatabase.getInstance(application)
        mQuestionDao = db.dao
    }

    fun getTotalScore(): LiveData<Double> = mQuestionDao.getTotalScore()

    fun updateData(model: Model) {
        CoroutineScope(Dispatchers.IO).launch {
            mQuestionDao.updateData(model)
        }
    }

    fun resetScore() {
        CoroutineScope(Dispatchers.IO).launch {
            mQuestionDao.resetScore()
        }
    }

    fun getHurufQuestion(): LiveData<MutableList<Model>> = mQuestionDao.getQuestionCategory("Huruf")
    fun getSukuKataQuestion(): LiveData<MutableList<Model>> = mQuestionDao.getQuestionCategory("Suku Kata")
    fun getKataBermaknaQuestion(): LiveData<MutableList<Model>> = mQuestionDao.getQuestionCategory("Kata Bermakna")
    fun getKataTidakBermaknaQuestion(): LiveData<MutableList<Model>> = mQuestionDao.getQuestionCategory("Kata Tidak Bermakna")
}