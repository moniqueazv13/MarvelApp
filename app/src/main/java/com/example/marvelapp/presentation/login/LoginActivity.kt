package com.example.marvelapp.presentation.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.marvelapp.R
import com.example.marvelapp.presentation.MainActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import java.lang.Exception

class LoginActivity : AppCompatActivity() {
    private val buttonCreateAccount by lazy { findViewById<Button>(R.id.button_create_an_account1) }
    private val buttonForgotPassword by lazy { findViewById<Button>(R.id.button_forgot_your_password3) }
    private val buttonSignIn by lazy { findViewById<MaterialButton>(R.id.button_sign_in1) }
    private val emailField by lazy { findViewById<TextInputEditText>(R.id.tiet_email1) }
    private val passField by lazy { findViewById<TextInputEditText>(R.id.tiet_password1) }
    private val emailInputLayout by lazy { findViewById<TextInputLayout>(R.id.til_email1) }
    private val passInputLayout by lazy { findViewById<TextInputLayout>(R.id.til_password1) }


    //login google usando firebase abaixo.
    //EmailtV usado somente para teste

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    private val emailTv by lazy { findViewById<TextView>(R.id.tv_message4) }
    private val buttonGoogleLogin by lazy { findViewById<Button>(R.id.button_google1) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        createAnAccount()

        forgotPassword()

        signIn()

        //login google usando firebase abaixo:

        firebaseAuth = FirebaseAuth.getInstance()

        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        // usar .requestProfile() para pegar nome, sobrenome, etc

        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions)

//        buttonGoogleLogin.setOnClickListener { signInGoogle(it) } //faltou o it

    }

    //login google usando firebase abaixo:

    override fun onStart() {
        super.onStart()
        val currentUser = firebaseAuth.currentUser
        setUserEmail(currentUser?.email ?: "Usuário desconectado")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == 200) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d("GoogleSign", "firebaseAuthWithGoogle:" + account.idToken)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w("GoogleSign", "Google sign in failed", e)
            } catch (e: Exception) {
                setUserEmail("Erro ao efetuar login")
            }
        }
    }


    private fun createAnAccount() {
        buttonCreateAccount.setOnClickListener {
            val intent = Intent(this, CreateAnAccountActivity::class.java)
            startActivity(intent)
        }
    }

    private fun forgotPassword() {
        buttonForgotPassword.setOnClickListener {
            val intent = Intent(this, ForgotYourPassword::class.java)
            startActivity(intent)
        }
    }

    private fun signIn() {
        buttonSignIn.setOnClickListener {
            onFinishForm()
        }
    }

    private fun onFinishForm() {

        val name = emailField?.text.toString()
        val pass = passField?.text.toString()

        when {
            name.isBlank() && pass.isBlank() -> {
                emailInputLayout?.error = "Required"
                passInputLayout?.error = "Required"
            }
            name.isBlank() -> {
                emailInputLayout?.error = "Required"
                passInputLayout?.error = null
            }
            pass.isBlank() -> {
                passInputLayout?.error = "Required"
                emailInputLayout?.error = null
            }
            else -> {
                emailInputLayout?.error = null
                passInputLayout?.error = null
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }

    //login google usando firebase abaixo:

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("GoogleSign", "signInWithCredential:success")
                    val user = firebaseAuth.currentUser
                    setUserEmail(user?.email ?: "Usuário desconectado")
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("GoogleSign", "signInWithCredential:failure", task.exception)
                    setUserEmail("Erro ao efetuar login")
                }
            }
    }

    private fun setUserEmail(email: String) {
        emailTv.text = email
    }

    fun signInGoogle(view: View) {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, 200)
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}