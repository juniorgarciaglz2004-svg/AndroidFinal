package com.example.androidfinal

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class VerListaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_lista)

        // Configurar Toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar_lista)
        setSupportActionBar(toolbar)

        // Configurar título de la toolbar
        supportActionBar?.title = "Lista de Coches"

        // Configurar RecyclerView
        val recycler = findViewById<RecyclerView>(R.id.lista)
        val adaptador = AdaptadorLista(this)
        recycler.adapter = adaptador
        recycler.layoutManager = LinearLayoutManager(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_lista, menu)
        return true // Cambiado a true en lugar de super
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.toolbar_annadir -> {
                val intent = Intent(this, CrearActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}