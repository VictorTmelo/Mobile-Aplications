package com.example.travisapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main3.*

class Main3Activity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        filhaFalso.setOnClickListener(this)
        filhaReal.setOnClickListener(this)

    }

    override fun onClick(view: View) {

        val id = view.id

        if(id == R.id.filhaFalso){

            val it = Intent(this, PerdeuActivity::class.java)

            startActivity(it);

        }


        if(id == R.id.filhaReal){

            val it = Intent(this, VenceuActivity::class.java)

            startActivity(it);

        }
    }
}


