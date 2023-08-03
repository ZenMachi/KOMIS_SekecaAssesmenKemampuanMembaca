package com.dokari4.sekeca.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dokari4.sekeca.data.local.User
import com.dokari4.sekeca.databinding.ItemUserBinding

class UserListAdapter: ListAdapter<User, UserListAdapter.MyHolder>(DiffutilCallBack) {

    object DiffutilCallBack: DiffUtil.ItemCallback<User>(){
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }

    }

    inner class MyHolder(val binding: ItemUserBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            if (user.nama.isNullOrEmpty())
                binding.tvContentName.text = "Kosong"
            else
                binding.tvContentName.text = user.nama

            if (user.kelas.isNullOrEmpty())
                binding.tvContentKelas.text = "Kosong"
            else
                binding.tvContentKelas.text = user.kelas

            if (user.sekolah.isNullOrEmpty())
                binding.tvContentSekolah.text = "Kosong"
            else
                binding.tvContentSekolah.text = user.sekolah

            if (user.provinsi.isNullOrEmpty())
                binding.tvContentProvinsi.text = "Kosong"
            else
                binding.tvContentProvinsi.text = user.provinsi

            if (user.kabupatenAtauKota.isNullOrEmpty())
                binding.tvContentKabKota.text = "Kosong"
            else
                binding.tvContentKabKota.text = user.kabupatenAtauKota

            if (user.scoreTotal == null)
                binding.tvContentNilai.text = "0"
            else
                binding.tvContentNilai.text = user.scoreTotal.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}