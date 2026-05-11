package com.segundoparcialseminaroi2partea.data

import com.segundoparcialseminaroi2partea.model.Registro

object MockRegistroData {
    val registros = listOf(
        Registro(
            id = 1, numero = 1,
            titulo = "SANTO, SANTO, SANTO",
            subtitulo = "Tonalidad: Re Mayor",
            descripcion = "D  G  A  D\nSanto, santo, santo...\nG  D  A  D\nSeñor omnipotente",
            descripciondos = "Santo, santo, santo, Señor omnipotente,\nEterno, poderoso, Dios de Israel.",
            imagen = null,
            audio = null,
            video = "https://www.youtube.com/watch?v=dQw4w9WgXcQ"
        ),
        Registro(
            id = 2, numero = 2,
            titulo = "GRANDE ES TU FIDELIDAD",
            subtitulo = "Tonalidad: Do Mayor",
            descripcion = "C  F  G  C\nGrande es tu fidelidad...\nF  C  G  C\nDios eterno",
            descripciondos = "Grande es tu fidelidad, oh Dios mi Padre,\nNo hay sombra de variación en ti.",
            imagen = null,
            audio = null,
            video = "https://www.youtube.com/watch?v=dQw4w9WgXcQ"
        ),
        Registro(
            id = 3, numero = 3,
            titulo = "CUÁN GRANDE ES ÉL",
            subtitulo = "Tonalidad: Sol Mayor",
            descripcion = "G  C  G  D\nCuán grande es él...\nG  C  D  G\nMi Salvador",
            descripciondos = "Señor mi Dios, al contemplar los cielos,\nEl firmamento y las estrellas mil.",
            imagen = null,
            audio = null,
            video = null
        ),
        Registro(
            id = 4, numero = 4,
            titulo = "SUBLIME GRACIA",
            subtitulo = "Tonalidad: Fa Mayor",
            descripcion = "F  Bb  F  C\nSublime gracia...\nF  Bb  C  F\nQué dulce el son",
            descripciondos = "Sublime gracia del Señor,\nQue a un pecador salvó.",
            imagen = null,
            audio = "https://example.com/audio/sublime-gracia.mp3",
            video = null
        ),
        Registro(
            id = 5, numero = 5,
            titulo = "CRISTO ME AMA",
            subtitulo = "Tonalidad: Re Mayor",
            descripcion = "D  G  D  A\nCristo me ama...\nD  G  A  D\nBien lo sé",
            descripciondos = "Cristo me ama, bien lo sé,\nSu palabra me hace ver.",
            imagen = null,
            audio = null,
            video = "https://www.youtube.com/watch?v=dQw4w9WgXcQ"
        )
    )
}