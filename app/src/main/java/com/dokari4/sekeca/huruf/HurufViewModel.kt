package com.dokari4.sekeca.huruf

import android.app.Application
import android.content.Intent
import android.speech.RecognizerIntent
import android.speech.tts.TextToSpeech
import androidx.activity.result.ActivityResultLauncher
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dokari4.sekeca.data.local.Model
import com.dokari4.sekeca.data.local.ScoreEntity
import com.dokari4.sekeca.data.local.ScoreRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Locale

class HurufViewModel(application: Application) : ViewModel() {
    private lateinit var textToSpeechEngine: TextToSpeech
    private lateinit var startForResult: ActivityResultLauncher<Intent>

    private val mScoreRepository: ScoreRepository = ScoreRepository(application)


    fun insertScore(scoreEntity: ScoreEntity) {
        mScoreRepository.insertScore(scoreEntity)
    }

    val getTotalScore = mScoreRepository.getTotalScore()

    fun initialTextSpeech(speechToTextLauncher: ActivityResultLauncher<Intent>) =
        viewModelScope.launch {
            startForResult = speechToTextLauncher
        }

    fun displaySpeechRecognizer() {
        startForResult.launch(
            with(Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)) {
                putExtra(
                    RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
                )
                putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale("in_ID"))
                putExtra(RecognizerIntent.EXTRA_PROMPT, Locale("Bicara Sekarang"))
            }
        )
    }
}