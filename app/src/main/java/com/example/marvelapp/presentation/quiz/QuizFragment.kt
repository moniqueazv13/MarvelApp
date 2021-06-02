package com.example.marvelapp.presentation.quiz


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.marvelapp.Constants.CHAVE_NOME
import com.example.marvelapp.Helper
import com.example.marvelapp.R

class QuizFragment : Fragment(R.layout.fragment_quiz){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btStartGame = view.findViewById<Button>(R.id.bt_start)
        btStartGame.setOnClickListener {
            val intent = Intent(requireContext(), QuizActivity::class.java)
            val bundle = Bundle()
            val rnds = (1..4).random()

            intent.putExtra(CHAVE_NOME, Helper.buscaChaveQuiz(rnds))
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }
}


