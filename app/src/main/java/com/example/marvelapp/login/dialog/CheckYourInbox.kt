package com.example.marvelapp.login.dialog

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.marvelapp.R
import com.example.marvelapp.login.LoginActivity
import com.google.android.material.button.MaterialButton

class CheckYourInbox: DialogFragment() {

    private val buttonBackToSignIn by lazy { view?.findViewById<MaterialButton>(R.id.button_hint_back_to_sign_in) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        getDialog()!!.getWindow()?.setBackgroundDrawableResource(R.drawable.background_dialog_check)
        return inflater.inflate(R.layout.fragment_check_your_inbox, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonBackToSignIn?.setOnClickListener {
            val i = Intent(context, LoginActivity::class.java)
            startActivity(i)
        }

    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.65).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.45).toInt()
        getDialog()!!.getWindow()?.setLayout(width, height)
    }
}