package com.dokari4.sekeca.view.result

import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dokari4.sekeca.databinding.ActivityResultBinding
import com.dokari4.sekeca.ui.ViewModelfactory
import com.dokari4.sekeca.viewmodels.ViewModel
import com.robinhood.ticker.TickerUtils
import java.math.BigDecimal
import java.math.RoundingMode

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    private lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //TickerView
        binding.tickerView.setCharacterLists(TickerUtils.provideNumberList())
        binding.tickerView.animationDuration = 1000
        binding.tickerView.gravity = Gravity.CENTER

        //ViewModel
        val factory = ViewModelfactory.getInstance(application)
        viewModel = ViewModelProvider(this, factory)[ViewModel::class.java]

        //Feed Results
        viewModel.getTotalScore.observe(this) {
            if (it != null) {
                val bd = BigDecimal(it)
                val roundedScore = bd.setScale(2, RoundingMode.FLOOR)
                binding.tickerView.setText(roundedScore.toString(), true)
            } else {
                binding.tickerView.setText("000", true)
            }
        }

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnReset.setOnClickListener {
            viewModel.resetScore()
        }
    }
}