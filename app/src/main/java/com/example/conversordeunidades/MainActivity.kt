package com.example.conversordeunidades


import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    val unidades = arrayOf("Centímetros", "Metros", "Quilômetros", "Milhas")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val valorInput = findViewById<EditText>(R.id.valorInput)
        val unidadeOrigem = findViewById<Spinner>(R.id.unidadeOrigem)
        val unidadeDestino = findViewById<Spinner>(R.id.unidadeDestino)
        val btnConverter = findViewById<Button>(R.id.btnConverter)
        val resultado = findViewById<TextView>(R.id.resultado)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, unidades)
        unidadeOrigem.adapter = adapter
        unidadeDestino.adapter = adapter

        btnConverter.setOnClickListener {
            val valor = valorInput.text.toString().toDoubleOrNull()
            val origem = unidadeOrigem.selectedItem.toString()
            val destino = unidadeDestino.selectedItem.toString()

            if (valor != null) {
                val resultadoConversao = converterUnidades(valor, origem, destino)
                resultado.text = "Resultado: $resultadoConversao $destino"
            } else {
                Toast.makeText(this, "Digite um valor válido", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun converterUnidades(valor: Double, origem: String, destino: String): Double {
        val metros = when (origem) {
            "Centímetros" -> valor / 100
            "Metros" -> valor
            "Quilômetros" -> valor * 1000
            "Milhas" -> valor * 1609.34
            else -> 0.0
        }

        return when (destino) {
            "Centímetros" -> metros * 100
            "Metros" -> metros
            "Quilômetros" -> metros / 1000
            "Milhas" -> metros / 1609.34
            else -> 0.0
        }
    }
}
