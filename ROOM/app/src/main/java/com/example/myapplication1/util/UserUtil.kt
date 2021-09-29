package com.example.myapplication1.util

import android.provider.ContactsContract
import com.example.myapplication1.entity.Category
import com.example.myapplication1.entity.User

object UserUtil {

    var userList = mutableListOf<User>()


    fun createUserList(){

        userList.add(User(1, "Victor Torres", "victor_tmeloo@edu.unifor.br", "1820331"))

    }

    fun addUser(nome:String, email: String, senha:String){


        userList.add(User(userList.size + 1,nome, email, senha))


    }

    fun getUser(): MutableList<User> {

        return userList

    }

    fun getUserByEmail(email: String) : User?{

    return userList.firstOrNull { it.email == email}

    }
}