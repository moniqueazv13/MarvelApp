package com.example.marvelapp


import com.example.marvelapp.R

object Helper {
    fun buscaChaveQuiz(n: Int): String {
        var sChave = ""
        when (n) {
            //R.string.quiz_total-> sChave = "HA"
            1-> sChave = "1"
            2-> sChave = "2"
            3-> sChave = "3"
            4-> sChave = "4"
        }
        return sChave
    }
}