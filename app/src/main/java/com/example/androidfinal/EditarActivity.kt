package com.example.androidfinal

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidfinal.bd.DbRepositorioCoches
import com.example.androidfinal.modelos.Coche

class EditarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_editar)
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

        val id = intent.getIntExtra("indice", 0)
        val coche = bd.elemento(id)
        modelo.text = coche.modelo
        marca.text = coche.marca
        anno.text = coche.anno.toString()
        caracteristicas.text = coche.caracteristicas

        findViewById<Button>(R.id.btnCancelar).setOnClickListener {
            finish()
        }

        findViewById<Button>(R.id.btnGuardar).setOnClickListener {
            val annoValue = anno.text.toString().trim()
            val coche = Coche(
                id = id,
                modelo = modelo.text.toString(),
                marca = marca.text.toString(),
                anno = if (annoValue.isEmpty())  0 else  annoValue.toInt(),
                caracteristicas = caracteristicas.text.toString())

            if (coche.esValido()) {
                bd.actualiza(coche)
                finish()
            } else {
                Toast.makeText(this, "Teclee todos los campos", Toast.LENGTH_LONG).show()
            }
        }
    }
}