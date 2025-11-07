package com.example.grupo06_candidatoinfo.ui.screens.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.grupo06_candidatoinfo.data.remote.RetrofitCliente1
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update // Importante para la función de volver
import kotlinx.coroutines.launch

/**
 * Define los pasos del flujo de registro.
 */
enum class RegistrationStep {
    DNI_INPUT,        // Paso 1: Ingresar DNI y aceptar términos
    PROFILE_COMPLETE, // Paso 2: Completar email y contraseña
    EMAIL_VERIFY      // Paso 3: Verificar código de email
}

/**
 * Data class que mantiene el estado completo del formulario de registro.
 */
data class RegistroState(
    // Paso 1
    val dni: String = "",
    val termsAccepted: Boolean = false,

    // Paso 2
    val nombres: String = "",
    val apellidoPaterno: String = "",
    val apellidoMaterno: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",

    // Paso 3
    val verificationCode: String = "",

    // Estado general
    val step: RegistrationStep = RegistrationStep.DNI_INPUT, // Paso actual
    val isLoading: Boolean = false,
    val error: String? = null
)

class AuthViewModel : ViewModel() {

    private val _registroState = MutableStateFlow(RegistroState())
    val registroState: StateFlow<RegistroState> = _registroState

    // --- Funciones para actualizar el estado (Event Handlers) ---

    fun onDniChange(value: String) {
        // Limitar a 8 dígitos numéricos
        if (value.length <= 8 && value.all { it.isDigit() }) {
            // _registroState.value = _registroState.value.copy(dni = value, error = null)
            // Usamos .update() para ser seguro con la concurrencia
            _registroState.update { it.copy(dni = value, error = null) }
        }
    }

    fun onTermsAcceptedChange(value: Boolean) {
        _registroState.update { it.copy(termsAccepted = value, error = null) }
    }

    fun onEmailChange(value: String) {
        _registroState.update { it.copy(email = value, error = null) }
    }

    fun onPasswordChange(value: String) {
        _registroState.update { it.copy(password = value, error = null) }
    }

    fun onConfirmPasswordChange(value: String) {
        _registroState.update { it.copy(confirmPassword = value, error = null) }
    }

    fun onVerificationCodeChange(value: String) {
        // Limitar a 6 dígitos
        if (value.length <= 6 && value.all { it.isDigit() }) {
            _registroState.update { it.copy(verificationCode = value, error = null) }
        }
    }

    // --- Acciones del ViewModel (Lógica de negocio) ---

    // --- FUNCIÓN AÑADIDA ---
    /**
     * Se llama desde la UI para navegar al paso anterior.
     */
    fun goToPreviousStep() {
        _registroState.update { currentState ->
            val previousStep = when (currentState.step) {
                RegistrationStep.PROFILE_COMPLETE -> RegistrationStep.DNI_INPUT
                RegistrationStep.EMAIL_VERIFY -> RegistrationStep.PROFILE_COMPLETE
                // No se puede volver desde el primer paso
                RegistrationStep.DNI_INPUT -> currentState.step
            }
            // Regresa al paso anterior y limpia cualquier error
            currentState.copy(step = previousStep, error = null)
        }
    }
    // --- FIN DE LA FUNCIÓN AÑADIDA ---

    /**
     * Se llama en el Paso 1.
     * Valida el DNI y los términos, luego busca en la API.
     */
    fun buscarDni() {
        val state = _registroState.value
        val dni = state.dni.trim()

        if (dni.length != 8) {
            _registroState.update { it.copy(error = "El DNI debe tener 8 dígitos") }
            return
        }

        if (!state.termsAccepted) {
            _registroState.update { it.copy(error = "Debes aceptar los Términos y Condiciones") }
            return
        }

        viewModelScope.launch {
            _registroState.update { it.copy(isLoading = true, error = null) }
            try {
                // Llamada a la API (como en tu código original)
                val response = RetrofitCliente1.api.getDniInfo(dni)

                if (response.success) {
                    // Éxito: autocompletar datos y avanzar al siguiente paso
                    _registroState.update {
                        it.copy(
                            nombres = response.data.nombres,
                            apellidoPaterno = response.data.apellido_paterno,
                            apellidoMaterno = response.data.apellido_materno,
                            isLoading = false,
                            step = RegistrationStep.PROFILE_COMPLETE // <-- Avanza al Paso 2
                        )
                    }
                } else {
                    // DNI no encontrado u otro error de la API
                    _registroState.update {
                        it.copy(
                            error = "No se encontró el DNI ingresado",
                            isLoading = false
                        )
                    }
                }
            } catch (e: Exception) {
                // Error de conexión
                _registroState.update {
                    it.copy(
                        error = "Error al conectar con el servidor: ${e.localizedMessage}",
                        isLoading = false
                    )
                }
            }
        }
    }

    /**
     * Se llama en el Paso 2.
     * Valida los campos de perfil (email, contraseñas) y avanza.
     */
    fun submitProfile() {
        val state = _registroState.value

        // TODO: Añadir validación de email (formato)
        if (state.email.isBlank()) {
            _registroState.update { it.copy(error = "El correo es obligatorio") }
            return
        }

        // TODO: Añadir validación de fortaleza de contraseña
        if (state.password.length < 6) {
            _registroState.update { it.copy(error = "La contraseña debe tener al menos 6 caracteres") }
            return
        }

        if (state.password != state.confirmPassword) {
            _registroState.update { it.copy(error = "Las contraseñas no coinciden") }
            return
        }

        // TODO: Aquí llamarías a tu API para CREAR la cuenta
        // y enviar el correo de verificación.

        // Simulación de éxito: avanzar al Paso 3
        _registroState.update {
            it.copy(
                isLoading = false,
                error = null,
                step = RegistrationStep.EMAIL_VERIFY // <-- Avanza al Paso 3
            )
        }
    }

    /**
     * Se llama en el Paso 3.
     * Valida el código de verificación.
     */
    fun submitVerification() {
        val state = _registroState.value

        if (state.verificationCode.length != 6) {
            _registroState.update { it.copy(error = "El código debe tener 6 dígitos") }
            return
        }

        // TODO: Aquí llamarías a tu API para VALIDAR el código
        // y activar la cuenta del usuario.

        // Simulación de éxito:
        // El VM no navega, pero puede poner un estado de "éxito"
        // que el Composable usará para navegar a Home.
        println("Verificación exitosa (simulada)")
        _registroState.update { it.copy(isLoading = false, error = null) }

        // El Composable (RegisterTab) se encargará de navegar a Home
        // al detectar que este submit fue exitoso.
    }
}