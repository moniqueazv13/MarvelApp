package com.example.marvelapp.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.marvelapp.model.Result
import com.example.marvelapp.repository.RepositoryApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.UnknownHostException

class ViewModelCharacter {
    val listMutableCharacter = MutableLiveData<List<Result>>()
    val loading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()

    private val repositoryApi = RepositoryApi()
    init {
        getAllCharacters()
    }

    private fun getAllCharacters() = CoroutineScope(IO).launch {
        loading.postValue(true)
        try {

            repositoryApi.getCharacterService().let { characterResponse ->
                listMutableCharacter.postValue((characterResponse.data.results))
                loading.postValue(false)
            }
        } catch (error: Throwable) {
            loading.postValue(false)
            handleError(error   )
        }

    }

    private fun handleError(error: Throwable) {
        when (error) {
            is HttpException -> errorMessage.postValue("Erro de conexão código: ${error.code()}")
            is UnknownHostException -> errorMessage.postValue("Verifique sua conexão")
        }
    }
}