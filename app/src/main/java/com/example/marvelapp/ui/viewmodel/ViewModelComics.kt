package com.example.marvelapp.ui.viewmodel

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marvelapp.model.comics.Result
import com.example.marvelapp.model.ui.ComicsUi
import com.example.marvelapp.model.ui.toComics
import com.example.marvelapp.repository.RepositoryApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.UnknownHostException

class ViewModelComics : ViewModel() {

    private val _listMutableComics = MutableLiveData<List<ComicsUi>>()
    val listMutableComics: LiveData<List<ComicsUi>> = _listMutableComics
//    private val _emptyField = MutableLiveData<Boolean>()
//    val emptyField: LiveData<Boolean> = _emptyField

    val loading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()
    private val repository = RepositoryApi()

//    fun searchComics(search: Editable?) {
//        if (search != null && search.isNotEmpty()) {
//            getAllComicsByName(search.toString())
//        } else {
//            _emptyField.postValue(true)
//        }
//    }

    fun getAllComics() = CoroutineScope(Dispatchers.IO).launch {
        loading.postValue(true)
        try {
            repository.getComicsService().let { comicsResponse ->
            val comics = comicsResponse.data.results.map {
                it.toComics()
            }
                _listMutableComics.postValue(comics)
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


//    private fun getAllComicsByName(name: String) {
//        CoroutineScope(Dispatchers.IO).launch {
//            loading.postValue(true)
//            try {
//                repository.getComicsOrderName(name).let { comicsResponse ->
//                    _listMutableComics.postValue(comicsResponse.data.results)
//                    loading.postValue(false)
//                }
//            } catch (error: Throwable) {
//                loading.postValue(false)
//                handleError(error)
//            }
//        }
//    }
}