package com.example.tp_integrador

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val mensaje = intent.getStringExtra("mensaje")
        val tvBienvenida = findViewById<TextView>(R.id.tvBienvenida)
        tvBienvenida.text = mensaje

        val btnConsulta = findViewById<Button>(R.id.btnConsulta)
        val btnHistorial = findViewById<Button>(R.id.btnHistorial)
        val btnTyC = findViewById<Button>(R.id.btnTyC)
        val btnSalir = findViewById<Button>(R.id.btnSalir)

        btnConsulta.setOnClickListener {
            val intent = Intent(this,ComparatorInputDataActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnHistorial.setOnClickListener {
            val intent = Intent(this,HistoryActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnTyC.setOnClickListener {
            val intent = Intent(this,TermYCond::class.java)
            startActivity(intent)
            finish()
        }

        btnSalir.setOnClickListener {
            finish()
        }
    }
}