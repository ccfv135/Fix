package com.chrisfajardo.fix

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chrisfajardo.fix.paintscreen.PaintScreenActivity
import kotlinx.android.synthetic.main.activity_services.*

class Services : AppCompatActivity() {

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
