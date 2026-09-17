package com.marcudlcv.dolist.data.remote.api

import com.marcudlcv.dolist.data.remote.dto.auth.LoginRequest
import com.marcudlcv.dolist.data.remote.dto.auth.LoginResponse
import com.marcudlcv.dolist.data.remote.dto.auth.RegisterRequest
import com.marcudlcv.dolist.data.remote.dto.auth.RegisterResponse
import com.marcudlcv.dolist.data.remote.dto.auth.UserDto
import com.marcudlcv.dolist.data.remote.dto.auth.ChangePasswordRequest
import com.marcudlcv.dolist.data.remote.dto.task.CreateTaskRequest
import com.marcudlcv.dolist.data.remote.dto.task.TaskDto
import com.marcudlcv.dolist.data.remote.dto.task.UpdateTaskRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import com.marcudlcv.dolist.data.remote.dto.auth.MeResponse
import com.marcudlcv.dolist.data.remote.dto.task.TaskListResponse
import com.marcudlcv.dolist.data.remote.dto.task.TaskResponse

interface ApiService {

    // =========================
    // AUTENTICACIÓN
    // =========================

    @POST("api/auth/register")
    suspend fun register(
        @Body request: RegisterRequest
    ): Response<RegisterResponse>

    @POST("api/auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<LoginResponse>

    @GET("api/auth/me")
    suspend fun getCurrentUser(): Response<MeResponse>

    @PUT("api/auth/password")
    suspend fun changePassword(
        @Body request: ChangePasswordRequest
    ): Response<Unit>


    // =========================
    // TAREAS
    // =========================

    @GET("api/tasks")
    suspend fun getTasks(): Response<TaskListResponse>

    @POST("api/tasks")
    suspend fun createTask(
        @Body request: CreateTaskRequest
    ): Response<TaskResponse>

    @PUT("api/tasks/{id}")
    suspend fun updateTask(
        @Path("id") id: Int,
        @Body request: UpdateTaskRequest
    ): Response<TaskResponse>

    @DELETE("api/tasks/{id}")
    suspend fun deleteTask(
        @Path("id") id: Int
    ): Response<Unit>
}