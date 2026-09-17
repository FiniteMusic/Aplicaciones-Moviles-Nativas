package com.marcudlcv.dolist.data.repository

import com.google.gson.JsonObject
import com.marcudlcv.dolist.data.local.TokenStorage
import com.marcudlcv.dolist.data.remote.api.ApiService
import com.marcudlcv.dolist.data.remote.dto.auth.ChangePasswordRequest
import com.marcudlcv.dolist.data.remote.dto.auth.LoginRequest
import com.marcudlcv.dolist.data.remote.dto.auth.RegisterRequest
import com.marcudlcv.dolist.data.remote.dto.auth.UserDto
import com.google.gson.Gson
import retrofit2.Response

private fun getErrorMessage(
    response: Response<*>,
    defaultMessage: String
): String {
    return try {
        val errorBody = response.errorBody()?.string()

        if (!errorBody.isNullOrBlank()) {
            val errorJson = Gson().fromJson(
                errorBody,
                JsonObject::class.java
            )

            errorJson
                .get("error")
                ?.asString
                ?: defaultMessage
        } else {
            defaultMessage
        }

    } catch (_: Exception) {
        defaultMessage
    }
}
class AuthRepository(
    private val apiService: ApiService,
    private val tokenStorage: TokenStorage
) {

    suspend fun register(
        name: String,
        email: String,
        password: String
    ): Result<Unit> {

        return try {
            val response = apiService.register(
                RegisterRequest(
                    name = name,
                    email = email,
                    password = password
                )
            )

            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(
                    Exception(
                        getErrorMessage(
                            response,
                            "No se pudo registrar el usuario"
                        )
                    )
                )
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun login(
        email: String,
        password: String
    ): Result<UserDto> {
        return try {
            val response = apiService.login(
                LoginRequest(
                    email = email,
                    password = password
                )
            )

            if (response.isSuccessful) {
                val body = response.body()

                if (body != null) {
                    // Guardamos el token
                    tokenStorage.saveToken(body.token)

                    // El backend obtiene los datos completos mediante /me
                    getCurrentUser()
                } else {
                    Result.failure(
                        Exception("Respuesta vacía del servidor")
                    )
                }
            } else {
                Result.failure(
                    Exception(
                        response.errorBody()?.string()
                            ?: "Correo o contraseña incorrectos"
                    )
                )
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getCurrentUser(): Result<UserDto> {
        return try {
            val response = apiService.getCurrentUser()

            if (response.isSuccessful) {
                val body = response.body()

                if (body != null) {
                    Result.success(body.user)
                } else {
                    Result.failure(
                        Exception("El servidor no devolvió los datos del usuario")
                    )
                }
            } else {
                Result.failure(
                    Exception("Sesión no válida")
                )
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun changePassword(
        currentPassword: String,
        newPassword: String
    ): Result<Unit> {

        return try {
            val response = apiService.changePassword(
                ChangePasswordRequest(
                    currentPassword = currentPassword,
                    newPassword = newPassword
                )
            )

            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(
                    Exception(
                        response.errorBody()?.string()
                            ?: "No se pudo cambiar la contraseña"
                    )
                )
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun logout() {
        tokenStorage.clearToken()
    }
}