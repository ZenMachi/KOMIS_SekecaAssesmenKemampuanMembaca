package com.dokari4.sekeca.huruf

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.dokari4.sekeca.ui.ViewModelfactory
import com.dokari4.sekeca.data.local.Model
import com.dokari4.sekeca.data.local.ScoreEntity
import com.dokari4.sekeca.data.local.questionDatabase
import com.dokari4.sekeca.databinding.ActivityHurufBinding
import com.dokari4.sekeca.ui.HurufAdapter
import com.dokari4.sekeca.utils.Helper
import com.dokari4.sekeca.utils.QuestionClickHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HurufActivity : AppCompatActivity(), QuestionClickHandler {
    private lateinit var binding: ActivityHurufBinding
    private lateinit var hurufViewModel: HurufViewModel
    private var questionAnswer: String? = null
    private var question: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHurufBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Adapter
        val adapter = HurufAdapter(this, questionDatabase.getHuruf() as ArrayList<Model>)
        adapter.handler = this
        binding.recyclerView.apply {
            this.layoutManager = LinearLayoutManager(this@HurufActivity)
            this.adapter = adapter
        }

        //ViewModels
        val factory = ViewModelfactory.getInstance(application)
        hurufViewModel = ViewModelProvider(this, factory)[HurufViewModel::class.java]

        hurufViewModel.initialTextSpeech(speechToText)

        getTotalScore()

    }

    override fun clickedQuestionItem(view: View, model: Model) {
//        Toast.makeText(this, "Question: ${model.question} = $questionAnswer", Toast.LENGTH_SHORT).show()
        hurufViewModel.displaySpeechRecognizer()
        question = model.question
    }

    //SpeechRecognizer
    private val speechToText = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val textFromSpeech = result.data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS).let { it?.get(0) }
            if (textFromSpeech != null) {
                questionAnswer = textFromSpeech
                insertScore(Helper.findSimilarity(questionAnswer, question))
                Toast.makeText(this, "$questionAnswer = $question similarities ${Helper.findSimilarity(questionAnswer, question)}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getTotalScore() {
        binding.btnNext.setOnClickListener {
            hurufViewModel.getTotalScore.observe(this, {
                Helper.createToast(this, it.toString())
            })

        }
//        hurufViewModel.getTotalScore().o
    }

    private fun insertScore(score: Double) {
        hurufViewModel.insertScore(ScoreEntity(score = score))
    }


}