package com.example.demo.ui.auth.signin

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
class SignInViewModel @Inject constructor(private val manageAuthUseCase: ManageAuthUseCase) : ViewModel() {
    private val _signInState = MutableStateFlow(SignInUiState())
    val signInState = _signInState as StateFlow<SignInUiState>

    fun signIn(email: String?, pass: String?) {
        _signInState.update {
            SignInUiState(
                isLoading = true,
                error = null,
                userUiState = null
            )
        }
        viewModelScope.launch {
            try {
                onSignInSuccess(manageAuthUseCase.signIn(email,pass,"12233454566787877"))
            }catch (e:Exception){
                onSignInFailure(e.message.toString())
            }
        }
    }

    private fun onSignInSuccess(user: User){
        _signInState.update {
            SignInUiState(
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

    private fun onSignInFailure(error:String?){
        _signInState.update {
            SignInUiState(
                isLoading = false,
                error = error,
                userUiState = null
            )
        }
    }

    fun navigateToSignUp(){
        _signInState.update {
            SignInUiState(
                navigateToSignUp = true
            )
        }
    }

    fun navigateToSignUpDone(){
        _signInState.update {
            SignInUiState(
                navigateToSignUp = false
            )
        }
    }
}