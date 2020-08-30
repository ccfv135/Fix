package com.chrisfajardo.fix.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chrisfajardo.fix.R

class SplashscreemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreem)
    }
}
