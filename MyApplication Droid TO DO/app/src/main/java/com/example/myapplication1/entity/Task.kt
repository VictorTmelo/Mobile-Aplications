package com.example.myapplication1.entity

import java.util.*

data class Task(

    var id:Long? = null,
    var name:String,
    var description:String,
    var isImportant:Boolean,
    var startDate: Date,
    var endDate: Date,
    var isDone:Boolean

)
