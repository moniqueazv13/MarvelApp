package com.example.marvelapp.ui.viewmodel

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marvelapp.model.characters.Result
import com.example.marvelapp.repository.RepositoryApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.UnknownHostException

class ViewModelCharacters : ViewModel() {

    private val _listMutableChar = MutableLiveData<List<Result>>()
    val listMutableChar: LiveData<List<Result>> = _listMutableChar
    private val _emptyField = MutableLiveData<Boolean>()
    val emptyField: LiveData<Boolean> = _emptyField

    val loading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()
    private val repository = RepositoryApi()

    fun searchHeroes(search: Editable?) {
        if (search != null && search.isNotEmpty()) {
            getAllCharactersByName(search.toString())
        }else{
            _emptyField.postValue(true)
        }
    }

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


    private fun getAllCharactersByName(name: String) {
        CoroutineScope(Dispatchers.IO).launch {
            loading.postValue(true)
            try {
                repository.getCharacterOrderName(name).let { characterResponse ->
                    _listMutableChar.postValue(characterResponse.data.results)
                    loading.postValue(false)
                }
            } catch (error: Throwable) {
                loading.postValue(false)
                handleError(error)
            }
        }
    }
}


