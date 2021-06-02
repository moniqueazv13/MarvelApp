package com.example.marvelapp.presentation.quiz

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.marvelapp.R
import java.text.DecimalFormat

class ResultadoFragment : Fragment() {
    private val formato = DecimalFormat("#")
    private var helperQuiz: HelperQuiz? = null
    private var porcentagem: TextView? = null
    private var rtitulo: String? = ""
    private var questao: TextView? = null
    private var titulo: TextView? = null
    private var rcorreto = 0.0
    private var rerrado = 0.0
    private var resultado = 0.0
    private var porcento: String? = null
    private var porcento2: String? = null
    private var share_Personagem: ImageView? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v: View = inflater.inflate(R.layout.fragment_resultado, container, false)
        initViews(v)
        val bundle = this.arguments
        rtitulo = bundle!!.getString(getString(R.string.titulo))
        rerrado = bundle.getInt(getString(R.string.errado)).toDouble()
        rcorreto = bundle.getInt(getString(R.string.correto)).toDouble()
        rerrado += rcorreto
        titulo!!.text = rtitulo
        porcento = formato.format(rerrado).toString()
        porcento2 = formato.format(rcorreto).toString()
        val texto =
            porcento2 + getString(R.string.simbolo_barra) + porcento + getString(R.string.resp_corretas)
        questao!!.text = texto
        resultado = rcorreto / rerrado * 100
        porcento = formato.format(resultado).toString()
        porcentagem!!.text = porcento
//        clickBtnShared()
        return v
    }

    private fun initViews(v: View) {
        porcentagem = v.findViewById(R.id.resultado_numero)
        questao = v.findViewById(R.id.resultado_numero_questao)
        titulo = v.findViewById(R.id.titulo_resultado)
        //share_Personagem = v.findViewById(R.id.share_resultado)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        helperQuiz = activity as HelperQuiz?
    }

    private fun shareMarvel() {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(
            Intent.EXTRA_TEXT, """
     ${getString(R.string.result)}
     Resultado: $porcento% 
     $porcento2${getString(R.string.questoes_corretas)}
     """.trimIndent()
        )
        sendIntent.type = "text/plain"
        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

//    private fun clickBtnShared() {
//        share_Personagem!!.setOnClickListener { v: View? -> shareMarvel() }
//    }
}