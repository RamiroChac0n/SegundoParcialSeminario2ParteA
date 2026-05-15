package com.segundoparcialseminaroi2partea.data

import com.segundoparcialseminaroi2partea.model.Registro

class RegistroRepository {

    // Caché en memoria
    private var cache: List<Registro>? = null

    suspend fun obtenerRegistros(forzarRecarga: Boolean = false): List<Registro> {
        // Si hay caché y no se fuerza recarga, devolver caché directamente
        kotlinx.coroutines.delay(4000)
        //return throw Exception("Error 500: Servid")
        if (!forzarRecarga && cache != null) {
            return cache!!
        }

        val datos = MockRegistroData.registros

        // Guardar en caché
        cache = datos
        return datos
    }

    suspend fun obtenerRegistroPorId(id: Int): Registro? {
        // Si hay caché, buscar ahí sin ir a la red
        cache?.let { lista ->
            return lista.find { it.id == id }
        }

        return MockRegistroData.registros.find { it.id == id }
    }

    fun limpiarCache() {
        cache = null
    }
}