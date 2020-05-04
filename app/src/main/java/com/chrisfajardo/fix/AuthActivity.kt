package com.chrisfajardo.fix

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.EmailAuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.inject.Provider
import kotlinx.android.synthetic.main.activity_auth.*
import kotlinx.android.synthetic.main.activity_home.*
import java.util.ArrayList

class AuthActivity : AppCompatActivity() {

    companion object{
        private const val RC_SIGN_IN=423
    }

    private val authUser:FirebaseAuth by lazy { FirebaseAuth.getInstance() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        setup()
        googleLogin()



    }
    private fun goto(){
        if (authUser.currentUser!=null){
            startActivity(Intent(this,HomeActivity::class.java ))
            finish()
        }else{
            startActivity(Intent(this,AuthActivity::class.java ))
            finish()
        }
    }

    private fun setup(){
        title= "Autenticacion"
        button.setOnClickListener {
            if (emailBarra.text.isNotEmpty() && passwordBarra.text.isNotEmpty()){

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(emailBarra.text.toString(),
                    passwordBarra.text.toString()).addOnCompleteListener {
                    if (it.isSuccessful){
                        showHome(it.result?.user?.email?:"", ProviderTipe.BASIC)

                    }else{
                        showAlert("Error", "Error al autenticar el usuario")

                    }

                    }
            }

        }
        buttonLogin.setOnClickListener {
            if (emailBarra.text.isNotEmpty() && passwordBarra.text.isNotEmpty()){

                FirebaseAuth.getInstance().signInWithEmailAndPassword(emailBarra.text.toString(),
                    passwordBarra.text.toString()).addOnCompleteListener {
                    if (it.isSuccessful){
                        showHome(it.result?.user?.email?:"", ProviderTipe.BASIC)

                    }else{
                        showAlert("Error", "Error al autenticar el usuario")

                    }

                }
            }

        }

    }

    private fun showAlert(title:String,message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("aceptar",null)
        val dialog:AlertDialog = builder.create()
        dialog.show()
    }
    private fun showHome(email: String, provider: ProviderTipe) {
        val homeIntent = Intent(this, HomeActivity::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider.name)
        }
        startActivity(homeIntent)
    }

    fun facebook(view: View) {}

    fun googleLogin() {

        val providers = arrayListOf(
            AuthUI.IdpConfig.GoogleBuilder().build())


        buttonGoogle.setOnClickListener {
            startActivityForResult(
                AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(providers)
                    .setIsSmartLockEnabled(false)
                    .build(),
                RC_SIGN_IN)

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {
                // Successfully signed in
                val user = FirebaseAuth.getInstance().currentUser
                Toast.makeText(this,"Bienvenid@ ${user!!.displayName}" ,Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,HomeActivity::class.java ))
                finish()
            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
                Toast.makeText(this,"Ocurrio un error ${response!!.error!!.errorCode}" ,Toast.LENGTH_SHORT).show()

            }
        }
    }


}
