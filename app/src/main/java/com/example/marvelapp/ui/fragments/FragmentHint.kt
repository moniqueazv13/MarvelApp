package com.example.marvelapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.example.marvelapp.R

class FragmentHint: DialogFragment() {

    private val btBackGame by lazy { view?.findViewById<Button>(R.id.bt_hint_back) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        getDialog()!!.getWindow()?.setBackgroundDrawableResource(R.drawable.background_dialog_fragment)
        return inflater.inflate(R.layout.fragment_dialog_hint, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btBackGame?.setOnClickListener {
            dismiss()
            //val intentGame = Intent(context, QuizActivity::class.java)
            //context!!.startActivity(intentGame)
        }
    }

    override fun onStart() {
        super.onStart()
//        val width = (resources.displayMetrics.widthPixels * 0.65).toInt()
//        val height = (resources.displayMetrics.heightPixels * 0.45).toInt()
//        dialog!!.window?.setLayout(width, height)
    }
}