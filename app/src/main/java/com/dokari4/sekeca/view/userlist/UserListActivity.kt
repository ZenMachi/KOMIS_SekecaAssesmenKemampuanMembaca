package com.dokari4.sekeca.view.userlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dokari4.sekeca.databinding.ActivityUserListBinding
import com.dokari4.sekeca.ui.UserListAdapter
import com.dokari4.sekeca.ui.ViewModelfactory
import com.dokari4.sekeca.viewmodels.ViewModel

class UserListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserListBinding
    private lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelfactory.getInstance(application)
        viewModel = ViewModelProvider(this, factory)[ViewModel::class.java]

        val adapter = UserListAdapter()

        viewModel.getUser.observe(this) {
            adapter.submitList(it)
        }

        binding.recyclerView.apply {
            this.layoutManager = LinearLayoutManager(this@UserListActivity)
            this.adapter = adapter
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}