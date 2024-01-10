package com.example.demo.ui.auth.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demo.ui.shared.AddressUiState
import com.example.demo.ui.shared.UserUiState
import com.example.domain.entity.User
import com.example.domain.usecase.ManageAuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val manageAuthUseCase: ManageAuthUseCase) :
    ViewModel() {
    private val _signUpState = MutableStateFlow(SignUpState())
    val signUpState = _signUpState as StateFlow<SignUpState>

    fun signUp(email: String?,name: String?, pass: String?, phone: String?,confirmPass: String?) {
        _signUpState.update {
            SignUpState(
                isLoading = true,
                error = null,
                userUiState = null
            )
        }
        viewModelScope.launch {
            try {
                onSignUpSuccess(manageAuthUseCase.signUp(email,name,pass,phone,confirmPass))
            } catch (e: Exception) {
                onSignUpFailure(e.message.toString())
            }
        }
    }

    private fun onSignUpSuccess(user: User) {
        _signUpState.update {
            SignUpState(
                isLoading = false,
                error = null,
                userUiState = UserUiState(
                    id = user.id,
                    name = user.name,
                    token = user.token,
                    addresses = user.addresses.map {
                        AddressUiState(
                            address = it.address
                        )
                    }
                )
            )
        }
    }

    private fun onSignUpFailure(error: String?) {
        _signUpState.update {
            SignUpState(
                isLoading = false,
                error = error,
                userUiState = null
            )
        }
    }

    fun navigateToSignIn() {
        _signUpState.update {
            SignUpState(
                navigateToSignUp = true
            )
        }
    }

    fun navigateToSignInDone() {
        _signUpState.update {
            SignUpState(
                navigateToSignUp = false
            )
        }
    }
}