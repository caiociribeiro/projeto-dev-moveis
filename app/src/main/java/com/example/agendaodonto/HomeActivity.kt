package com.example.agendaodonto

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class HomeActivity : CommonInterfaceActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutInflater.inflate(R.layout.activity_home, findViewById(R.id.content_frame))

        val pageName = findViewById<TextView>(R.id.tv_page_name)
        pageName.text = getString(R.string.home)

        val agendarConsulta: Button = findViewById(R.id.btn_home_agendar)
        agendarConsulta.setOnClickListener {
            val intent = Intent(this, AgendarListaDentistaActivity::class.java)
            startActivity(intent)
        }

        val historicoConsulta: Button = findViewById(R.id.btn_home_historico)
        historicoConsulta.setOnClickListener {
            val intent = Intent(this, HistoricoConsultasActivity::class.java)
            startActivity(intent)
        }

    }
}