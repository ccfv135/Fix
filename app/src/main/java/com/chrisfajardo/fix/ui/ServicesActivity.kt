package com.chrisfajardo.fix.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chrisfajardo.fix.R
import kotlinx.android.synthetic.main.activity_services.*

class ServicesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_services)

        paintingButtom.setOnClickListener {
            val intent= Intent(this,
                PaintScreenActivity::class.java)
            startActivity(intent)
        }


    }
}
