package com.example.marvelapp.presentation.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.marvelapp.R
import com.example.marvelapp.presentation.MainActivity
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider


class LoginActivity : AppCompatActivity() {
    private val buttonCreateAccount by lazy { findViewById<Button>(R.id.button_create_an_account1) }
    private val buttonForgotPassword by lazy { findViewById<Button>(R.id.button_forgot_your_password3) }
    private val buttonSignIn by lazy { findViewById<MaterialButton>(R.id.button_sign_in1) }
    private val emailField by lazy { findViewById<TextInputEditText>(R.id.tiet_email1) }
    private val passField by lazy { findViewById<TextInputEditText>(R.id.tiet_password1) }
    private val fieldLayoutEmail by lazy { findViewById<TextInputLayout>(R.id.til_email1) }
    private val fieldLayoutPassword by lazy { findViewById<TextInputLayout>(R.id.til_password1) }
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    private val emailTv by lazy { findViewById<TextView>(R.id.tv_message4) }
    private var tryLoginFacebook = false

    private lateinit var viewModel: LoginActivityViewModel

    private lateinit var callbackManager: CallbackManager
    private val loginManager = LoginManager.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //quando passar pelo onCreate vamos pegar o LoginActivityViewModel e vai alocar ele na variavel viewmodel. Por isso, posso usar ela na minha classe inteira, possibilitando a mudança das lógicas para o viewmodel
        viewModel = ViewModelProvider(this).get(LoginActivityViewModel::class.java)

        configureValidationOfFields()

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

        callbackManager = CallbackManager.Factory.create()
    }


    //login google usando firebase abaixo:
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
                setUserName("Erro ao efetuar login")
            }
        }

        // Pass the activity result back to the Facebook SDK
        if (tryLoginFacebook) {
            callbackManager.onActivityResult(requestCode, resultCode, data)
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
                    Toast.makeText(this, "Welcome ${user.displayName}", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("GoogleSign", "signInWithCredential:failure", task.exception)
                    setUserName("Erro ao efetuar login")
                }
            }
    }

    fun signInGoogle(view: View) {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, 200)
    }

    fun signInFace(view: View) {
        loginFacebook()
        tryLoginFacebook = true
    }

    private fun loginFacebook() {
        loginManager.logInWithReadPermissions(this, arrayListOf("email", "public_profile"))
        loginManager.registerCallback(callbackManager, object :
            FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                Log.d("facebook", "facebook:onSuccess:$loginResult")
                firebaseAuthWithFacebook(loginResult.accessToken)
            }

            override fun onCancel() {
                Log.d("facebook", "facebook:onCancel")
            }

            override fun onError(error: FacebookException) {
                Log.d("facebook", "facebook:onError", error)
            }
        })
    }

    private fun firebaseAuthWithFacebook(token: AccessToken) {
        Log.d("facebook", "handleFacebookAccessToken:$token")

        val credential = FacebookAuthProvider.getCredential(token.token)
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("facebook", "signInWithCredential:success")
                    val name = firebaseAuth.currentUser?.displayName
                    setUserName(name)
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
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

        val email = emailField?.text.toString()
        val password = passField?.text.toString()

        viewModel.validateEntryFields(email, password)
    }

    private fun configureValidationOfFields() {
        viewModel.fieldEmail.observe(this) { emailValid ->
            if (emailValid) {
                fieldLayoutEmail.error = null
            } else {
                fieldLayoutEmail.error = "Required"
            }

            navigateIfValid()
        }

        viewModel.fieldPassword.observe(this) { passValid ->
            if (passValid) {
                fieldLayoutPassword.error = null
            } else {
                fieldLayoutPassword.error = "Required"
            }

            navigateIfValid()
        }
    }

    private fun navigateIfValid() {
        if (fieldLayoutPassword.error.isNullOrBlank() &&
            fieldLayoutEmail.error.isNullOrBlank()
        ) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

//
//    fun signout(view: View) {
//        firebaseAuth.signOut()
//        loginManager.logOut()
//
//        setUserName("Usuário desconectado")
//    }

}