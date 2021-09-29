package com.example.myapplication1.entity

import androidx.room.Embedded
import androidx.room.Relation

data class UserCategories(

    @Embedded
    val user: User,

    @Relation(

        parentColumn = "id",

        entityColumn = "user_id"

    )
    val categories:List<Category>


)