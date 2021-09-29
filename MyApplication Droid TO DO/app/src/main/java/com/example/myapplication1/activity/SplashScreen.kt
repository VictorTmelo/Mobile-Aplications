package com.example.myapplication1.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.myapplication1.R
import com.example.myapplication1.util.UserUtil

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        UserUtil.createUserList()

        val handler = Handler()
        handler.postDelayed({

            val it = Intent(this, LoginActivity::class.java)

            startActivity(it)
            finish()

        },3000)
    }
}
