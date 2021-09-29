package com.example.myapplication1.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.myapplication1.R
import com.example.myapplication1.dao.UserDao
import com.example.myapplication1.entity.User
import com.example.myapplication1.util.DataBaseUtil
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.doAsync

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

        val db = DataBaseUtil.getInstance(this)

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



                doAsync {

                    db.userDao().insertUser(User(name = nome.text.toString(), email = email.text.toString(), password = senha.text.toString()))

                }


                runOnUiThread {

                    val dialog = AlertDialog.Builder(this@CadastroActivity)
                        .setMessage("Cadastero feito com sucesso.")
                        .setTitle("Droid To Do")
                        .setCancelable(false)
                        .setNeutralButton("Ok") { dialog, id ->
                            dialog.dismiss()
                            finish()
                        }.create()

                    dialog.show()

                    Log.i("teste",db.userDao().findByEmail(email.text.toString()).toString())

                    /*
                    Toast.makeText(
                        this@CadastroActivity,
                        "Cadastro feito com sucesso!",
                        Toast.LENGTH_LONG
                    )
                        .show()

                     */


                }

                Log.i("teste",db.userDao().findByEmail(email.text.toString()).toString())

                    val it = Intent(this, LoginActivity::class.java)

                    startActivity(it)
                    finish()


            }



        }

    }


}
