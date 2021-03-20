package com.example.marvelapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment

class FragmentConfirmWrong:DialogFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        getDialog()!!.getWindow()?.setBackgroundDrawableResource(R.drawable.background_dialog_fragment)
        return inflater.inflate(R.layout.fragment_dialog_wrong, container, false)
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.65).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.45).toInt()
        dialog!!.window?.setLayout(width, height)
    }
}