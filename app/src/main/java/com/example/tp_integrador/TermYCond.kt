package com.example.tp_integrador

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TermYCond : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.terminosycond)

        val tvTyC = findViewById<TextView>(R.id.tvTyC)
        val btnAtras = findViewById<Button>(R.id.btAtras)

        val termycondTxt = getString(R.string.terminos_condiciones)
        tvTyC.text = termycondTxt

        btnAtras.setOnClickListener {
            finish()
        }
    }
}