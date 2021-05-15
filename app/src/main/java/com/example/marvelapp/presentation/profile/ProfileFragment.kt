package com.example.marvelapp.presentation.profile

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.marvelapp.R
import com.example.marvelapp.presentation.login.LoginActivity
import com.example.marvelapp.presentation.quiz.QuizActivity
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val firebaseAuth = FirebaseAuth.getInstance()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btSignOut = view.findViewById<MaterialButton>(R.id.button_sign_out)
        btSignOut.setOnClickListener {
            signOut()
        }

        firebaseAuth.currentUser?.let { user ->
            txt_profile_user_email.text = user.email
            txt_user_name.text = user.displayName
        }

    }

    private fun signOut() {
        firebaseAuth.signOut()
        Toast.makeText(activity, "Usuário desconectado", Toast.LENGTH_LONG).show()
        startActivity(Intent(requireContext(), LoginActivity::class.java))
    }


}


