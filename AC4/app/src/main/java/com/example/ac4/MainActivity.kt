package com.example.ac4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // configura função para calcular e exibir média ao clicar no botão
        findViewById<Button>(R.id.botao_calcular).setOnClickListener { exibeMedia() }
    }

    private fun exibeMedia() {
        // limpa ultima nota calculada da tela
        findViewById<TextView>(R.id.nota_final).text = ""

        val nota1 = findViewById<TextView>(R.id.primeira_nota).text.toString().toDoubleOrNull()
        val nota2 = findViewById<TextView>(R.id.segunda_nota).text.toString().toDoubleOrNull()
        val nota3 = findViewById<TextView>(R.id.terceira_nota).text.toString().toDoubleOrNull()
        val nota4 = findViewById<TextView>(R.id.quarta_nota).text.toString().toDoubleOrNull()

        val notas = listOf(nota1, nota2, nota3, nota4)

        if (notas.any { it == null }) {
            Toast.makeText(
                this,
                "Por favor, preencha todas as notas",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        val media = calcularMedia(notas as List<Double>)

        val resultText = when {
            media >= 6.0 -> "Aprovado"
            media >= 3.0 && media <= 5.9 -> "Exame"
            else -> "Reprovado"
        }

        findViewById<TextView>(R.id.nota_final).text = resultText
    }

    private fun calcularMedia(notas: List<Double>): Double {
        return notas.reduce { media, nota -> media + nota } / notas.size
    }
}