package com.dokari4.sekeca.view.result

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dokari4.sekeca.MainActivity
import com.dokari4.sekeca.view.userlist.UserListActivity
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

        val intentName = intent.getStringExtra("name").toString()

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
                viewModel.updateUserScore(nama = intentName, score = roundedScore.toDouble())
            } else {
                binding.tickerView.setText("000", true)
            }
        }

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnReset.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }

        binding.btnUserList.setOnClickListener {
            val intent = Intent(this, UserListActivity::class.java)
            startActivity(intent)
        }
    }
}