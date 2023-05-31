package com.dokari4.sekeca

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.dokari4.sekeca.databinding.ActivityHurufBinding

class HurufActivity : AppCompatActivity(), QuestionClickHandler {
    private lateinit var binding: ActivityHurufBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHurufBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = HurufAdapter(this, questionDatabase.getHuruf() as ArrayList<Model>)
        adapter.handler = this
        binding.recyclerView.apply {
            this.layoutManager = LinearLayoutManager(this@HurufActivity)
            this.adapter = adapter
        }

    }

    override fun clickedQuestionItem(view: View, model: Model) {
        Toast.makeText(this, "Question: ${model.question}", Toast.LENGTH_SHORT).show()
    }


}