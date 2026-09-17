package com.marcudlcv.dolist.data

import android.content.Context
import com.marcudlcv.dolist.data.local.TokenStorage
import com.marcudlcv.dolist.data.remote.api.ApiClient
import com.marcudlcv.dolist.data.remote.api.ApiService
import com.marcudlcv.dolist.data.repository.AuthRepository
import com.marcudlcv.dolist.data.repository.TaskRepository

class AppContainer(
    context: Context
) {

    private val appContext = context.applicationContext

    val tokenStorage: TokenStorage by lazy {
        TokenStorage(appContext)
    }

    val apiService: ApiService by lazy {
        ApiClient.create(
            context = appContext,
            tokenStorage = tokenStorage
        )
    }

    val authRepository: AuthRepository by lazy {
        AuthRepository(
            apiService = apiService,
            tokenStorage = tokenStorage
        )
    }

    val taskRepository: TaskRepository by lazy {
        TaskRepository(
            apiService = apiService
        )
    }
}