package com.example.myapplication1.dao

import androidx.room.*
import com.example.myapplication1.entity.Category

@Dao
interface CategoryDao {

    @Insert
    fun insert(category: Category)

    @Update
    fun update(category:Category)

    @Delete
    fun delete(category: Category)

    @Query("SELECT * FROM categories")
    fun findAll(): List<Category>

    @Query("SELECT * FROM categories WHERE id = :id")
    fun findById(id:Int):Category?

}