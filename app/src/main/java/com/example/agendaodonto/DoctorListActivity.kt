package com.example.agendaodonto

import ProfilesAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson
import java.io.InputStreamReader

class DoctorListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_list)

        // Carrega a lista de médicos
        val doctorList = loadDoctorList()

        // Configura o RecyclerView com o ProfilesAdapter
        val recyclerView = findViewById<RecyclerView>(R.id.rv_doctor_list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ProfilesAdapter(doctorList) // Usa o ProfilesAdapter
    }

    // Função para carregar a lista de médicos do arquivo JSON
    private fun loadDoctorList(): List<Doctor> {
        val inputStream = assets.open("doctors.json")
        val reader = InputStreamReader(inputStream)
        val doctorsListType = object : TypeToken<List<Doctor>>() {}.type
        return Gson().fromJson(reader, doctorsListType)
    }
}
