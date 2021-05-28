package com.example.marvelapp.presentation.quiz

interface HelperQuiz {
    fun correto(correto: Int)
    fun errado(errado: Int)
    fun troca()
    fun titulo(titulo: String?)
}