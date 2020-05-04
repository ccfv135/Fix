package com.chrisfajardo.fix

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_auth.*
import kotlinx.android.synthetic.main.activity_home.*

enum class ProviderTipe{
    BASIC
}

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val bundle = intent.extras
        val email = bundle?.getString("email")
        val provider = bundle?.getString("provider")

        setup(email?:"",provider?:"")
servicesButton.setOnClickListener {
    val intent=Intent(this,Services::class.java)
    startActivity(intent)
}
        publishButtomone.setOnClickListener {
            val intent= Intent(this,PostActivity::class.java)
            startActivity(intent)
        }
    }
    private fun setup(email: String, provider: String) {
        title = "inicio"
        emailText.text = email
        providerTextView.text = provider

        buttonLogout.setOnClickListener { FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }

    }
}
