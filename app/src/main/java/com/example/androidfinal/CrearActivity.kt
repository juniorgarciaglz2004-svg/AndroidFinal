package com.example.androidfinal

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidfinal.bd.DbRepositorioCoches
import com.example.androidfinal.modelos.Coche

class CrearActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_crear)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
        val bd = DbRepositorioCoches(this)
        val modelo = findViewById<TextView>(R.id.addModelo)
        val marca = findViewById<TextView>(R.id.addMarca)
        val anno = findViewById<TextView>(R.id.addAnno)
        val caracteristicas  = findViewById<TextView>(R.id.addCaracteristicas)

        findViewById<Button>(R.id.btnCancelar).setOnClickListener {
            finish()
        }

        findViewById<Button>(R.id.btnGuardar).setOnClickListener {
            bd.adiciona(Coche(modelo.text.toString(),marca.text.toString(),anno.text.toString().toInt(),caracteristicas.text.toString()))
            finish()
        }

    }
}