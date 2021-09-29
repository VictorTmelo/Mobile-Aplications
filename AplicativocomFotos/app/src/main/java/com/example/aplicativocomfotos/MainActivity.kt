package com.example.aplicativocomfotos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button_main).setOnClickListener{

            val random = Math.random();

            if(random > 0.5){

                val it = Intent(this, ChuckNorrisActivity::class.java);

                startActivity(it);


            }else{

                val it2 = Intent(this, VanDammeActivity::class.java);

                startActivity(it2);



            }




        }

    }
}
