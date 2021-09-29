package com.example.myapplication1.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.myapplication1.R
import com.example.myapplication1.util.UserUtil
import kotlinx.android.synthetic.main.activity_login.*

class CadastroActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var nome:EditText
    lateinit var email:EditText
    lateinit var senha:EditText
    lateinit var cadastro:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

         nome = findViewById(R.id.editText)
         email = findViewById(R.id.editText2)
         senha = findViewById(R.id.editText3)
         cadastro= findViewById(R.id.button)

        cadastro.setOnClickListener(this)



    }


    override fun onClick(v: View) {

        val id = v.id

        if(id == R.id.button){


            // Verificar se nome foi digitado

            if (nome.text.isEmpty()) {

                nome.error = "Um Nome deve ser digitado."

                return
            }

            // Verificar se email foi digitada

            if (email.text.isEmpty()) {

                email.error = "Um email deve ser digitado."

                return
            }

            if (senha.text.isEmpty()) {

                senha.error = "Um email deve ser digitado."

                return
            }

            if(!(nome.text.isEmpty() && senha.text.isEmpty() && email.text.isEmpty() )){

                val user = UserUtil

                user.addUser(nome.text.toString(), email.text.toString(), senha.text.toString())


                val it = Intent(this, LoginActivity::class.java)

                startActivity(it)
                finish()


            }



        }

    }


}
