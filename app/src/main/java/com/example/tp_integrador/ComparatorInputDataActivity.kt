package com.example.tp_integrador

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ComparatorInputDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.comparator_input_data_activity)


        val botonEnviar = findViewById<Button>(R.id.comparar_inversiones)
        val botonHistorial = findViewById<Button>(R.id.navegar_a_historial)

        val editTextMontoInversion1 = findViewById<EditText>(R.id.monto_inversion1)
        val editTextTNAInversion1 = findViewById<EditText>(R.id.tna_inversion1)
        val editTextPlazoInversion1 = findViewById<EditText>(R.id.plazo_inversion1)
        val editTextEntidadInversion1 = findViewById<EditText>(R.id.entidad_inversion1)
        val radioGroupTipoInversion1 = findViewById<RadioGroup>(R.id.rg_tipo_inversion1)

        val editTextMontoInversion2 = findViewById<EditText>(R.id.monto_inversion2)
        val editTextTNAInversion2 = findViewById<EditText>(R.id.tna_inversion2)
        val editTextPlazoInversion2 = findViewById<EditText>(R.id.plazo_inversion2)
        val editTextEntidadInversion2 = findViewById<EditText>(R.id.entidad_inversion2)
        val radioGroupTipoInversion2 = findViewById<RadioGroup>(R.id.rg_tipo_inversion2)

        botonEnviar.setOnClickListener {
            val montoInversion1 = editTextMontoInversion1.text.toString()
            val tnaInversion1 = editTextTNAInversion1.text.toString()
            val plazoInversion1 = editTextPlazoInversion1.text.toString()
            val entidadInversion1 = editTextEntidadInversion1.text.toString()
            val radioButtonGroupTipoInversion1 = radioGroupTipoInversion1.checkedRadioButtonId

            val montoInversion2 = editTextMontoInversion2.text.toString()
            val tnaInversion2 = editTextTNAInversion2.text.toString()
            val plazoInversion2 = editTextPlazoInversion2.text.toString()
            val entidadInversion2 = editTextEntidadInversion2.text.toString()
            val radioButtonGroupTipoInversion2 = radioGroupTipoInversion2.checkedRadioButtonId

            if (inversionHaveEmptyFields(
                    montoInversion1,
                    tnaInversion1,
                    plazoInversion1,
                    entidadInversion1,
                    radioButtonGroupTipoInversion1
                )
            ) {
                Toast.makeText(
                    this,
                    "Faltan completar o seleccionar campos de la Inversion 1",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (inversionHaveEmptyFields(
                    montoInversion2,
                    tnaInversion2,
                    plazoInversion2,
                    entidadInversion2,
                    radioButtonGroupTipoInversion2
                )
            ) {
                Toast.makeText(
                    this,
                    "Faltan completar o seleccionar campos de la Inversion 2",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val montoInversion1Value = montoInversion1.toIntOrNull()
                val tnaInversion1Value = tnaInversion1.toFloatOrNull()
                val plazoInversion1Value = plazoInversion1.toIntOrNull()

                val montoInversion2Value = montoInversion2.toIntOrNull()
                val tnaInversion2Value = tnaInversion2.toFloatOrNull()
                val plazoInversion2Value = plazoInversion2.toIntOrNull()

                if (isValidAmount(montoInversion1Value)) {
                    Toast.makeText(
                        this,
                        "Por favor, ingrese un Monto de la Inversion 1 que sea mayor a 0",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (isValidTNA(tnaInversion1Value)) {
                    Toast.makeText(
                        this,
                        "Por favor, ingrese una Tasa de Interés de la Inversion 1 que sea mayor a 0",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (isValidTerm(plazoInversion1Value)) {
                    Toast.makeText(
                        this,
                        "Por favor, ingrese una Tasa de Interés de la Inversion 1 que sea mayor a 0",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (isValidAmount(montoInversion2Value)) {
                    Toast.makeText(
                        this,
                        "Por favor, ingrese un Monto de la Inversion 2 que sea mayor a 0",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (isValidTNA(tnaInversion2Value)) {
                    Toast.makeText(
                        this,
                        "Por favor, ingrese una Tasa de Interés de la Inversion 2 que sea mayor a 0",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (isValidTerm(plazoInversion2Value)) {
                    Toast.makeText(
                        this,
                        "Por favor, ingrese una Tasa de Interés de la Inversion 2 que sea mayor a 0",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {

                    val radioButtonSeleccionadoTipoInversion1 =
                        findViewById<RadioButton>(radioButtonGroupTipoInversion1)
                    val radioButtonSeleccionadoTipoInversion2 =
                        findViewById<RadioButton>(radioButtonGroupTipoInversion2)

                    val tipoInversion1 = radioButtonSeleccionadoTipoInversion1.text
                    val tipoInversion2 = radioButtonSeleccionadoTipoInversion2.text


                    navigateToComparator(
                        montoInversion1,
                        tnaInversion1,
                        plazoInversion1,
                        entidadInversion1,
                        tipoInversion1.toString(),
                        montoInversion2,
                        tnaInversion2,
                        plazoInversion2,
                        entidadInversion2,
                        tipoInversion2.toString()
                    )
                }
            }
        }
        botonHistorial.setOnClickListener {
            navigateToHistory()
        }
    }

    private fun isValidTNA(tnaInversionValue: Float?) =
        tnaInversionValue == null || tnaInversionValue <= 0

    private fun isValidAmount(montoInversionValue: Int?) =
        montoInversionValue == null || montoInversionValue <= 0

    private fun isValidTerm(plazoInversionValue: Int?) =
        plazoInversionValue == null || plazoInversionValue <= 0

    private fun inversionHaveEmptyFields(
        montoInversion: String,
        tnaInversion: String,
        plazoInversion: String,
        entidadInversion: String,
        tipoInversion1: Int
    ) =
        montoInversion.isEmpty() || tnaInversion.isEmpty() || plazoInversion.isEmpty() || entidadInversion.isEmpty() || tipoInversion1 == -1

    private fun navigateToComparator(
        montoInversion1: String,
        tnaInversion1: String,
        plazoInversion1: String,
        entidadInversion1: String,
        tipoInversion1: String,
        montoInversion2: String,
        tnaInversion2: String,
        plazoInversion2: String,
        entidadInversion2: String,
        tipoInversion2: String
    ) {
        val intent = Intent(this, ComparatorCalcActivity::class.java).apply {
            putExtra("monto_inversion_1", montoInversion1)
            putExtra("tna_inversion_1", tnaInversion1)
            putExtra("plazo_inversion_1", plazoInversion1)
            putExtra("entidad_inversion_1", entidadInversion1)
            putExtra("tipo_inversion_1", tipoInversion1)

            putExtra("monto_inversion_2", montoInversion2)
            putExtra("tna_inversion_2", tnaInversion2)
            putExtra("plazo_inversion_2", plazoInversion2)
            putExtra("entidad_inversion_2", entidadInversion2)
            putExtra("tipo_inversion_2", tipoInversion2)
        }
        startActivity(intent)
        finish()
    }

    private fun navigateToHistory() {
        val intent = Intent(this, HistoryActivity::class.java).apply {}
        startActivity(intent)
        finish()
    }

}