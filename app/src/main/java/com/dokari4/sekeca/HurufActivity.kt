package com.dokari4.sekeca

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dokari4.sekeca.databinding.ActivityHurufBinding

class HurufActivity : AppCompatActivity(), QuestionClickHandler {
    private lateinit var binding: ActivityHurufBinding
    private lateinit var hurufViewModel: HurufViewModel

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

    }

    override fun clickedQuestionItem(view: View, model: Model) {
//        Toast.makeText(this, "Question: ${model.question}", Toast.LENGTH_SHORT).show()
        hurufViewModel.displaySpeechRecognizer()
    }

    //SpeechRecognizer
    private val speechToText = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val textFromSpeech = result.data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS).let { it?.get(0) }
            if (textFromSpeech != null) {
                Toast.makeText(this, "$textFromSpeech", Toast.LENGTH_SHORT).show()
            }
        }
    }


}