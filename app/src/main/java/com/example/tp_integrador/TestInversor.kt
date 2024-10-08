package com.example.tp_integrador

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class TestInversor : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.testinversor)

        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etApellido = findViewById<EditText>(R.id.etApellido)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val rgPregunta1 = findViewById<RadioGroup>(R.id.rgpregunta1)
        val rgPregunta2 = findViewById<RadioGroup>(R.id.rgpregunta2)
        val rgPregunta3 = findViewById<RadioGroup>(R.id.rgpregunta3)
        val btnContinuar = findViewById<Button>(R.id.btContinuar)
        val chbTyC = findViewById<CheckBox>(R.id.chbTyC)

        val datosInversor = getSharedPreferences("Inversiones", Context.MODE_PRIVATE)
        val yaIngreso = datosInversor.getBoolean("estaIngresado", false)

        chbTyC.isChecked= false

        if (yaIngreso) {
            val mensaje = datosInversor.getString("mensaje", "")
            irAlHome(mensaje!!)
        }

        btnContinuar.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            val apellido = etApellido.text.toString().trim()
            val email = etEmail.text.toString().trim()

            if (nombre.isEmpty() || apellido.isEmpty() || email.isEmpty()) {
                when {
                    nombre.isEmpty() -> Toast.makeText(this, "Debes completar el nombre", Toast.LENGTH_SHORT).show()
                    apellido.isEmpty() -> Toast.makeText(this, "Debes completar el apellido", Toast.LENGTH_SHORT).show()
                    email.isEmpty() -> Toast.makeText(this, "Debes completar el correo electrónico", Toast.LENGTH_SHORT).show()
                }
            } else if (!chbTyC.isChecked) {
                Toast.makeText(this, "Debes aceptar los términos y condiciones para continuar", Toast.LENGTH_SHORT).show()
            } else {

                val respuesta1 = getRespuesta(rgPregunta1.checkedRadioButtonId)
                val respuesta2 = getRespuesta(rgPregunta2.checkedRadioButtonId)
                val respuesta3 = getRespuesta(rgPregunta3.checkedRadioButtonId)

                Log.d("Respuestas", "Respuesta 1: $respuesta1, Respuesta 2: $respuesta2, Respuesta 3: $respuesta3")

                val tipoInversor = determinarTipo(respuesta1, respuesta2, respuesta3)

                val mensaje = "Holaa $nombre $apellido!\n sos un Inversor $tipoInversor"

                datosInversor.edit().putBoolean("estaIngresado", true).apply()
                datosInversor.edit().putString("mensaje", mensaje).apply()

                Log.d("MainActivity", "Mensaje: $mensaje")

                irAlHome(mensaje)
            }
        }

        chbTyC.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                val intent = Intent(this, TermYCond::class.java)
                startActivity(intent)
            }
        }
    }

    private fun getRespuesta(id: Int): String {
        return when (id) {
            R.id.rbp1opcion1, R.id.rbp2opcion1, R.id.rbp3opcion1 -> "A"
            R.id.rbp1opcion2, R.id.rbp2opcion2, R.id.rbp3opcion2 -> "B"
            R.id.rbp1opcion3, R.id.rbp2opcion3, R.id.rbp3opcion3 -> "C"
            R.id.rbp1opcion4, R.id.rbp2opcion4, R.id.rbp3opcion4 -> "D"
            else -> ""
        }
    }

    private fun determinarTipo(respuesta1: String, respuesta2: String, respuesta3: String): String {
        val respuestas = listOf(respuesta1, respuesta2, respuesta3)
        val conteoRespuestas = respuestas.groupingBy { it }.eachCount()

        return when {
            conteoRespuestas.getOrDefault("A", 0) >= 2 -> "Conservador"
            conteoRespuestas.getOrDefault("B", 0) >= 2 -> "Moderado"
            conteoRespuestas.getOrDefault("C", 0) >= 2 -> "Agresivo"
            else -> "Diversificado"
        }
    }

    private fun irAlHome(mensaje: String) {
        val intent = Intent(this, WelcomeActivity::class.java)
        intent.putExtra("mensaje", mensaje)
        startActivity(intent)
        finish()
    }
}