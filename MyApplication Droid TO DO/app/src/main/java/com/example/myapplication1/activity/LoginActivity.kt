package com.example.myapplication1.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.myapplication1.R
import com.example.myapplication1.util.UserUtil

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var email:EditText;
    lateinit var senha:EditText
    lateinit var login:Button
    lateinit var cadastro:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        email = findViewById(R.id.email)
        senha = findViewById(R.id.password)
        login = findViewById(R.id.button_login)
        cadastro = findViewById(R.id.cadastro)

        login.setOnClickListener(this)
        cadastro.setOnClickListener(this)
    }

    override fun onClick(v: View) {

        val id = v.id

        if(id == R.id.button_login) {

            // Verificar se email foi digitado

            if (email.text.isEmpty()) {

                email.error = "Um Email deve ser digitado."

                return
            }

            // Verificar se senha foi digitada

            if (senha.text.isEmpty()) {

                senha.error = "Uma Senha deve ser digitado."

                return
            }

            // Verificar se existe usuário com esse email e senha

            val usermail = email.text.toString()

            val password = senha.text.toString()

            val user = UserUtil.getUserByEmail(usermail)

            if (user != null) {

                if (user.password == password) {

                    val it = Intent(this, MainActivity::class.java)

                    startActivity(it)
                    finish()


                } else {

                    Toast.makeText(
                        this,
                        "email ou senha invalido, tente novamente",
                        Toast.LENGTH_LONG
                    )
                        .show()
                }

            } else {

                Toast.makeText(this, "email ou senha invalido, tente novamente", Toast.LENGTH_LONG)
                    .show()

            }

        }


        if(id == R.id.cadastro){

            val it = Intent(this, CadastroActivity::class.java)

            startActivity(it)

        }
    }
}
