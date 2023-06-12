package com.dokari4.sekeca

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dokari4.sekeca.databinding.ActivityMainBinding
import com.dokari4.sekeca.view.huruf.HurufActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //init binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGo.setOnClickListener {
            val intent = Intent(this, HurufActivity::class.java)
            startActivity(intent)
        }
    }
}