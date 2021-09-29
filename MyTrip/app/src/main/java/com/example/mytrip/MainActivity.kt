package com.example.mytrip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , OnClickListener {

    override fun onClick(view: View) {

        val id = view.id

        if(id == R.id.buttonCalcular){


            handlerCalculate();

        }

    }


    private fun handlerCalculate(){

        // (distancia * preço) / autonomia

        val distancia = distancia.text.toString(). toFloat();
        val preco = preco.text.toString(). toFloat();
        val autonomia = autonomia.text.toString(). toFloat();

        val total = (distancia * preco) / autonomia;

        valor.setText("R$ $total")
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonCalcular.setOnClickListener(this);




    }



}
