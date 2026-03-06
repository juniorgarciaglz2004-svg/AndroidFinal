package com.example.androidfinal

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class VerListaActivity : AppCompatActivity() {
    lateinit var adaptador: AdaptadorLista

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ver_lista)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Configurar Toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar_lista)
        setSupportActionBar(toolbar)

        // Configurar título de la toolbar
        supportActionBar?.title = "Lista de Coches"

        //Crear el adaptador
        adaptador = AdaptadorLista(this, { coche ->
            val criterio = "${coche.modelo} ${coche.marca}"
            val url = "https://www.google.com/search?q=${Uri.encode(criterio)}".toUri()
            val intent = Intent(Intent.ACTION_VIEW, url)
            startActivity(intent)
        })

        //Configurar el recycler
        val recycler = findViewById<RecyclerView>(R.id.lista)
        recycler.adapter = adaptador
        recycler.setLayoutManager(LinearLayoutManager(this))

        val decoration = DividerItemDecoration(recycler.context, DividerItemDecoration.VERTICAL)
        val drawable = ContextCompat.getDrawable(this, R.drawable.custom_divider)

        drawable?.let {
            decoration.setDrawable(it)
        }
        recycler.addItemDecoration(decoration)
    }

    override fun onResume() {
        super.onResume()
        adaptador.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_lista, menu)
        return true
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