package com.example.myapplication1.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(

    tableName = "categories",

    foreignKeys = arrayOf(
        ForeignKey(entity = User::class, parentColumns = arrayOf("id"), childColumns = arrayOf("user_id"))
    )
)

data class Category(

    @PrimaryKey
    var id:Long? = null,
    var name:String,
    var description:String,

    @ColumnInfo(name = "user_id")
    var userId:Long

)