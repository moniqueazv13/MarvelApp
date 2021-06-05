package com.example.marvelapp.presentation.quiz

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.fragment.app.Fragment
import com.example.marvelapp.R
import com.example.marvelapp.model.quiz.Questao
import com.example.marvelapp.presentation.MainActivity
import kotlinx.android.synthetic.main.fragment_dialog_confirm_right.view.*
import kotlinx.android.synthetic.main.fragment_dialog_confirm_right.view.bt_continue_right
import kotlinx.android.synthetic.main.fragment_dialog_wrong.view.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.util.*

class PerguntasQuizFragment : Fragment() {
    private var helperQuiz: HelperQuiz? = null
    private var fragment_titulo: TextView? = null
    private var fragmentPergunta: TextView? = null
    private var alternativaUm: Button? = null
    private var alternativaDois: Button? = null
    private var alternativaTres: Button? = null
    private var alternativaQuatro: Button? = null
    private var listaperguntas: MutableList<Questao?>? = null
    private val listaperguntasfiltrada: MutableList<Questao?> = ArrayList<Questao?>()
    private var perguntaAtual = 0
    private var correto = 0
    private var errado = 0
    private var controle = true


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v: View = inflater.inflate(R.layout.fragment_perguntas_quiz, container, false)
        initViews(v)
        carregaTodasPerguntas()
        Collections.shuffle(listaperguntas)
        filtroLista()
        colocarPerguntasTela(perguntaAtual)
        alternativaUm!!.setOnClickListener { v1: View? ->
            if (controle) {
                if (listaperguntasfiltrada[perguntaAtual]?.alternativa1
                        .equals(listaperguntasfiltrada[perguntaAtual]?.resposta)
                ) {
                    correto++
                    rightAnswer(alternativaUm)
                    Log.d("deu certo", "deu certo mesmo")

                } else {
                    errado++
                    ficaVermelho(alternativaUm)
                }
            }
        }
        alternativaDois!!.setOnClickListener { v12: View? ->
            if (controle) {
                if (listaperguntasfiltrada[perguntaAtual]?.alternativa2
                        .equals(listaperguntasfiltrada[perguntaAtual]?.resposta)
                ) {
                    correto++
                    rightAnswer(alternativaDois)
                } else {
                    errado++
                    ficaVermelho(alternativaDois)
                }
            }
        }
        alternativaTres!!.setOnClickListener { v13: View? ->
            if (controle) {
                if (listaperguntasfiltrada[perguntaAtual]?.alternativa3
                        .equals(listaperguntasfiltrada[perguntaAtual]?.resposta)
                ) {
                    correto++
                    rightAnswer(alternativaTres)
                } else {
                    errado++
                    ficaVermelho(alternativaTres)
                }
            }
        }
        alternativaQuatro!!.setOnClickListener { v14: View? ->
            if (controle) {
                if (listaperguntasfiltrada[perguntaAtual]?.alternativa4
                        .equals(listaperguntasfiltrada[perguntaAtual]?.resposta)
                ) {
                    correto++
                    rightAnswer(alternativaQuatro)
                } else {
                    errado++
                    ficaVermelho(alternativaQuatro)
                }
            }
        }
        return v
    }

    private fun initViews(view: View) {
        fragment_titulo = view.findViewById(R.id.fragment_titulo_principal)
        fragmentPergunta = view.findViewById(R.id.fragement_textview_pergunta)
        alternativaUm = view.findViewById(R.id.fragment_button_alternativaUm)
        alternativaDois = view.findViewById(R.id.fragment_button_alternativaDois)
        alternativaTres = view.findViewById(R.id.fragment_button_alternativaTres)
        alternativaQuatro = view.findViewById(R.id.fragment_button_alternativaQuatro)
    }

    private fun carregaJsonDoAsset(file: String): String {
        var json = ""
        try {
            val `is` = requireContext().assets.open(file)
            val size = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            json = String(buffer, StandardCharsets.UTF_8)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return json
    }

    private fun mudaTitulo() {
        if (QuizActivity.nome.equals("HA")) {
            //fragment_titulo.setText(R.string.quiz_spider_man)
            fragment_titulo?.text = R.string.quiz_total.toString()
        }
//        if (RecebePerguntasQuizActivity.nome.equals("HF")) {
//            //fragment_titulo.setText(R.string.quiz_homem_ferro)
//            fragment_titulo?.text = R.string.quiz_homem_ferro.toString()
//        }
//        if (RecebePerguntasQuizActivity.nome.equals("TH")) {
//            //fragment_titulo.setText(R.string.quiz_thor)
//            fragment_titulo?.text = R.string.quiz_thor.toString()
//        }
//        if (RecebePerguntasQuizActivity.nome.equals("CA")) {
//            //fragment_titulo.setText(R.string.quiz_capitao)
//            fragment_titulo?.text = R.string.quiz_capitao.toString()
//        }
    }

    private fun filtroLista() {
        for (i in listaperguntas!!.indices) {
            if (QuizActivity.nome.equals(listaperguntas!![i]?.nome)) {
                listaperguntasfiltrada.add(listaperguntas!![i])
            }
        }
        //mudaTitulo()
    }

    private fun colocarPerguntasTela(numero: Int) {
        //fragmentPergunta.setText(listaperguntasfiltrada[numero]?.pergunta)
        fragmentPergunta?.text = listaperguntasfiltrada[numero]?.pergunta
        //alternativaUm?.setText(listaperguntasfiltrada[numero]?.alternativa1)
        alternativaUm?.text = listaperguntasfiltrada[numero]?.alternativa1
        alternativaDois?.text = listaperguntasfiltrada[numero]?.alternativa2
        alternativaTres?.text = listaperguntasfiltrada[numero]?.alternativa3
        alternativaQuatro?.text = listaperguntasfiltrada[numero]?.alternativa4

        controle = true
    }

    private fun carregaTodasPerguntas() {
        listaperguntas = ArrayList<Questao?>()
        val jsonStr = carregaJsonDoAsset(getString(R.string.json))
        try {
            val jsonObject = JSONObject(jsonStr)
            val perguntas = jsonObject.getJSONArray(getString(R.string.perguntas))
            for (i in 0 until perguntas.length()) {
                val pergunta = perguntas.getJSONObject(i)
                val nomeString = pergunta.getString(getString(R.string.nome))
                val perguntaString = pergunta.getString(getString(R.string.pergunta))
                val alternativa1String = pergunta.getString(getString(R.string.alt_1))
                val alternativa2String = pergunta.getString(getString(R.string.alt_2))
                val alternativa3String = pergunta.getString(getString(R.string.alt_3))
                val alternativa4String = pergunta.getString(getString(R.string.alt_4))
                val respostaString = pergunta.getString(getString(R.string.resposta))
                listaperguntas!!.add(
                    Questao(
                        nomeString,
                        perguntaString,
                        alternativa1String,
                        alternativa2String,
                        alternativa3String,
                        alternativa4String,
                        respostaString
                    )
                )
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    private fun ficaVermelho(button: Button?) {
        controle = false
        val dialogView = LayoutInflater.from(context)
            .inflate(R.layout.fragment_dialog_wrong, null)
        val dialog = AlertDialog.Builder(context)
            .setView(dialogView)
            .create()
        dialogView.bt_continue_wrong.setOnClickListener {
            ficaNormal(button)
            dialog.dismiss()
        }
        dialogView.bt_quit_wrong.setOnClickListener {
            Toast.makeText(activity, "Fim do Jogo", Toast.LENGTH_LONG).show()
            startActivity(Intent(requireContext(), MainActivity::class.java))
            dialog.dismiss()
        }
        dialog.show()
    }


    private fun rightAnswer(button: Button?) {
        controle = false
        val dialogView = LayoutInflater.from(context)
            .inflate(R.layout.fragment_dialog_confirm_right, null)
        val dialog = AlertDialog.Builder(context)
            .setView(dialogView)
            .create()
        dialogView.bt_continue_right.setOnClickListener {
            ficaNormal(button)
            dialog.dismiss()
        }
        dialogView.bt_quit_right.setOnClickListener {
            Toast.makeText(activity, "Fim do Jogo", Toast.LENGTH_LONG).show()
            startActivity(Intent(requireContext(), MainActivity::class.java))
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun ficaNormal(button: Button?) {
        val handler = Handler()
        val delay: Long = 1000
        handler.postDelayed({
            //button!!.background = resources.getDrawable(R.drawable.round_button)
            //button!!.resources.getDrawable(R.drawable.round_button,requireContext().theme)
            //button!!.background = resources.getDrawable(R.drawable.round_button,requireContext().theme)
            button!!.background = getDrawable(requireContext(), R.drawable.round_button)
            //button?.setBackgroundResource(R.drawable.round_button)
            confereEPoe()
        }, delay)
    }

    private fun confereEPoe() {
        if (perguntaAtual < listaperguntasfiltrada.size - 1) {
            perguntaAtual++
            colocarPerguntasTela(perguntaAtual)
        } else {
            helperQuiz?.correto(correto)
            helperQuiz?.errado(errado)
            helperQuiz?.titulo(fragment_titulo!!.text.toString())
            helperQuiz?.troca()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        helperQuiz = activity as HelperQuiz?
    }
}