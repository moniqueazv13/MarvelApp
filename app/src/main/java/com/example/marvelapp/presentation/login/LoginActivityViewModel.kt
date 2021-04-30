package com.example.marvelapp.presentation.login

import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marvelapp.presentation.MainActivity

class LoginActivityViewModel: ViewModel() {
    val fieldEmail: MutableLiveData<Boolean> by lazy {MutableLiveData<Boolean>()}
    val fieldPassword: MutableLiveData<Boolean> by lazy {MutableLiveData<Boolean>()}

    //logica de validação de parametros de entrada
    fun validateEntryFields(email: String, password: String){

        when {
            email.isBlank() && password.isBlank() -> {
                fieldEmail.postValue(false)
                fieldPassword.postValue(false)
            }
            email.isBlank() -> {
                fieldEmail.postValue(false)
                fieldPassword.postValue(true)
            }
            password.isBlank() -> {
                fieldEmail.postValue(true)
                fieldPassword.postValue(false)
            }
            else -> {
                fieldEmail.postValue(true)
                fieldPassword.postValue(true)
            }
        }

        // chamada de api

        // resultado usuario

        // shared para salvar o usuario 1:09 revisao 17.03 - 02:13:22

    }

    // logica de login

}