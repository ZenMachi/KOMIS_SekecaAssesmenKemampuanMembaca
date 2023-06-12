package com.dokari4.sekeca.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dokari4.sekeca.data.local.Model
import com.dokari4.sekeca.databinding.ItemRowBinding
import com.dokari4.sekeca.utils.QuestionClickHandler

class RecyclerViewAdapter(): ListAdapter<Model,RecyclerViewAdapter.MyHolder>(DiffutilCallBack) {

    var handler: QuestionClickHandler? = null

    object DiffutilCallBack: DiffUtil.ItemCallback<Model>(){
        override fun areItemsTheSame(oldItem: Model, newItem: Model): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Model, newItem: Model): Boolean {
            return oldItem == newItem
        }

    }

    inner class MyHolder(val binding: ItemRowBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(model: Model) {
            binding.tvQuestion.text = model.question
            binding.btnMic.setOnClickListener {
                handler?.clickedQuestionItem(it, model)
            }
            binding.tvAnswer.text = model.answer
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

//    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
//        holder.binding.tvQuestion.text = data[position].question
//        holder.binding.btnMic.setOnClickListener {
//            handler?.clickedQuestionItem(it,data[position])
//        }
        val item = getItem(position)
        holder.bind(item)
    }

}