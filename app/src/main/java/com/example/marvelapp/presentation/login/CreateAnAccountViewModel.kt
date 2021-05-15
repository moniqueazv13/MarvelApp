package com.example.marvelapp.presentation.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CreateAnAccountViewModel : ViewModel() {
    val fieldEmail: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val fieldPassword: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val fieldPasswordConfirmation: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }

    //logica de validação de parametros de entrada
    fun validateEntryFields(email: String, password: String, confirmationPassword: String) {

        when {
            email.isBlank() && password.isBlank() && confirmationPassword.isBlank() -> {
                fieldEmail.postValue(false)
                fieldPassword.postValue(false)
                fieldPasswordConfirmation.postValue(false)
            }

            email.isBlank() && password.isBlank() -> {
                fieldEmail.postValue(false)
                fieldPassword.postValue(false)
                fieldPasswordConfirmation.postValue(true)
            }

            email.isBlank() && confirmationPassword.isBlank() -> {
                fieldEmail.postValue(false)
                fieldPassword.postValue(true)
                fieldPasswordConfirmation.postValue(false)
            }

            password.isBlank() && confirmationPassword.isBlank() -> {
                fieldEmail.postValue(true)
                fieldPassword.postValue(false)
                fieldPasswordConfirmation.postValue(false)
            }

            email.isBlank() -> {
                fieldEmail.postValue(false)
                fieldPassword.postValue(true)
                fieldPasswordConfirmation.postValue(true)
            }

            password.isBlank() -> {
                fieldEmail.postValue(true)
                fieldPassword.postValue(false)
                fieldPasswordConfirmation.postValue(true)
            }

            confirmationPassword.isBlank() -> {
                fieldEmail.postValue(true)
                fieldPassword.postValue(true)
                fieldPasswordConfirmation.postValue(false)
            }
            else -> {
                fieldEmail.postValue(true)
                fieldPassword.postValue(true)
                fieldPasswordConfirmation.postValue(true)
            }
        }

        // chamada de api

        // resultado usuario

        // shared para salvar o usuario 1:09 revisao 17.03 - 02:13:22

    }

    // logica de login

}