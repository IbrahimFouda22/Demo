package com.example.demo.ui.auth.signin

import com.example.demo.ui.shared.UserUiState

data class SignInUiState(
    val isLoading: Boolean = false,
    val navigateToSignUp: Boolean = false,
    val error: String? = null,
    val userUiState: UserUiState? = null,
)