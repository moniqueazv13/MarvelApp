package com.example.marvelapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.example.marvelapp.R

class FragmentConfirmWrong:DialogFragment() {

    private val btContinueW by lazy { view?.findViewById<Button>(R.id.bt_continue_wrong)}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        getDialog()!!.getWindow()?.setBackgroundDrawableResource(R.drawable.background_dialog_fragment)
        return inflater.inflate(R.layout.fragment_dialog_wrong, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btContinueW?.setOnClickListener {
            dismiss()
            // val intentGame = Intent(context, QuizActivity::class.java)
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