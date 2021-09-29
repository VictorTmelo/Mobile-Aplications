package com.example.myapplication1.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.myapplication1.R
import com.example.myapplication1.entity.User
import com.google.firebase.auth.FirebaseAuth
import org.jetbrains.anko.doAsync

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var email:EditText;
    lateinit var senha:EditText
    lateinit var login:Button
    lateinit var cadastro:TextView

    private lateinit var auth:FirebaseAuth

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


    override fun onStart() {
        super.onStart()

        auth = FirebaseAuth.getInstance();
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

            var user: User? = null

            auth.signInWithEmailAndPassword(usermail,password).addOnCompleteListener{

                if(it.isSuccessful){

                    val it = Intent(this, MainActivity::class.java)

                    startActivity(it)

                }else{

                    val dialog = AlertDialog.Builder(this@LoginActivity)
                        .setMessage("email ou senha invalido, tente novamente.")
                        .setTitle("Droid To Do")
                        .setCancelable(false)
                        .setNeutralButton("Ok") { dialog, id ->
                            dialog.dismiss()
                        }.create()

                    dialog.show()

                }
            }




        }

        if(id == R.id.cadastro){

            val it = Intent(this, CadastroActivity::class.java)

            startActivity(it)

        }
    }
}
