package com.example.androidfinal.modelos

data class Coche(
    var id: Int = 0,
    var modelo: String = "",
    var marca: String = "",
    var anno: Int = 0,
    var caracteristicas: String = ""
) {
    fun esValido(): Boolean {
        return modelo.trim().isNotEmpty() &&
                marca.trim().isNotEmpty() &&
                anno > 0 &&
                caracteristicas.trim().isNotEmpty()

    }
}
