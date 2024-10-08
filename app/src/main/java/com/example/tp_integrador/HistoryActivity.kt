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
        val listaRendimientosPorcentualesInversiones1: MutableList<String> =
            rendimientosPorcentualesInversiones1!!.split(";").toMutableList()
        val listaRendimientosPorcentualesInversiones2: MutableList<String> =
            rendimientosPorcentualesInversiones2!!.split(";").toMutableList()

        val linLayout = findViewById<LinearLayout>(R.id.comparaciones)

        for (i in 0 until cantidadComparaciones) {
            val textViewTituloInversion1 = TextView(this)
            val textViewEntidadInversion1 = TextView(this)
            val textViewTipoInversion1 = TextView(this)
            val textViewRendimientoInversion1 = TextView(this)
            val textViewTituloInversion2 = TextView(this)
            val textViewEntidadInversion2 = TextView(this)
            val textViewTipoInversion2 = TextView(this)
            val textViewRendimientoInversion2 = TextView(this)

            val textEspacioSuperior = TextView(this)
            val textEspacioIntermedio = TextView(this)
            val textEspacioInferior = TextView(this)
            val divider = MaterialDivider(this)

            textViewTituloInversion1.setText("INVERSION 1")
            textViewEntidadInversion1.setText("Entidad: ${listaEntidadesInversiones1[i]}")
            textViewTipoInversion1.setText("Tipo: ${listaTiposInversion1[i]}")

            val rendimientoInversion1Float = listaRendimientosPorcentualesInversiones1[i].toFloat()
            textViewRendimientoInversion1.setText("Rendimiento: ${formatearFloatADosDecimales(rendimientoInversion1Float)}%")

            textViewTituloInversion2.setText("INVERSION 2")
            textViewEntidadInversion2.setText("Entidad: ${listaEntidadesInversiones2[i]}")
            textViewTipoInversion2.setText("Tipo: ${listaTiposInversion2[i]}")

            val rendimientoInversion2Float = listaRendimientosPorcentualesInversiones2[i].toFloat()
            textViewRendimientoInversion2.setText("Rendimiento: ${formatearFloatADosDecimales(rendimientoInversion2Float)}%")

            linLayout.addView(textEspacioSuperior)
            linLayout.addView(textViewTituloInversion1)
            linLayout.addView(textViewEntidadInversion1)
            linLayout.addView(textViewTipoInversion1)
            linLayout.addView(textViewRendimientoInversion1)
            linLayout.addView(textEspacioIntermedio)
            linLayout.addView(textViewTituloInversion2)
            linLayout.addView(textViewEntidadInversion2)
            linLayout.addView(textViewTipoInversion2)
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
        val intent = Intent(this, ComparatorInputDataActivity::class.java).apply {}
        startActivity(intent)
        finish()
    }
}