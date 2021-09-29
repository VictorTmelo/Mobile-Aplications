package com.example.myapplication1.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication1.R
import com.example.myapplication1.util.DataBase
import com.example.myapplication1.util.UserUtil
import org.jetbrains.anko.doAsync

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        UserUtil.createUserList()

        val db = Room.databaseBuilder(

            this,
            DataBase::class.java,
            "droidtododatabase"
        ).build()


        doAsync {

            db.userDao().insertUser(UserUtil.getUser()[0])

            db.userDao().findAll().forEach{

                    user -> Log.i("ToDo", "Nome : ${user.name} , Email: ${user.email}")

            }

            db.userDao().deleteUser(UserUtil.getUser()[0])

            db.userDao().findAll().forEach{

                    user -> Log.i("ToDo", "Nome : ${user.name} , Email: ${user.email}")

            }

        }


        val handler = Handler()
        handler.postDelayed({

            val it = Intent(this, LoginActivity::class.java)

            startActivity(it)
            finish()

        },3000)
    }
}
