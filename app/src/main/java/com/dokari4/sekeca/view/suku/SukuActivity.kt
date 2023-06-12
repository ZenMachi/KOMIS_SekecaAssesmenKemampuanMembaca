package com.dokari4.sekeca.view.suku

import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dokari4.sekeca.data.local.Model
import com.dokari4.sekeca.databinding.ActivitySukuBinding
import com.dokari4.sekeca.ui.RecyclerViewAdapter
import com.dokari4.sekeca.ui.ViewModelfactory
import com.dokari4.sekeca.utils.Helper
import com.dokari4.sekeca.utils.QuestionClickHandler
import com.dokari4.sekeca.view.bermakna.BermaknaActivity
import com.dokari4.sekeca.viewmodels.ViewModel

class SukuActivity : AppCompatActivity(), QuestionClickHandler {
    private lateinit var binding: ActivitySukuBinding
    private lateinit var viewmodel: ViewModel
    private var questionAnswer: String? = null
    private var question: String? = null
    private var category: String? = null
    private var id: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Set Binding
        binding = ActivitySukuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //ViewModelss
        val factory = ViewModelfactory.getInstance(application)
        viewmodel = ViewModelProvider(this, factory)[ViewModel::class.java]
        viewmodel.initialTextSpeech(speechToText)

        //Adapter
        val adapter = RecyclerViewAdapter()

        //Fill Data to Adapter
        viewmodel.getSukuKataQuestion.observe(this) {
            adapter.submitList(it)
        }

        //Initialize OnClickListener in Recycler View
        adapter.handler = this

        //Set RecyclerView Properties
        binding.recyclerView.apply {
            this.layoutManager = LinearLayoutManager(this@SukuActivity)
            this.adapter = adapter
        }

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnNext.setOnClickListener {
            val intent = Intent(this, BermaknaActivity::class.java)
            startActivity(intent)
        }
    }

    override fun clickedQuestionItem(view: View, model: Model) {
        viewmodel.displaySpeechRecognizer()
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
            }
        }
    }

    private fun updateData(model: Model) {
        viewmodel.updateData(model)
    }

}