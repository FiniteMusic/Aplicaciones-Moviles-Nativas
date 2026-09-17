package com.marcudlcv.dolist.ui.screens.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marcudlcv.dolist.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface LoginState {

    data object Idle : LoginState

    data object Loading : LoginState

    data object Success : LoginState

    data class Error(
        val message: String
    ) : LoginState
}

data class UserState(
    val isLoading: Boolean = false,
    val user: com.marcudlcv.dolist.data.remote.dto.auth.UserDto? = null,
    val error: String? = null
)

class AuthViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _loginState = MutableStateFlow<LoginState>(
        LoginState.Idle
    )

    val loginState: StateFlow<LoginState> =
        _loginState.asStateFlow()

    private val _userState = MutableStateFlow(UserState())
    val userState: StateFlow<UserState> = _userState.asStateFlow()

    fun login(
        email: String,
        password: String
    ) {
        if (email.isBlank() || password.isBlank()) {
            _loginState.value = LoginState.Error(
                "Completa todos los campos"
            )
            return
        }

        viewModelScope.launch {

            _loginState.value = LoginState.Loading

            val result = authRepository.login(
                email = email,
                password = password
            )

            result
                .onSuccess {
                    _loginState.value = LoginState.Success
                }
                .onFailure { error ->
                    _loginState.value = LoginState.Error(
                        error.message
                            ?: "No se pudo iniciar sesión"
                    )
                }
        }
    }
    fun register(name: String, email: String, password: String) {

        android.util.Log.d(
            "DoListRegister",
            "register() ejecutado: name=$name, email=$email"
        )

        if (name.isBlank() || email.isBlank() || password.isBlank()) {
            android.util.Log.d(
                "DoListRegister",
                "Registro detenido: hay campos vacíos"
            )

            _loginState.value = LoginState.Error("Completa todos los campos")
            return
        }

        viewModelScope.launch {

            android.util.Log.d(
                "DoListRegister",
                "Iniciando petición de registro..."
            )

            _loginState.value = LoginState.Loading

            val registerResult = authRepository.register(
                name = name,
                email = email,
                password = password
            )

            registerResult
                .onSuccess {

                    android.util.Log.d(
                        "DoListRegister",
                        "Registro exitoso. Intentando login automático..."
                    )

                    val loginResult = authRepository.login(
                        email = email,
                        password = password
                    )

                    loginResult
                        .onSuccess {
                            android.util.Log.d(
                                "DoListRegister",
                                "Login automático exitoso"
                            )

                            _loginState.value = LoginState.Success
                        }
                        .onFailure { error ->

                            android.util.Log.e(
                                "DoListRegister",
                                "Falló el login automático",
                                error
                            )

                            _loginState.value = LoginState.Error(
                                error.message
                                    ?: "La cuenta fue creada, pero no se pudo iniciar sesión"
                            )
                        }
                }
                .onFailure { error ->

                    android.util.Log.e(
                        "DoListRegister",
                        "Falló el registro",
                        error
                    )

                    _loginState.value = LoginState.Error(
                        error.message
                            ?: "No se pudo crear la cuenta"
                    )
                }
        }
    }

    fun resetLoginState() {
        _loginState.value = LoginState.Idle
    }

    fun logout() {
        viewModelScope.launch {
            authRepository.logout()
        }
    }

    fun loadCurrentUser() {
        viewModelScope.launch {

            android.util.Log.d(
                "DoListUser",
                "Solicitando usuario actual..."
            )

            _userState.value = UserState(isLoading = true)

            val result = authRepository.getCurrentUser()

            result
                .onSuccess { user ->

                    android.util.Log.d(
                        "DoListUser",
                        "Usuario obtenido: id=${user.id}, name=${user.name}, email=${user.email}"
                    )

                    _userState.value = UserState(
                        user = user
                    )
                }
                .onFailure { error ->

                    android.util.Log.e(
                        "DoListUser",
                        "Error obteniendo usuario: ${error.message}",
                        error
                    )

                    _userState.value = UserState(
                        error = error.message
                            ?: "No se pudo obtener el usuario"
                    )
                }
        }
    }

}