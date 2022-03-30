package com.chrisfajardo.fixjob.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chrisfajardo.fixjob.R

class SplashscreemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreem)
    }
}
