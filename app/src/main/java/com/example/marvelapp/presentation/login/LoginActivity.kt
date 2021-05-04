package com.example.marvelapp.presentation.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.marvelapp.R
import com.example.marvelapp.presentation.MainActivity
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private val buttonCreateAccount by lazy { findViewById<Button>(R.id.button_create_an_account1) }
    private val buttonForgotPassword by lazy { findViewById<Button>(R.id.button_forgot_your_password3) }
    private val buttonSignIn by lazy { findViewById<MaterialButton>(R.id.button_sign_in1) }
    private val nameField by lazy { findViewById<TextInputEditText>(R.id.tiet_email1) }
    private val passField by lazy { findViewById<TextInputEditText>(R.id.tiet_password1) }
    private val nameInputLayout by lazy { findViewById<TextInputLayout>(R.id.til_email1) }
    private val passInputLayout by lazy { findViewById<TextInputLayout>(R.id.til_password1) }

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var callbackManager: CallbackManager
    private val loginManager = LoginManager.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        createAnAccount()

        forgotPassword()

        signIn()

        firebaseAuth = FirebaseAuth.getInstance()

        callbackManager = CallbackManager.Factory.create()
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

        val name = nameField?.text.toString()
        val pass = passField?.text.toString()

        when {
            name.isBlank() && pass.isBlank() -> {
                nameInputLayout?.error = "Required"
                passInputLayout?.error = "Required"
            }
            name.isBlank() -> {
                nameInputLayout?.error = "Required"
                passInputLayout?.error = null
            }
            pass.isBlank() -> {
                passInputLayout?.error = "Required"
                nameInputLayout?.error = null
            }
            else -> {
                nameInputLayout?.error = null
                passInputLayout?.error = null
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun loginFacebook() {
        loginManager.logInWithReadPermissions(this, arrayListOf("email", "public_profile"))
        loginManager.registerCallback(callbackManager, object :
            FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                Log.d("facebook", "facebook:onSuccess:$loginResult")
                handleFacebookAccessToken(loginResult.accessToken)

            }

            override fun onCancel() {
                Log.d("facebook", "facebook:onCancel")
            }

            override fun onError(error: FacebookException) {
                Log.d("facebook", "facebook:onError", error)
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Pass the activity result back to the Facebook SDK
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    private fun handleFacebookAccessToken(token: AccessToken) {
        Log.d("facebook", "handleFacebookAccessToken:$token")

        val credential = FacebookAuthProvider.getCredential(token.token)
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("facebook", "signInWithCredential:success")
                    val name = firebaseAuth.currentUser?.displayName
                    setUserName(name)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("facebook", "signInWithCredential:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                    setUserName("Usuário desconectado")
                }
            }
    }

    private fun setUserName(name: String?) {
        Toast.makeText(
            baseContext, "Welcome, $name",
            Toast.LENGTH_SHORT
        ).show()

    }

    override fun onStart() {
        super.onStart()
        val currentUser = firebaseAuth.currentUser
        setUserName(currentUser?.displayName)
    }

    fun signingFace(view: View) {
        loginFacebook()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
//
//    fun signout(view: View) {
//        firebaseAuth.signOut()
//        loginManager.logOut()
//
//        setUserName("Usuário desconectado")
//    }

}