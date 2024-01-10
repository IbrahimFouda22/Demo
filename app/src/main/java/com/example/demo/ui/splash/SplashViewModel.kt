package com.example.demo.ui.splash

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(): ViewModel() {
    private val _splashState = MutableStateFlow(SplashUiState())
    val splashState = _splashState as StateFlow<SplashUiState>

    fun navigateToHome(){
        _splashState.update {
            SplashUiState(
                navigateToSignIn = false,
                navigateToHome = true
            )
        }
    }
    fun navigateToHomeDone(){
        _splashState.update {
            SplashUiState(
                navigateToSignIn = false,
                navigateToHome = false
            )
        }
    }

    fun navigateToSignIn(){
        _splashState.update {
            SplashUiState(
                navigateToSignIn = true,
                navigateToHome = false
            )
        }
    }
    fun navigateToSignInDone(){
        _splashState.update {
            SplashUiState(
                navigateToSignIn = false,
                navigateToHome = false
            )
        }
    }
}