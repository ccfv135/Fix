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
            intent.putExtra("data","value")
            startActivity(intent)
        }

        buildingButtom.setOnClickListener {
            val intent= Intent(this,
                BuildingScreenActivity::class.java)
            startActivity(intent)
        }
        locksmithButtom.setOnClickListener {
            val intent= Intent(this,
                LocksmithScreenActivity::class.java)
            startActivity(intent)
        }
        cleaningButtom.setOnClickListener {
            val intent= Intent(this,
                CleaningScreenActivity::class.java)
            startActivity(intent)
        }


    }
}
