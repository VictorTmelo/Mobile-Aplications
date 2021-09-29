package com.example.myapplication1.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.room.Room
import com.example.myapplication1.R
import com.example.myapplication1.dao.DataBase
import com.example.myapplication1.util.DataBaseUtil


class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        //UserUtil.createUserList()


        val handler = Handler()
        handler.postDelayed({

            val it = Intent(this, LoginActivity::class.java)

            startActivity(it)
            finish()

        },3000)
    }
}
