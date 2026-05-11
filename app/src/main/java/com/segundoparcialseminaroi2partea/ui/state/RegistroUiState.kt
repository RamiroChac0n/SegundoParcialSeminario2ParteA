package com.segundoparcialseminaroi2partea.ui.state

import com.segundoparcialseminaroi2partea.model.Registro

sealed class RegistroUiState {
    object Loading : RegistroUiState()
    data class Success(val registros: List<Registro>) : RegistroUiState()
    data class Error(val mensaje: String) : RegistroUiState()
}

sealed class RegistroDetalleUiState {
    object Loading : RegistroDetalleUiState()
    data class Success(val registro: Registro) : RegistroDetalleUiState()
    data class Error(val mensaje: String) : RegistroDetalleUiState()
}