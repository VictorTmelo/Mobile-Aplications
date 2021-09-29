package com.example.myapplication1.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(

    tableName = "categories",

    foreignKeys = [
        ForeignKey(entity = User::class, parentColumns = ["id"], childColumns = ["user_id"]
    )]
)

data class Category(

    @PrimaryKey
    var id:Int? = null,
    var name:String,

    @ColumnInfo(name = "user_id")
    var userId:Int

)