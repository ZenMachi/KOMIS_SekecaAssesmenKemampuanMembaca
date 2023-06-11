package com.dokari4.sekeca.huruf

import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dokari4.sekeca.data.local.Model
import com.dokari4.sekeca.databinding.ActivityHurufBinding
import com.dokari4.sekeca.ui.HurufAdapter
import com.dokari4.sekeca.ui.ViewModelfactory
import com.dokari4.sekeca.utils.Helper
import com.dokari4.sekeca.utils.QuestionClickHandler

class HurufActivity : AppCompatActivity(), QuestionClickHandler {
    private lateinit var binding: ActivityHurufBinding
    private lateinit var hurufViewModel: HurufViewModel
    private var questionAnswer: String? = null
    private var question: String? = null
    private var category: String? = null
    private var id: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Set Binding
        binding = ActivityHurufBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //ViewModels
        val factory = ViewModelfactory.getInstance(application)
        hurufViewModel = ViewModelProvider(this, factory)[HurufViewModel::class.java]
        hurufViewModel.initialTextSpeech(speechToText)

        //Adapter
        val adapter = HurufAdapter()

        //Fill Data to Adapter
        hurufViewModel.getHurufQuestion.observe(this) {
            adapter.submitList(it)
        }

        //Initialize OnClickListener in Recycler View
        adapter.handler = this

        //Set RecyclerView Properties
        binding.recyclerView.apply {
            this.layoutManager = LinearLayoutManager(this@HurufActivity)
            this.adapter = adapter
        }

        getTotalScore()
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    override fun clickedQuestionItem(view: View, model: Model) {
//        Toast.makeText(this, "Question: ${model.question} = $questionAnswer", Toast.LENGTH_SHORT).show()
        hurufViewModel.displaySpeechRecognizer()
        question = model.question
        id = model.id
        category = model.category
    }

    //SpeechRecognizer
    private val speechToText = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val textFromSpeech = result.data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS).let { it?.get(0) }
            if (textFromSpeech != null) {
                questionAnswer = textFromSpeech
                val score = Helper.findSimilarity(questionAnswer!!.lowercase(), question!!.lowercase())
                val data = Model(id!!, question!!, answer = questionAnswer, category = category!!, score = score)
                updateData(data)
                Helper.createToast(this, "$questionAnswer = $question similarities $score")
            }
        }
    }

    private fun getTotalScore() {
        binding.btnNext.setOnClickListener {
            hurufViewModel.getTotalScore.observe(this) {
                Helper.createToast(this, it.toString())
            }

        }
    }

    private fun updateData(model: Model) {
        hurufViewModel.updateData(model)
    }


}