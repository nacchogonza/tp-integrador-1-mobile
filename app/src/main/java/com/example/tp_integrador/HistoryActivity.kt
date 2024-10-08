package com.example.tp_integrador

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.divider.MaterialDivider

class HistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.history_activity)

        val historyStorage =
            getSharedPreferences("historyComparationsStorage", Context.MODE_PRIVATE)

        val botonVolverAInicio = findViewById<Button>(R.id.volver_a_inicio)

        var cantidadComparaciones = historyStorage.getInt("cantidadComparaciones", 0)
        var entidadesInversiones1 = historyStorage.getString("entidadesInversiones1", "")
        var entidadesInversiones2 = historyStorage.getString("entidadesInversiones2", "")
        var tiposInversiones1 = historyStorage.getString("tiposInversiones1", "")
        var tiposInversiones2 = historyStorage.getString("tiposInversiones2", "")
        var montosInicialesInversiones1 = historyStorage.getString("montosInicialesInversiones1", "")
        var montosInicialesInversiones2 = historyStorage.getString("montosInicialesInversiones2", "")
        var montosFinalesInversiones1 = historyStorage.getString("montosFinalesInversiones1", "")
        var montosFinalesInversiones2 = historyStorage.getString("montosFinalesInversiones2", "")
        var rendimientosPorcentualesInversiones1 =
            historyStorage.getString("rendimientosPorcentualesInversiones1", "")
        var rendimientosPorcentualesInversiones2 =
            historyStorage.getString("rendimientosPorcentualesInversiones2", "")

        val listaEntidadesInversiones1: MutableList<String> =
            entidadesInversiones1!!.split(";").toMutableList()
        val listaEntidadesInversiones2: MutableList<String> =
            entidadesInversiones2!!.split(";").toMutableList()
        val listaTiposInversion1: MutableList<String> =
            tiposInversiones1!!.split(";").toMutableList()
        val listaTiposInversion2: MutableList<String> =
            tiposInversiones2!!.split(";").toMutableList()
        val listaMontosInicialesInversion1: MutableList<String> =
            montosInicialesInversiones1!!.split(";").toMutableList()
        val listaMontosInicialesInversion2: MutableList<String> =
            montosInicialesInversiones2!!.split(";").toMutableList()
        val listaMontosFinalesInversion1: MutableList<String> =
            montosFinalesInversiones1!!.split(";").toMutableList()
        val listaMontosFinalesInversion2: MutableList<String> =
            montosFinalesInversiones2!!.split(";").toMutableList()
        val listaRendimientosPorcentualesInversiones1: MutableList<String> =
            rendimientosPorcentualesInversiones1!!.split(";").toMutableList()
        val listaRendimientosPorcentualesInversiones2: MutableList<String> =
            rendimientosPorcentualesInversiones2!!.split(";").toMutableList()

        val linLayout = findViewById<LinearLayout>(R.id.comparaciones)

        for (i in 0 until cantidadComparaciones) {
            val textViewTituloInversion1 = TextView(this)
            val textViewEntidadInversion1 = TextView(this)
            val textViewTipoInversion1 = TextView(this)
            val textViewMontoInicialInversion1 = TextView(this)
            val textViewMontoFinalInversion1 = TextView(this)
            val textViewRendimientoInversion1 = TextView(this)
            val textViewTituloInversion2 = TextView(this)
            val textViewEntidadInversion2 = TextView(this)
            val textViewTipoInversion2 = TextView(this)
            val textViewMontoInicialInversion2 = TextView(this)
            val textViewMontoFinalInversion2 = TextView(this)
            val textViewRendimientoInversion2 = TextView(this)

            val textEspacioSuperior = TextView(this)
            val textEspacioIntermedio = TextView(this)
            val textEspacioInferior = TextView(this)
            val divider = MaterialDivider(this)

            textViewTituloInversion1.setText("INVERSION 1")
            textViewEntidadInversion1.setText("Entidad: ${listaEntidadesInversiones1[i]}")
            textViewTipoInversion1.setText("Tipo: ${listaTiposInversion1[i]}")

            val montoInicialInversion1Int = listaMontosInicialesInversion1[i].toInt()
            textViewMontoInicialInversion1.setText("Monto Inicial: $${montoInicialInversion1Int}")

            val montoFinalInversion1Float = listaMontosFinalesInversion1[i].toFloat()
            textViewMontoFinalInversion1.setText("Monto Final: ${formatearFloatADosDecimales(montoFinalInversion1Float)}")

            val rendimientoInversion1Float = listaRendimientosPorcentualesInversiones1[i].toFloat()
            textViewRendimientoInversion1.setText("Rendimiento: ${formatearFloatADosDecimales(rendimientoInversion1Float)}%")

            textViewTituloInversion2.setText("INVERSION 2")
            textViewEntidadInversion2.setText("Entidad: ${listaEntidadesInversiones2[i]}")
            textViewTipoInversion2.setText("Tipo: ${listaTiposInversion2[i]}")

            val montoInicialInversion2Int = listaMontosInicialesInversion2[i].toInt()
            textViewMontoInicialInversion2.setText("Monto Inicial: $${montoInicialInversion2Int}")

            val montoFinalInversion2Float = listaMontosFinalesInversion2[i].toFloat()
            textViewMontoFinalInversion2.setText("Monto Final: ${formatearFloatADosDecimales(montoFinalInversion2Float)}")

            val rendimientoInversion2Float = listaRendimientosPorcentualesInversiones2[i].toFloat()
            textViewRendimientoInversion2.setText("Rendimiento: ${formatearFloatADosDecimales(rendimientoInversion2Float)}%")

            linLayout.addView(textEspacioSuperior)
            linLayout.addView(textViewTituloInversion1)
            linLayout.addView(textViewEntidadInversion1)
            linLayout.addView(textViewTipoInversion1)
            linLayout.addView(textViewMontoInicialInversion1)
            linLayout.addView(textViewMontoFinalInversion1)
            linLayout.addView(textViewRendimientoInversion1)
            linLayout.addView(textEspacioIntermedio)
            linLayout.addView(textViewTituloInversion2)
            linLayout.addView(textViewEntidadInversion2)
            linLayout.addView(textViewTipoInversion2)
            linLayout.addView(textViewMontoInicialInversion2)
            linLayout.addView(textViewMontoFinalInversion2)
            linLayout.addView(textViewRendimientoInversion2)
            linLayout.addView(textEspacioInferior)

            linLayout.addView(divider)

            botonVolverAInicio.setOnClickListener {
                navigateToHome()
            }
        }
    }

    private fun formatearFloatADosDecimales(valorAFormatear: Float): String {
        return String.format("%.2f", valorAFormatear)
    }

    private fun navigateToHome() {
        val intent = Intent(this, WelcomeActivity::class.java).apply {}
        val datosInversor = getSharedPreferences("Inversiones", Context.MODE_PRIVATE)
        val mensaje = datosInversor.getString("mensaje", "")
        intent.putExtra("mensaje", mensaje)
        startActivity(intent)
        finish()
    }
}