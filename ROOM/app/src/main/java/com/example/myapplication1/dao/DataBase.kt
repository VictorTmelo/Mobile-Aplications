package com.example.myapplication1.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication1.dao.UserDao
import com.example.myapplication1.entity.Category
import com.example.myapplication1.entity.User

@Database(

    entities = [User::class, Category::class] , version = 2

)

abstract class DataBase: RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun categoryDao(): CategoryDao


}