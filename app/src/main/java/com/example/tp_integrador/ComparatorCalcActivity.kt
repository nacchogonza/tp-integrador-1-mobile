package com.example.tp_integrador

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ComparatorCalcActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.comparator_calc_activity)
        val botonVolverAInicio = findViewById<Button>(R.id.volver_a_inicio)

        val montoInversion1 = intent.getStringExtra("monto_inversion_1")?.toInt()!!
        val tnaInversion1 = intent.getStringExtra("tna_inversion_1")?.toFloat()!!
        val plazoInversion1 = intent.getStringExtra("plazo_inversion_1")?.toInt()!!
        val entidadInversion1 = intent.getStringExtra("entidad_inversion_1")
        val tipoInversion1 = intent.getStringExtra("tipo_inversion_1").toString()

        val montoInversion2 = intent.getStringExtra("monto_inversion_2")?.toInt()!!
        val tnaInversion2 = intent.getStringExtra("tna_inversion_2")?.toFloat()!!
        val plazoInversion2 = intent.getStringExtra("plazo_inversion_2")?.toInt()!!
        val entidadInversion2 = intent.getStringExtra("entidad_inversion_2")
        val tipoInversion2 = intent.getStringExtra("tipo_inversion_2").toString()


        /* Calculos y llenado de campos Inversion 1 */
        val tnaDiariaInversion1 = calcularTasaDeInteresDiaria(tnaInversion1, plazoInversion1)
        val valorFinalInversion1 =
            calcularValorFinalInversion(montoInversion1, tnaDiariaInversion1, plazoInversion1)
        val rendimientoMonetarioInversion1 =
            calcularRendimientoMonetarioInversion(montoInversion1, valorFinalInversion1)
        val rendimientoPorcentualInversion1 =
            calcularRendimientoPorcentualInversion(montoInversion1, valorFinalInversion1)

        findViewById<TextView>(R.id.entidad_inversion1).apply {
            text = "Entidad de Inversion: $entidadInversion1"
        }

        findViewById<TextView>(R.id.tipo_inversion1).apply {
            text = "Tipo de Inversion: $tipoInversion1"
        }

        findViewById<TextView>(R.id.monto_inicial_inversion1).apply {
            text = "Monto Inicial Inversion: $$montoInversion1"
        }

        findViewById<TextView>(R.id.valor_final_inversion1).apply {
            text = "Valor Final Inversion: $${formatearFloatADosDecimales(valorFinalInversion1)}"
        }

        findViewById<TextView>(R.id.rendimiento_monetario_inversion1).apply {
            text = "Rendimiento Monetario Inversion 1: $${
                formatearFloatADosDecimales(rendimientoMonetarioInversion1)
            }"
        }

        findViewById<TextView>(R.id.rendimiento_porcentual_inversion1).apply {
            text = "Rendimiento Porcentual Inversion 1: ${
                formatearFloatADosDecimales(rendimientoPorcentualInversion1)
            }%"
        }

        /* Calculos y llenado de campos Inversion 2 */
        val tnaDiariaInversion2 = calcularTasaDeInteresDiaria(tnaInversion2, plazoInversion2)
        val valorFinalInversion2 =
            calcularValorFinalInversion(montoInversion2, tnaDiariaInversion2, plazoInversion2)
        val rendimientoMonetarioInversion2 =
            calcularRendimientoMonetarioInversion(montoInversion2, valorFinalInversion2)
        val rendimientoPorcentualInversion2 =
            calcularRendimientoPorcentualInversion(montoInversion2, valorFinalInversion2)

        findViewById<TextView>(R.id.entidad_inversion2).apply {
            text = "Entidad de Inversion: $entidadInversion2"
        }

        findViewById<TextView>(R.id.tipo_inversion2).apply {
            text = "Tipo de Inversion: $tipoInversion2"
        }

        findViewById<TextView>(R.id.monto_inicial_inversion2).apply {
            text = "Monto Inicial Inversion: $$montoInversion2"
        }

        findViewById<TextView>(R.id.valor_final_inversion2).apply {
            text = "Valor Final Inversion 2: $${formatearFloatADosDecimales(valorFinalInversion2)}"
        }

        findViewById<TextView>(R.id.rendimiento_monetario_inversion2).apply {
            text = "Rendimiento Monetario Inversion 2: $${
                formatearFloatADosDecimales(rendimientoMonetarioInversion2)
            }"
        }

        findViewById<TextView>(R.id.rendimiento_porcentual_inversion2).apply {
            text = "Rendimiento Porcentual Inversion 2: ${
                formatearFloatADosDecimales(rendimientoPorcentualInversion2)
            }%"
        }

        /* Constantes para utilizar en Recomendación de Inversión */
        val nombreMejorInversion =
            if (rendimientoPorcentualInversion1 > rendimientoPorcentualInversion2) "Inversion 1" else "Inversion 2"
        val entidadMejorInversion =
            if (rendimientoPorcentualInversion1 > rendimientoPorcentualInversion2) entidadInversion1 else entidadInversion2
        val rendimientoPorcentualMejorInversion =
            if (rendimientoPorcentualInversion1 > rendimientoPorcentualInversion2) rendimientoPorcentualInversion1 else rendimientoPorcentualInversion2

        /* Recomendación de Inversión */
        findViewById<TextView>(R.id.recomendacion_inversion).apply {
            text =
                "Según los cálculos realizados con la información brindada se considera que la mejor opción de inversión es la $nombreMejorInversion perteneciente a la entidad $entidadMejorInversion por dar un rendimiento de ${
                    formatearFloatADosDecimales(rendimientoPorcentualMejorInversion)
                }%"
        }


        /*
        * Agregamos datos de inversion a sharedPreferences
        * */

        val historyStorage =
            getSharedPreferences("historyComparationsStorage", Context.MODE_PRIVATE)

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

        /*
        * Las Listas de datos las armamos utilizando Strings separados por ;
        * que luego splitearemos para interpretar en HistoryActivity
        * */
        if (cantidadComparaciones == 0) {
            entidadesInversiones1 = "$entidadInversion1"
            entidadesInversiones2 = "$entidadInversion2"
            tiposInversiones1 = tipoInversion1
            tiposInversiones2 = tipoInversion2
            montosInicialesInversiones1 = "$montoInversion1"
            montosInicialesInversiones2 = "$montoInversion2"
            montosFinalesInversiones1 = "$valorFinalInversion1"
            montosFinalesInversiones2 = "$valorFinalInversion2"
            rendimientosPorcentualesInversiones1 = rendimientoPorcentualInversion1.toString()
            rendimientosPorcentualesInversiones2 = rendimientoPorcentualInversion2.toString()
            cantidadComparaciones++
        } else if (cantidadComparaciones > 0 && cantidadComparaciones < 5) {
            cantidadComparaciones++
            entidadesInversiones1 = "$entidadInversion1;$entidadesInversiones1"
            entidadesInversiones2 = "$entidadInversion2;$entidadesInversiones2"
            tiposInversiones1 = tipoInversion1 + ";" + tiposInversiones1
            tiposInversiones2 = tipoInversion2 + ";" + tiposInversiones2
            montosInicialesInversiones1 = "$montoInversion1;$montosInicialesInversiones1"
            montosInicialesInversiones2 = "$montoInversion2;$montosInicialesInversiones2"
            montosFinalesInversiones1 = "$valorFinalInversion1;$montosFinalesInversiones1"
            montosFinalesInversiones2 = "$valorFinalInversion2;$montosFinalesInversiones2"
            rendimientosPorcentualesInversiones1 =
                "$rendimientoPorcentualInversion1;$rendimientosPorcentualesInversiones1"
            rendimientosPorcentualesInversiones2 =
                "$rendimientoPorcentualInversion2;$rendimientosPorcentualesInversiones2"
        } else if (cantidadComparaciones == 5) {
            /*
            * Lógica para poder eliminar el ultimo valor de la lista y agregar
            * en la posicion 0 del array
            * */
            val listaEntidadesInversiones1: MutableList<String> =
                entidadesInversiones1!!.split(";").toMutableList()
            val listaEntidadesInversiones2: MutableList<String> =
                entidadesInversiones2!!.split(";").toMutableList()
            val listaTiposInversion1: MutableList<String> =
                tiposInversiones1!!.split(";").toMutableList()
            val listaTiposInversion2: MutableList<String> =
                tiposInversiones2!!.split(";").toMutableList()
            val listaMontosInicialesInversiones1: MutableList<String> =
                montosInicialesInversiones1!!.split(";").toMutableList()
            val listaMontosInicialesInversiones2: MutableList<String> =
                montosInicialesInversiones2!!.split(";").toMutableList()
            val listaMontosFinalesInversiones1: MutableList<String> =
                montosFinalesInversiones1!!.split(";").toMutableList()
            val listaMontosFinalesInversiones2: MutableList<String> =
                montosFinalesInversiones2!!.split(";").toMutableList()
            val listaRendimientosPorcentualesInversiones1: MutableList<String> =
                rendimientosPorcentualesInversiones1!!.split(";").toMutableList()
            val listaRendimientosPorcentualesInversiones2: MutableList<String> =
                rendimientosPorcentualesInversiones2!!.split(";").toMutableList()

            listaEntidadesInversiones1.removeLast()
            listaEntidadesInversiones2.removeLast()
            listaTiposInversion1.removeLast()
            listaTiposInversion2.removeLast()
            listaMontosInicialesInversiones1.removeLast()
            listaMontosInicialesInversiones2.removeLast()
            listaMontosFinalesInversiones1.removeLast()
            listaMontosFinalesInversiones2.removeLast()
            listaRendimientosPorcentualesInversiones1.removeLast()
            listaRendimientosPorcentualesInversiones2.removeLast()

            listaEntidadesInversiones1.add(0, entidadInversion1!!)
            listaEntidadesInversiones2.add(0, entidadInversion2!!)
            listaTiposInversion1.add(0, tipoInversion1)
            listaTiposInversion2.add(0, tipoInversion2)
            listaMontosInicialesInversiones1.add(0, montoInversion1.toString())
            listaMontosInicialesInversiones2.add(0, montoInversion2.toString())
            listaMontosFinalesInversiones1.add(0, valorFinalInversion1.toString())
            listaMontosFinalesInversiones2.add(0, valorFinalInversion2.toString())
            listaRendimientosPorcentualesInversiones1.add(
                0,
                rendimientoPorcentualInversion1.toString()
            )
            listaRendimientosPorcentualesInversiones2.add(
                0,
                rendimientoPorcentualInversion2.toString()
            )


            entidadesInversiones1 = ""
            entidadesInversiones2 = ""
            tiposInversiones1 = ""
            tiposInversiones2 = ""
            montosInicialesInversiones1 = ""
            montosInicialesInversiones2 = ""
            montosFinalesInversiones1 = ""
            montosFinalesInversiones2 = ""
            rendimientosPorcentualesInversiones1 = ""
            rendimientosPorcentualesInversiones2 = ""
            for (i in 0 until cantidadComparaciones) {
                if (i < cantidadComparaciones - 1) {
                    entidadesInversiones1 =
                        entidadesInversiones1 + listaEntidadesInversiones1[i] + ";"
                    entidadesInversiones2 =
                        entidadesInversiones2 + listaEntidadesInversiones2[i] + ";"
                    tiposInversiones1 = tiposInversiones1 + listaTiposInversion1[i] + ";"
                    tiposInversiones2 = tiposInversiones2 + listaTiposInversion2[i] + ";"
                    montosInicialesInversiones1 =
                        montosInicialesInversiones1 + listaMontosInicialesInversiones1[i] + ";"
                    montosInicialesInversiones2 =
                        montosInicialesInversiones2 + listaMontosInicialesInversiones2[i] + ";"
                    montosFinalesInversiones1 =
                        montosFinalesInversiones1 + listaMontosFinalesInversiones1[i] + ";"
                    montosFinalesInversiones2 =
                        montosFinalesInversiones2 + listaMontosFinalesInversiones2[i] + ";"
                    rendimientosPorcentualesInversiones1 =
                        rendimientosPorcentualesInversiones1 + listaRendimientosPorcentualesInversiones1[i] + ";"
                    rendimientosPorcentualesInversiones2 =
                        rendimientosPorcentualesInversiones2 + listaRendimientosPorcentualesInversiones2[i] + ";"

                } else {
                    entidadesInversiones1 += listaEntidadesInversiones1[i]
                    entidadesInversiones2 += listaEntidadesInversiones2[i]

                    tiposInversiones1 += listaTiposInversion1[i]
                    tiposInversiones2 += listaTiposInversion2[i]

                    montosInicialesInversiones1 += listaMontosInicialesInversiones1[i]
                    montosInicialesInversiones2 += listaMontosInicialesInversiones2[i]

                    montosFinalesInversiones1 += listaMontosFinalesInversiones1[i]
                    montosFinalesInversiones2 += listaMontosFinalesInversiones2[i]

                    rendimientosPorcentualesInversiones1 += listaRendimientosPorcentualesInversiones1[i]
                    rendimientosPorcentualesInversiones2 += listaRendimientosPorcentualesInversiones2[i]
                }

            }
        }

        /*
        * Agregamos datos de comparacion a sharedPreferences
        * */
        historyStorage.edit().apply {
            putInt("cantidadComparaciones", cantidadComparaciones)
            putString("entidadesInversiones1", entidadesInversiones1)
            putString("entidadesInversiones2", entidadesInversiones2)
            putString("tiposInversiones1", tiposInversiones1)
            putString("tiposInversiones2", tiposInversiones2)
            putString("montosInicialesInversiones1", montosInicialesInversiones1)
            putString("montosInicialesInversiones2", montosInicialesInversiones2)
            putString("montosFinalesInversiones1", montosFinalesInversiones1)
            putString("montosFinalesInversiones2", montosFinalesInversiones2)
            putString("rendimientosPorcentualesInversiones1", rendimientosPorcentualesInversiones1)
            putString("rendimientosPorcentualesInversiones2", rendimientosPorcentualesInversiones2)
            apply()
        }

        botonVolverAInicio.setOnClickListener {
            navigateToHome()
        }
    }


    /*
    * Metodos utilizados en el Activity para los calculos y formateo
    * */
    private fun formatearFloatADosDecimales(valorAFormatear: Float): String {
        return String.format("%.2f", valorAFormatear)
    }

    private fun calcularTasaDeInteresDiaria(tna: Float, plazo: Int): Float {
        return tna / 360 / 100
    }

    private fun calcularValorFinalInversion(
        valorInicial: Int,
        tnaDiaria: Float,
        plazo: Int
    ): Float {
        return valorInicial + (valorInicial * tnaDiaria * plazo)
    }

    private fun calcularRendimientoPorcentualInversion(
        valorInicial: Int,
        valorFinal: Float
    ): Float {
        return ((valorFinal - valorInicial) / valorInicial) * 100
    }

    private fun calcularRendimientoMonetarioInversion(valorInicial: Int, valorFinal: Float): Float {
        return valorFinal - valorInicial
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