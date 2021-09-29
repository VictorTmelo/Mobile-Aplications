package com.example.motivation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var filtro = 0;

    private var mock: Mock = Mock()

    private lateinit var securityPrefences: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        securityPrefences = SecurityPreferences(this)

        //EVENTOS
        setListeners()

        //INICIALIZA

        handleFilter(R.id.imageAll)
        refreshFrase();
        verifyUsername()

    }

    override fun onClick(view: View) {

        val id = view.id

        val listId = listOf(R.id.imageAll,R.id.imageHappy, R.id.imageSun)

        if(id in listId){
            
            handleFilter(id);


        }else if(id == R.id.button2){

            refreshFrase();

        }


    }

    private fun verifyUsername(){

        userName.text = securityPrefences.getStoredString("personName")


    }

    private fun setListeners(){

    imageAll.setOnClickListener(this)
    imageHappy.setOnClickListener(this)
    imageSun.setOnClickListener(this)
    button2.setOnClickListener(this)

    }

    private fun handleFilter(id: Int){



        if(id == R.id.imageAll){

            filtro = 1;

        }

        if(id == R.id.imageHappy){

            filtro = 2

        }

        if(id == R.id.imageSun){

            filtro = 3;

        }

    }

    private fun refreshFrase(){

        userPhrase.text = mock.getFrase(filtro)
    }

}
