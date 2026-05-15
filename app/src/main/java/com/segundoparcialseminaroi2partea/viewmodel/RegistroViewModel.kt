package com.segundoparcialseminaroi2partea.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.segundoparcialseminaroi2partea.data.RegistroRepository
import com.segundoparcialseminaroi2partea.ui.state.RegistroDetalleUiState
import com.segundoparcialseminaroi2partea.ui.state.RegistroUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegistroViewModel : ViewModel() {

    private val repository = RegistroRepository()

    // Estado del listado
    private val _uiState = MutableStateFlow<RegistroUiState>(RegistroUiState.Loading)
    val uiState: StateFlow<RegistroUiState> = _uiState.asStateFlow()

    // Estado del refresco manual
    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()

    // Estado del detalle
    private val _detalleUiState = MutableStateFlow<RegistroDetalleUiState>(RegistroDetalleUiState.Loading)
    val detalleUiState: StateFlow<RegistroDetalleUiState> = _detalleUiState.asStateFlow()

    init {
        cargarRegistros()
    }

    fun cargarRegistros() {
        viewModelScope.launch {
            _uiState.value = RegistroUiState.Loading
            try {
                val datos = repository.obtenerRegistros()
                _uiState.value = RegistroUiState.Success(datos)
            } catch (e: Exception) {
                _uiState.value = RegistroUiState.Error("No se pudo cargar la información.")
            }
        }
    }

    fun refrescar() {
        viewModelScope.launch {
            _isRefreshing.value = true
            try {
                val datos = repository.obtenerRegistros(forzarRecarga = true)
                _uiState.value = RegistroUiState.Success(datos)
            } catch (e: Exception) {
                _uiState.value = RegistroUiState.Error("Error al actualizar.")
            } finally {
                _isRefreshing.value = false
            }
        }
    }

    fun cargarDetalle(id: Int) {
        viewModelScope.launch {
            _detalleUiState.value = RegistroDetalleUiState.Loading
            try {
                val registro = repository.obtenerRegistroPorId(id)
                if (registro != null) {
                    _detalleUiState.value = RegistroDetalleUiState.Success(registro)
                } else {
                    _detalleUiState.value = RegistroDetalleUiState.Error("Registro no encontrado.")
                }
            } catch (e: Exception) {
                _detalleUiState.value = RegistroDetalleUiState.Error("Error al cargar el detalle.")
            }
        }
    }
}