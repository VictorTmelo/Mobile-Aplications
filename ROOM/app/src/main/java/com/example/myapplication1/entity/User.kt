package com.example.myapplication1.entity


import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(

    tableName = "users",

    indices = [ Index(value = ["email"], unique = true)]

)

data class User(

    @PrimaryKey
    var id:Int? = null,
    var name:String,
    var email:String,
    var password:String

)