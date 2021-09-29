package com.example.myapplication1.util

import android.content.Context
import androidx.room.Room
import com.example.myapplication1.dao.DataBase

object DataBaseUtil {

    private var db:DataBase? = null

    fun getInstance(context:Context):DataBase {

        if (db == null) {

            db = Room.databaseBuilder(
                context,
                DataBase::class.java,
                "droidtododatabase"
            ).build()
        }

        return db!!

    }
}