package com.example.myapplication1.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.myapplication1.R
import com.example.myapplication1.entity.User
import com.example.myapplication1.util.DataBaseUtil
import org.jetbrains.anko.doAsync

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

        val db = DataBaseUtil.getInstance(this)

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

            doAsync {

                user = db.userDao().findByEmail(usermail)


                    if (user != null) {

                        if (user!!.password == password) {

                            Log.i("teste",db.userDao().findByEmail(usermail).toString())

                            val it = Intent(this@LoginActivity, MainActivity::class.java)

                            it.putExtra("user_id", user!!.id);

                            startActivity(it)
                            finish()


                        } else {

                            runOnUiThread{

                                val dialog = AlertDialog.Builder(this@LoginActivity)
                                    .setMessage("email ou senha invalido, tente novamente.")
                                    .setTitle("Droid To Do")
                                    .setCancelable(false)
                                    .setNeutralButton("Ok") { dialog, id ->
                                        dialog.dismiss()
                                    }.create()

                                dialog.show()

                            }

                            /*
                            Toast.makeText(
                                this@LoginActivity,
                                "email ou senha invalido, tente novamente",
                                Toast.LENGTH_LONG
                            )
                                .show()

                             */


                        }

                    } else {

                        runOnUiThread{

                            val dialog = AlertDialog.Builder(this@LoginActivity)
                                .setMessage("email ou senha invalido, tente novamente.")
                                .setTitle("Droid To Do")
                                .setCancelable(false)
                                .setNeutralButton("Ok") { dialog, id ->
                                    dialog.dismiss()
                                }.create()

                            dialog.show()

                        }

                        /*
                        Toast.makeText(
                            this@LoginActivity,
                            "email ou senha invalido, tente novamente",
                            Toast.LENGTH_LONG
                        )
                            .show()

                         */


                    }



            }

        }

        if(id == R.id.cadastro){

            val it = Intent(this, CadastroActivity::class.java)

            startActivity(it)

        }
    }
}
