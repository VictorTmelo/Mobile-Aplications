package com.example.myapplication1.util

import android.content.Context
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.myapplication1.dao.DataBase

object DataBaseUtil {

    private var db:DataBase? = null

    private val migration_1_2 = object:Migration(1,2){

        override fun migrate(database: SupportSQLiteDatabase) {

            database.execSQL(""" CREATE TABLE categories(
                id INTEGER PRIMARY KEY,
                name TEXT NOT NULL,
                user_id INTEGER NOT NULL,
                FOREIGN KEY (user_id) REFERENCES users(id) ON UPDATE NO ACTION ON DELETE NO ACTION) """)

        }

    }

    fun getInstance(context:Context):DataBase {

        if (db == null) {

            db = databaseBuilder(
                context,
                DataBase::class.java,
                "droidtododatabase"
            ).addMigrations(migration_1_2)
                .build()
        }

        return db!!

    }
}