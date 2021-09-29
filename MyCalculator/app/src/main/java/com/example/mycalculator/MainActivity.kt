package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlin.concurrent.fixedRateTimer
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity(),View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var lista = listOf(zero,um,dois,tres,quatro,cinco,seis,sete,oito,nove,mais,igual,menos,multi,reiniciar,divisao,sinal,porcentagem)
        lista.forEach{it.setOnClickListener(this)}


    }

    lateinit var opMais:String
    lateinit var opIgual:String
    lateinit var opMenos:String
    lateinit var opMulti:String
    lateinit var opDiv:String
    lateinit var controle:String
    var ponto = 0



    override fun onClick(view: View) {

        var id = view.id


        var ids = listOf(zero,um,dois,tres,quatro,cinco,seis,sete,oito,nove)
        ids.forEach{ if(id == it.id){
                       result.setText((result.text.toString().toInt() * 10 + it.text.toString().toInt()).toString())
                      }}


        if(id == R.id.reiniciar){

            result.text = "0"
        }

         if(id == R.id.sinal){


             result.text = (result.text.toString().toInt() * -1).toString()

         }

        if(id == R.id.porcentagem){

            result.text = (result.text.toString().toDouble()/100).toString()
        }


        when(id){

            R.id.mais ->{

                opMais = result.text.toString()

                controle = "mais"

                result.text = "0"
            }

            R.id.menos ->{

                opMenos = result.text.toString()

                controle = "menos"

                result.text = "0"
            }

            R.id.multi ->{

                opMulti = result.text.toString()

                controle = "multi"

                result.text = "0"
            }

            R.id.divisao -> {

                opDiv = result.text.toString()

                controle = "divisao"

                result.text = "0"

            }

        }

        if(id == R.id.igual && controle == "mais"){

            opIgual= result.text.toString()
            result.text = (opMais.toInt()+opIgual.toInt()).toString()

        }

        if(id == R.id.igual && controle == "menos"){

            opIgual= result.text.toString()
            result.text = (opMenos.toInt()-opIgual.toInt()).toString()

        }

        if(id == R.id.igual && controle == "multi"){

            opIgual= result.text.toString()
            result.text = (opMulti.toInt()*opIgual.toInt()).toString()
        }

        if(id == R.id.igual && controle == "divisao"){

            opIgual = result.text.toString()

            if(opDiv.toInt() > opIgual.toInt() && opDiv.toInt()%opIgual.toInt() == 0){

                result.text =(opDiv.toInt()/opIgual.toInt()).toString()

            } else {

                result.text = (opDiv.toDouble() / opIgual.toDouble()).toString()

            }
        }
    }
}
