package com.example.marvelapp.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marvelapp.Constants.POSTS_REFERENCE
import com.example.marvelapp.model.ui.ComicsUi
import com.example.marvelapp.model.ui.toComics
import com.example.marvelapp.repository.RepositoryApi
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.UnknownHostException

class ViewModelComics : ViewModel() {

    private val _listMutableComics = MutableLiveData<List<ComicsUi>>()
    val listMutableComics: LiveData<List<ComicsUi>> = _listMutableComics

    private val _post = MutableLiveData<String>()
    val post : LiveData<String> = _post

    val loading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()
    private val repository = RepositoryApi()

    private val database = Firebase.database
    private val ref : DatabaseReference = database.getReference(POSTS_REFERENCE)


    init {
        listenForPostsValueChanges()
    }

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

    private fun listenForPostsValueChanges() {

        val postsValueEventListener = object : ValueEventListener {

            override fun onCancelled(databaseError: DatabaseError) {
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val text = dataSnapshot.value as Map<String, Any>?
                    _post.postValue(text.orEmpty().toString())
                } else {

                    _post.postValue("")
                }
            }
        }

        ref.addValueEventListener(postsValueEventListener)
    }

    private fun handleError(error: Throwable) {
        when (error) {
            is HttpException -> errorMessage.postValue("Erro de conexão código: ${error.code()}")
            is UnknownHostException -> errorMessage.postValue("Verifique sua conexão")
        }
    }
}