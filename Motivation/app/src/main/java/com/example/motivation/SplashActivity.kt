package com.example.motivation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() , View.OnClickListener{

    private lateinit var security: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        security = SecurityPreferences(this)

        button.setOnClickListener(this);

        verifyUsername()

    }

    override fun onClick(view: View) {

        val id = view.id;

        if(id == R.id.button){

            handleSave()

        }

    }

    private fun verifyUsername(){

        nome.setText(security.getStoredString("personName"))


    }

    private fun handleSave(){

        val name: String = nome.text.toString()

        if(name == ""){

            Toast.makeText(this, "Informe seu nome: ", Toast.LENGTH_LONG).show()

        }else {

            security.storeString("key", name)

            val it = Intent(this,MainActivity::class.java)

            startActivity(it)
        }

    }

}
