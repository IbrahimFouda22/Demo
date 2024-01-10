package com.example.demo.ui.auth.signup

import com.example.demo.ui.shared.UserUiState

data class SignUpState(
    val isLoading: Boolean = false,
    val navigateToSignUp: Boolean = false,
    val error: String? = null,
    val userUiState: UserUiState? = null,
)