package com.example.travisapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nomeFalso.setOnClickListener(this)
        nomeReal.setOnClickListener(this)

    }

    override fun onClick(view: View) {

        val id = view.id

        if(id == R.id.nomeFalso){

            val it = Intent(this, PerdeuActivity::class.java)

            startActivity(it);

        }


        if(id == R.id.nomeReal){

            val it = Intent(this, Main2Activity::class.java)

            startActivity(it);

        }



    }








}
