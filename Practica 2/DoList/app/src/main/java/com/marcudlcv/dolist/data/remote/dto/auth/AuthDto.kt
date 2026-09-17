package com.marcudlcv.dolist.data.remote.dto.auth

import com.google.gson.annotations.SerializedName

// =========================
// REGISTRO
// =========================

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String
)

data class RegisterResponse(
    val message: String?,
    val user: UserDto?
)


// =========================
// LOGIN
// =========================

data class LoginRequest(
    val email: String,
    val password: String
)

data class LoginResponse(
    val token: String,
    val user: UserDto?
)


// =========================
// USUARIO
// =========================

data class UserDto(
    val id: Int,
    val name: String,
    val email: String,
    @SerializedName("avatarUrl")
    val avatarUrl: String?,
    val createdAt: String?
)

data class MeResponse(
    val user: UserDto
)


// =========================
// CAMBIO DE CONTRASEÑA
// =========================

data class ChangePasswordRequest(
    val currentPassword: String,
    val newPassword: String
)