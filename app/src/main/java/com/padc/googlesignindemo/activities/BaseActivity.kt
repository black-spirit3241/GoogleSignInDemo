package com.padc.googlesignindemo.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    protected  fun showMessage(message:String){
//        Snackbar.make(window.decorView,message, Snackbar.LENGTH_LONG).show()
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }

}