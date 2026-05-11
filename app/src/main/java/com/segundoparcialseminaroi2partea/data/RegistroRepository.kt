package com.segundoparcialseminaroi2partea.data

import com.segundoparcialseminaroi2partea.model.Registro

class RegistroRepository {

    suspend fun obtenerRegistros(): List<Registro> {
        return MockRegistroData.registros
    }

    suspend fun obtenerRegistroPorId(id: Int): Registro? {
        return MockRegistroData.registros.find { it.id == id }
    }
}