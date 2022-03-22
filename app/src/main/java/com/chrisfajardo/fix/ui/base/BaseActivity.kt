package com.chrisfajardo.fix.ui.base

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

open class BaseActivity : AppCompatActivity() {
    fun baseCallPhoneApp(phone: String) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) ==
            PackageManager.PERMISSION_GRANTED
        ) {
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$phone"))
            startActivity(intent)
        } else {
            Toast.makeText(
                this, "No Hay permiso para llamada",
                Toast.LENGTH_LONG
            ).show()
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CALL_PHONE),
                123
            )
        }
    }
}