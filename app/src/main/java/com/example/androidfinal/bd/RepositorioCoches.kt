package com.example.androidfinal.bd

import com.example.androidfinal.modelos.Coche
import kotlin.random.Random

interface RepositorioCoches {
    fun adiciona(coche: Coche)
    fun actualiza(id: Int, coche: Coche)
    fun elimina(id: Int)
    fun elemento(id: Int): Coche
    fun tamanno(): Int

    fun annadeCoches(cantidad: Int) {
        val marcas = listOf(
            "Toyota", "Honda", "Ford", "Chevrolet", "Volkswagen",
            "BMW", "Mercedes-Benz", "Audi", "Hyundai", "Kia",
            "Nissan", "Mazda", "Subaru", "Lexus", "Tesla"
        )

        val modelosPorMarca = mapOf(
            "Toyota" to listOf("Corolla", "Camry", "RAV4", "Hilux", "Yaris", "Prius"),
            "Honda" to listOf("Civic", "Accord", "CR-V", "HR-V", "Fit", "Pilot"),
            "Ford" to listOf("Fiesta", "Focus", "Mustang", "Explorer", "Escape", "Ranger"),
            "Chevrolet" to listOf("Spark", "Cruze", "Malibu", "Camaro", "Silverado", "Tracker"),
            "Volkswagen" to listOf("Golf", "Jetta", "Passat", "Tiguan", "Polo", "Beetle"),
            "BMW" to listOf("Serie 1", "Serie 3", "Serie 5", "X1", "X3", "X5"),
            "Mercedes-Benz" to listOf("Clase A", "Clase C", "Clase E", "GLA", "GLC", "GLE"),
            "Audi" to listOf("A1", "A3", "A4", "Q3", "Q5", "Q7"),
            "Hyundai" to listOf("i10", "i20", "i30", "Tucson", "Santa Fe", "Kona"),
            "Kia" to listOf("Picanto", "Rio", "Ceed", "Sportage", "Sorento", "Stonic"),
            "Nissan" to listOf("Micra", "Qashqai", "Juke", "X-Trail", "Navara", "GT-R"),
            "Mazda" to listOf("Mazda2", "Mazda3", "Mazda6", "CX-3", "CX-5", "MX-5"),
            "Subaru" to listOf("Impreza", "Legacy", "Outback", "Forester", "XV", "WRX"),
            "Lexus" to listOf("CT", "IS", "ES", "RX", "NX", "LX"),
            "Tesla" to listOf("Model 3", "Model S", "Model X", "Model Y", "Cybertruck", "Roadster")
        )

        val caracteristicasList = listOf(
            "Automático", "Manual", "Híbrido", "Eléctrico", "Diésel", "Gasolina",
            "4x4", "Tracción delantera", "Tracción trasera", "Convertible",
            "5 puertas", "3 puertas", "Familiar", "Deportivo", "SUV",
            "Airbags", "ABS", "Control de estabilidad", "Asistente de carril",
            "Climatizador", "GPS", "Bluetooth", "Cámara trasera", "Sensores de aparcamiento"
        )

        val random = Random(System.currentTimeMillis())

        for (i in 1..cantidad) {
            val marca = marcas.random()
            val modelo = modelosPorMarca[marca]?.random() ?: "Desconocido"
            val anno = random.nextInt(2015, 2025)

            val numCaracteristicas = random.nextInt(2, 5)
            val caracteristicasSeleccionadas = mutableListOf<String>()
            val caracteristicasTemp = caracteristicasList.toMutableList()

            repeat(numCaracteristicas) {
                if (caracteristicasTemp.isNotEmpty()) {
                    val index = random.nextInt(caracteristicasTemp.size)
                    caracteristicasSeleccionadas.add(caracteristicasTemp[index])
                    caracteristicasTemp.removeAt(index)
                }
            }

            val caracteristicas = caracteristicasSeleccionadas.joinToString(", ")

            adiciona(Coche(modelo, marca, anno, caracteristicas))
        }
    }
}