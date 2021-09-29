package com.example.myapplication1.entity

data class Category(

    var id:Long? = null,
    var name:String,
    var description:String,
    var tasks:List<Task>

)