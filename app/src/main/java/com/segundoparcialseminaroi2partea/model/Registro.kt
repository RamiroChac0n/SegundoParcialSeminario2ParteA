package com.segundoparcialseminaroi2partea.model

data class Registro(
    val id: Int,
    val numero: Int,
    val titulo: String,
    val subtitulo: String,
    val descripcion: String,     // CIFRADO
    val descripciondos: String,  // LETRA
    val imagen: String?,
    val audio: String?,
    val video: String?
)
