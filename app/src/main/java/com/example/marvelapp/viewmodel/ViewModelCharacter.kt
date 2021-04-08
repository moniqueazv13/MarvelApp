package com.example.marvelapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marvelapp.model.Result
import com.example.marvelapp.repository.RepositoryApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.UnknownHostException

class ViewModelCharacters : ViewModel() {

    private val _listMutableChar = MutableLiveData<List<Result>>()
    val listMutableChar: LiveData<List<Result>> = _listMutableChar
    val loading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()
    private val repository = RepositoryApi()


    fun getAllCharacters() = CoroutineScope(Dispatchers.IO).launch {
        loading.postValue(true)
        try {

            repository.getCharacterService().let { characterResponse ->
                _listMutableChar.postValue(characterResponse.data.results)
                loading.postValue(false)
            }
        } catch (error: Throwable) {
            loading.postValue(false)
            handleError(error)
        }

    }

    private fun handleError(error: Throwable) {
        when (error) {
            is HttpException -> errorMessage.postValue("Erro de conexão código: ${error.code()}")
            is UnknownHostException -> errorMessage.postValue("Verifique sua conexão")
        }
    }
}
