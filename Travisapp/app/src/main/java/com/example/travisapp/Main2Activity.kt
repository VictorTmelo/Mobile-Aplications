package com.example.travisapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        tenisFalso.setOnClickListener(this)
        tenisReal.setOnClickListener(this)

    }

    override fun onClick(view: View) {

        val id = view.id

        if(id == R.id.tenisFalso){

            val it = Intent(this, PerdeuActivity::class.java)

            startActivity(it);

        }


        if(id == R.id.tenisReal){

            val it = Intent(this, Main3Activity::class.java)

            startActivity(it);

        }

    }
}
