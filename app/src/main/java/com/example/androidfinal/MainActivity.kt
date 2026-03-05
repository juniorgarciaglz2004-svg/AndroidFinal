package com.example.androidfinal

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<Button>(R.id.buttverLista).setOnClickListener {
            val intent = Intent(this , VerListaActivity::class.java)
            startActivity(intent)

        }

        findViewById<Button>(R.id.buttCrear).setOnClickListener {
            val intent = Intent(this , CrearActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.buttEditar).setOnClickListener {
            val intent = Intent(this , EditarActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.buttEliminar).setOnClickListener {
            val intent = Intent(this , EliminarActivity::class.java)
            startActivity(intent)
        }


    }
}