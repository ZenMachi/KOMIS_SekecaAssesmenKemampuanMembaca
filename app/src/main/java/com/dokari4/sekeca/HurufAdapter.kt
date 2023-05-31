package com.dokari4.sekeca

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.dokari4.sekeca.databinding.ItemRowBinding

class HurufAdapter(private val context: Context, private val data: ArrayList<Model>): RecyclerView.Adapter<HurufAdapter.MyHolder>() {

    var handler: QuestionClickHandler? = null

    inner class MyHolder(val binding: ItemRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.binding.tvQuestion.text = data[position].question
        holder.binding.btnMic.setOnClickListener {
            handler?.clickedQuestionItem(it,data[position])
        }
    }

}