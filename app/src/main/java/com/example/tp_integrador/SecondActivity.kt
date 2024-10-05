package com.example.tp_integrador

import android.content.Context
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)

        val montoInversion1 = intent.getStringExtra("monto_inversion_1")

        val textView = findViewById<TextView>(R.id.test_monto_1).apply {
            text = "Monto Inversion 1: $montoInversion1"
        }

        val historyStorage =
            getSharedPreferences("historyComparationsStorage", Context.MODE_PRIVATE)

        /*
        * Agregamos datos de inversion a sharedPreferences
        * */
        historyStorage.edit().apply {
            putBoolean("test", false)
            apply()
        }
    }
}