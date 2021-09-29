package com.example.myapplication1.entity

data class User(

    var id:Int? = null,
    var name:String,
    var email:String,
    var password:String,
    var categoria:List<Category>
)