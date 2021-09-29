package com.example.travisapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        button.setOnClickListener(this);
    }


    override fun onClick(view:View) {

        val id = view.id

        if(id == R.id.button){

            val it = Intent(this, MainActivity::class.java)

            startActivity(it);

        }

    }


}
