package com.example.grupo06_candidatoinfo.ui.screens.auth

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.grupo06_candidatoinfo.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterTab(viewModel: AuthViewModel, navController: NavHostController) {
    val state by viewModel.registroState.collectAsState()

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Indicador de progreso
        StepProgressIndicator(
            currentStep = state.step.ordinal,
            totalSteps = RegistrationStep.entries.size
        )

        Spacer(Modifier.height(24.dp))

        // Contenido del paso (animado)
        AnimatedContent(
            targetState = state.step,
            transitionSpec = {
                slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(300)) + fadeIn() togetherWith
                        slideOutHorizontally(targetOffsetX = { -it }, animationSpec = tween(300)) + fadeOut()
            }, label = "StepAnimation"
        ) { targetStep ->
            Column(modifier = Modifier.fillMaxWidth()) {
                when (targetStep) {
                    RegistrationStep.DNI_INPUT -> RegisterStep1_DniInput(viewModel, state)
                    RegistrationStep.PROFILE_COMPLETE -> RegisterStep2_ProfileComplete(viewModel, state)
                    RegistrationStep.EMAIL_VERIFY -> RegisterStep3_EmailVerify(viewModel, state, navController)
                }
            }
        }
    }
}

// PASO 1: Ingresar DNI
@Composable
fun RegisterStep1_DniInput(viewModel: AuthViewModel, state: RegistroState) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Ingresa tu DNI",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 4.dp),
            color = Color(0xFF2C2C54)
        )
        Text(
            text = "Lo usaremos para precargar tus datos",
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFF8E8E93),
            modifier = Modifier.padding(bottom = 24.dp)
        )

        OutlinedTextField(
            value = state.dni,
            onValueChange = { viewModel.onDniChange(it) },
            label = { Text("DNI", color = Color(0xFF8E8E93)) },
            placeholder = { Text("Ingresa tu DNI", color = Color(0xFFB0B0B0)) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF3F51B5),
                unfocusedBorderColor = Color(0xFFE0E0E0),
                focusedLabelColor = Color(0xFF3F51B5),
                cursorColor = Color(0xFF3F51B5)
            )
        )

        Spacer(Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            Checkbox(
                checked = state.termsAccepted,
                onCheckedChange = { viewModel.onTermsAcceptedChange(it) },
                modifier = Modifier.padding(end = 8.dp),
                colors = CheckboxDefaults.colors(
                    checkedColor = Color(0xFF3F51B5),
                    uncheckedColor = Color(0xFF8E8E93)
                )
            )
            Text(
                text = "He leído y acepto los Términos y Condiciones.",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 12.dp),
                color = Color(0xFF2C2C54)
            )
        }

        if (state.error != null) {
            Spacer(Modifier.height(8.dp))
            Text(
                text = state.error,
                color = Color(0xFFD32F2F),
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = { viewModel.buscarDni() },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            enabled = !state.isLoading,
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF3F51B5),
                disabledContainerColor = Color(0xFF9FA8DA)
            )
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = Color.White,
                    strokeWidth = 2.dp
                )
            } else {
                Text(
                    "REGISTRARME",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White
                )
            }
        }
    }
}

// PASO 2: Completar Perfil
@Composable
fun RegisterStep2_ProfileComplete(viewModel: AuthViewModel, state: RegistroState) {
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Ya te encontramos!",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 4.dp),
            color = Color(0xFF2C2C54)
        )
        Text(
            text = "Estos son tus datos. Completa el resto para crear tu perfil.",
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFF8E8E93),
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Campos pre-cargados (solo lectura)
        ReadOnlyTextField(label = "Nombres", value = state.nombres)
        Spacer(Modifier.height(12.dp))
        ReadOnlyTextField(label = "Apellido Paterno", value = state.apellidoPaterno)
        Spacer(Modifier.height(12.dp))
        ReadOnlyTextField(label = "Apellido Materno", value = state.apellidoMaterno)

        Spacer(Modifier.height(24.dp))

        // Campos nuevos
        OutlinedTextField(
            value = state.email,
            onValueChange = { viewModel.onEmailChange(it) },
            label = { Text("Correo Electrónico", color = Color(0xFF8E8E93)) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true,
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF3F51B5),
                unfocusedBorderColor = Color(0xFFE0E0E0),
                focusedLabelColor = Color(0xFF3F51B5),
                cursorColor = Color(0xFF3F51B5)
            )
        )

        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = state.password,
            onValueChange = { viewModel.onPasswordChange(it) },
            label = { Text("Contraseña", color = Color(0xFF8E8E93)) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val image = if (passwordVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, null, tint = Color(0xFF8E8E93))
                }
            },
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF3F51B5),
                unfocusedBorderColor = Color(0xFFE0E0E0),
                focusedLabelColor = Color(0xFF3F51B5),
                cursorColor = Color(0xFF3F51B5)
            )
        )

        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = state.confirmPassword,
            onValueChange = { viewModel.onConfirmPasswordChange(it) },
            label = { Text("Confirmar contraseña", color = Color(0xFF8E8E93)) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val image = if (confirmPasswordVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility
                IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                    Icon(imageVector = image, null, tint = Color(0xFF8E8E93))
                }
            },
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF3F51B5),
                unfocusedBorderColor = Color(0xFFE0E0E0),
                focusedLabelColor = Color(0xFF3F51B5),
                cursorColor = Color(0xFF3F51B5)
            )
        )

        if (state.error != null) {
            Spacer(Modifier.height(8.dp))
            Text(
                text = state.error,
                color = Color(0xFFD32F2F),
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = { viewModel.submitProfile() },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF3F51B5)
            )
        ) {
            Text(
                "CONFIRMAR",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )
        }
    }
}

// PASO 3: Verificar Correo
@Composable
fun RegisterStep3_EmailVerify(viewModel: AuthViewModel, state: RegistroState, navController: NavHostController) {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
        Text(
            text = "Verifica tu correo",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 4.dp),
            color = Color(0xFF2C2C54)
        )
        Text(
            text = "Te enviamos un código de 6 dígitos",
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFF8E8E93),
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Campo para código de verificación con diseño de 6 cajas
        OtpInputField(
            value = state.verificationCode,
            onValueChange = { viewModel.onVerificationCodeChange(it) }
        )

        if (state.error != null) {
            Spacer(Modifier.height(8.dp))
            Text(
                text = state.error,
                color = Color(0xFFD32F2F),
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = {
                viewModel.submitVerification()
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Auth.route) { inclusive = true }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF3F51B5)
            )
        ) {
            Text(
                "VALIDAR Y FINALIZAR",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )
        }
    }
}

// Componente para entrada de código OTP (6 dígitos)
@Composable
fun OtpInputField(value: String, onValueChange: (String) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        repeat(6) { index ->
            val char = if (index < value.length) value[index].toString() else ""

            Box(
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(horizontal = 4.dp)
                    .border(
                        width = 2.dp,
                        color = if (char.isNotEmpty()) Color(0xFF3F51B5) else Color.LightGray,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .clip(RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = char,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF3F51B5)
                )
            }
        }
    }

    // Campo invisible para capturar entrada del teclado
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .size(0.dp)
            .offset(y = (-1000).dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

// Componentes reusables

@Composable
fun StepProgressIndicator(currentStep: Int, totalSteps: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 0 until totalSteps) {
            val isPast = i < currentStep
            val isCurrent = i == currentStep
            val color = when {
                isCurrent -> Color(0xFF3F51B5)
                isPast -> Color(0xFF3F51B5)
                else -> Color.LightGray
            }
            val size = if (isCurrent) 12.dp else 8.dp

            Box(
                modifier = Modifier
                    .size(size)
                    .background(color, CircleShape)
            )
            if (i < totalSteps - 1) {
                Divider(
                    modifier = Modifier.width(32.dp),
                    color = if (isPast) Color(0xFF3F51B5) else Color.LightGray,
                    thickness = 2.dp
                )
            }
        }
    }
}

@Composable
fun ReadOnlyTextField(label: String, value: String) {
    OutlinedTextField(
        value = value,
        onValueChange = {},
        label = { Text(label, color = Color(0xFF8E8E93)) },
        readOnly = true,
        modifier = Modifier.fillMaxWidth(),
        trailingIcon = {
            Icon(Icons.Filled.Lock, contentDescription = "Campo bloqueado", tint = Color(0xFF8E8E93))
        },
        colors = TextFieldDefaults.colors(
            disabledTextColor = Color(0xFF2C2C54),
            disabledIndicatorColor = Color(0xFFE0E0E0),
            disabledLabelColor = Color(0xFF8E8E93),
            disabledTrailingIconColor = Color(0xFF8E8E93),
            disabledContainerColor = Color(0xFFF5F5F5)
        ),
        enabled = false,
        shape = RoundedCornerShape(8.dp)
    )
}