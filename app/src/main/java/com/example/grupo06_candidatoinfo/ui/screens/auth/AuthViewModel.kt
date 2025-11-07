package com.example.grupo06_candidatoinfo.ui.screens.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.grupo06_candidatoinfo.data.remote.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class RegistroState(
    val dni: String = "",
    val nombres: String = "",
    val apellidoPaterno: String = "",
    val apellidoMaterno: String = "",
    val email: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)

class AuthViewModel : ViewModel() {

    private val _registroState = MutableStateFlow(RegistroState())
    val registroState: StateFlow<RegistroState> = _registroState

    fun onDniChange(value: String) {
        _registroState.value = _registroState.value.copy(dni = value, error = null)
    }

    fun onEmailChange(value: String) {
        _registroState.value = _registroState.value.copy(email = value, error = null)
    }

    fun buscarDni() {
        val dni = _registroState.value.dni.trim()
        if (dni.length != 8) {
            _registroState.value = _registroState.value.copy(error = "El DNI debe tener 8 dígitos")
            return
        }

        viewModelScope.launch {
            _registroState.value = _registroState.value.copy(isLoading = true, error = null)
            try {
                val response = RetrofitClient.api.getDniInfo(dni)
                if (response.success) {
                    _registroState.value = _registroState.value.copy(
                        nombres = response.data.nombres,
                        apellidoPaterno = response.data.apellido_paterno,
                        apellidoMaterno = response.data.apellido_materno,
                        isLoading = false
                    )
                } else {
                    _registroState.value = _registroState.value.copy(
                        error = "No se encontró el DNI ingresado",
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                _registroState.value = _registroState.value.copy(
                    error = "Error al conectar con el servidor: ${e.localizedMessage}",
                    isLoading = false
                )
            }
        }
    }
}
