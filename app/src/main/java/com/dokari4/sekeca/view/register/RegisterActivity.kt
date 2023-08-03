package com.dokari4.sekeca.view.register

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dokari4.sekeca.R
import com.dokari4.sekeca.data.local.User
import com.dokari4.sekeca.databinding.ActivityRegisterBinding
import com.dokari4.sekeca.ui.ViewModelfactory
import com.dokari4.sekeca.utils.Helper
import com.dokari4.sekeca.view.huruf.HurufActivity
import com.dokari4.sekeca.viewmodels.ViewModel

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: ViewModel

    private val itemProvinsi: ArrayList<String> = ArrayList()
    private val itemKabKota: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelfactory.getInstance(application)
        viewModel = ViewModelProvider(this, factory)[ViewModel::class.java]

        setAutoCompleteTextViewData()

        binding.btnGo.setOnClickListener {
            insertUser()
            viewModel.resetScoreAndAnswer()
            val intent = Intent(this, HurufActivity::class.java)
            intent.putExtra("name", binding.inputNama.text.toString())
            startActivity(intent)
            finish()
        }

        binding.btnCancel.setOnClickListener {
            finish()
        }

    }

    private fun insertUser() {
        val nama = binding.inputNama.text.toString()
        val kelas = binding.inputKelas.text.toString()
        val sekolah = binding.inputSekolah.text.toString()
        val provinsi = binding.inputProvinsi.text.toString()
        val kabKota = binding.inputKabKota.text.toString()

        viewModel.insertUser(User(
            nama = nama,
            kelas = kelas,
            sekolah = sekolah,
            provinsi = provinsi,
            kabupatenAtauKota = kabKota
        ))
    }

    private fun setAutoCompleteTextViewData() {
        val kelasArray = resources.getStringArray(R.array.kelas)
        val kelasAdapter = ArrayAdapter(this, R.layout.dropdown_item, kelasArray)
        binding.inputKelas.setAdapter(kelasAdapter)

        val data = Helper.loadJSONArray(this, R.raw.address)
        for (i in 0 until data.length()) {
            val item = data.getJSONObject(i)

            val provinsi = item.getString("provinsi")
            val kabKotaArray = item.getJSONArray("kabkota")
            Array(kabKotaArray.length()) {
                val dataKabKota = kabKotaArray.getString(it)
                itemKabKota.add(dataKabKota)
            }
            itemProvinsi.add(provinsi)

        }

        val provinsiAdapter = ArrayAdapter(this, R.layout.dropdown_item, itemProvinsi)
        val kabKotaAdapter = ArrayAdapter(this, R.layout.dropdown_item, itemKabKota)

        binding.inputKabKota.setAdapter(kabKotaAdapter)
        binding.inputProvinsi.setAdapter(provinsiAdapter)
    }

}