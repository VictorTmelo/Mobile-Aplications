package com.example.myapplication1.dao

import android.provider.ContactsContract
import androidx.room.*
import com.example.myapplication1.entity.User

@Dao
interface UserDao {

    @Insert
    fun insertUser(user:User)

    @Update
    fun updateUser(user:User)

    @Delete
    fun deleteUser(user:User)

    @Query("SELECT * FROM users")
    fun findAll():List<User>

    @Query("SELECT * FROM users WHERE id = :id")
    fun find(id:Long):User?

    @Query("SELECT * FROM users WHERE email = :email")
    fun findByEmail(email: String):User?


}
