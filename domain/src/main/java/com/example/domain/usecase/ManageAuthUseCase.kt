package com.example.domain.usecase

import com.example.domain.entity.User
import com.example.domain.exception.EmptyDataException
import com.example.domain.repo.IRepo
import javax.inject.Inject

class ManageAuthUseCase @Inject constructor(private val repo: IRepo) {
    suspend fun signIn(email: String?, password: String?, deviceToken: String): User {
        validateSignInData(email, password)
        return repo.signIn(email!!, password!!, deviceToken)
    }

    suspend fun signUp(
        email: String?,
        name: String?,
        password: String?,
        phone: String?,
        confirmPass: String?
    ): User {
        validateSignUpData(email, name, password, phone,confirmPass)
        return repo.signUp(email!!, name!!, password!!, phone!!)
    }


    private fun validateSignInData(email: String?, password: String?) {
        if (email.isNullOrEmpty())
            throw EmptyDataException("Email Field is empty")
        if (password.isNullOrEmpty())
            throw EmptyDataException("Password Field is empty")
    }

    private fun validateSignUpData(
        email: String?,
        name: String?,
        password: String?,
        phone: String?,
        confirmPass: String?
    ) {
        if (name.isNullOrEmpty())
            throw EmptyDataException("Name Field is empty")
        if (email.isNullOrEmpty())
            throw EmptyDataException("Email Field is empty")
        if (phone.isNullOrEmpty())
            throw EmptyDataException("Phone Field is empty")
        if (password.isNullOrEmpty())
            throw EmptyDataException("Password Field is empty")
        if (confirmPass.isNullOrEmpty())
            throw EmptyDataException("Confirm Password Field is empty")
    }
}