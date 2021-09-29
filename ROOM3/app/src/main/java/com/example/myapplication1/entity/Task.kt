package com.example.myapplication1.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(

    tableName = "tasks",

    foreignKeys = arrayOf(
        ForeignKey(entity = Category::class, parentColumns = arrayOf("id"), childColumns = arrayOf("category_id"))
    )

)


data class Task(

    @PrimaryKey
    var id:Long? = null,
    var name:String,
    var description:String,
    var isImportant:Boolean,
    var startDate: Date,
    var endDate: Date,
    var isDone:Boolean,

    @ColumnInfo(name = "category_id")
    var categoryId:Long

)
