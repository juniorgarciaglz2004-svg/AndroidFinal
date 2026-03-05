package com.example.androidfinal

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidfinal.bd.DbRepositorioCoches
import com.example.androidfinal.bd.RepositorioCoches

class MainActivity : AppCompatActivity() {
    lateinit var repositorio : RepositorioCoches

    override fun onCreate(savedInstanceState: Bundle?) {
        repositorio = DbRepositorioCoches(this)

        if (repositorio.tamanno() == 0) {
            repositorio.annadeCoches(20)
        }

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
            dialogoEditar()
        }

        findViewById<Button>(R.id.buttEliminar).setOnClickListener {
            dialogoEliminar()
        }


    }
    fun dialogoEditar() {
        val entrada = EditText(this)
        entrada.inputType = InputType.TYPE_CLASS_TEXT
        AlertDialog.Builder(this)
            .setTitle("Editar coche")
            .setMessage("Teclee ID")
            .setView(entrada)
            .setPositiveButton("Editar") { dialog, which ->
                val id = entrada.text.toString().toInt()

                if (repositorio.existe(id)) {
                    val intent = Intent(this, EditarActivity::class.java)
                    intent.putExtra("indice", id)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "El id no existe", Toast.LENGTH_LONG).show()
                }

            }
            .setNegativeButton("Cancelar", null)
            .show()
    }


    fun dialogoEliminar() {
        val entrada = EditText(this)
        entrada.inputType = InputType.TYPE_CLASS_TEXT
        AlertDialog.Builder(this)
            .setTitle("Eliminar Coche")
            .setMessage("Teclee ID")
            .setView(entrada)
            .setPositiveButton("Eliminar") { dialog, which ->

                if (repositorio.elimina(entrada.text.toString().toInt())) {
                    Toast.makeText(this, "Eliminado", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "No se ha encontrado", Toast.LENGTH_LONG).show()
                }

            }

            .setNegativeButton("Cancelar", null)
            .show()
    }
}