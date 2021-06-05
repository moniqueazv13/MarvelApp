package com.example.marvelapp.presentation.quiz

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.marvelapp.Constants.CHAVE_NOME
import com.example.marvelapp.R
import com.example.marvelapp.presentation.MainActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class QuizActivity : AppCompatActivity(), HelperQuiz {
    private var fragmentResultado =  ResultadoFragment()
    private var fragmentQuiz = PerguntasQuizFragment()
    private var mcorreto = 0
    private var merrado = 0
    private var mtitulo = ""
    val btHome by lazy { findViewById<FloatingActionButton>(R.id.bt_backhome) }

    //@BindView(R.id.tapBarMenu)
    //var tapBarMenu: TapBarMenu? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        //window.setBackgroundDrawableResource(R.drawable.background_padrao)
        //ButterKnife.bind(this)


        initViews()
        if (intent != null) {
            val intent = intent
            nome = intent.extras!!.getString(CHAVE_NOME)
        }
        replaceFragments(R.id.container, fragmentQuiz)

        btHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initViews() {
        fragmentResultado = ResultadoFragment()
        fragmentQuiz = PerguntasQuizFragment()
    }

    private fun replaceFragments(container: Int, fragment: Fragment?) {
        supportFragmentManager
            .beginTransaction()
            .replace(container, fragment!!)
            .commit()
    }

//    @OnClick(R.id.tapBarMenu)
//    fun onMenuButtonClick() {
//        tapBarMenu.toggle()
//    }

//    @OnClick([R.id.item1, R.id.item2, R.id.item3, R.id.item4])
//    fun onMenuItemClick(view: View) {
//        tapBarMenu.close()
//        when (view.id) {
//            R.id.item1 -> startActivity(
//                Intent(
//                    this@RecebePerguntasQuizActivity,
//                    MainActivity::class.java
//                )
//            )
//            R.id.item2 -> startActivity(
//                Intent(
//                    this@RecebePerguntasQuizActivity,
//                    RecyclerFavoritosActivity::class.java
//                )
//            )
//            R.id.item3 -> startActivity(
//                Intent(
//                    this@RecebePerguntasQuizActivity,
//                    RecyclerPersonagensActivity::class.java
//                )
//            )
//            R.id.item4 -> startActivity(
//                Intent(
//                    this@RecebePerguntasQuizActivity,
//                    RecyclerQuizActivity::class.java
//                )
//            )
//        }
//    }

    override fun correto(correto: Int) {
        mcorreto = correto
    }

    override fun errado(errado: Int) {
        merrado = errado
    }

    override fun troca() {
        val bundle = Bundle()
        bundle.putInt(getString(R.string.correto), mcorreto)
        bundle.putInt(getString(R.string.errado), merrado)
        bundle.putString(getString(R.string.titulo), mtitulo)
        fragmentResultado.arguments = bundle
        replaceFragments(R.id.container, fragmentResultado)
    }

    override fun titulo(titulo: String?) {
        if (titulo != null) {
            mtitulo = titulo
        }
    }

    companion object {
        var nome: String? = null
    }
}