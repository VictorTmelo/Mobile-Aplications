package com.example.myapplication1.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.example.myapplication1.R
import com.example.myapplication1.entity.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class CadastroActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var nome:EditText
    lateinit var email:EditText
    lateinit var senha:EditText
    lateinit var cadastro:Button

    private lateinit var auth:FirebaseAuth
    private lateinit var database:FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

         nome = findViewById(R.id.editText)
         email = findViewById(R.id.editText2)
         senha = findViewById(R.id.editText3)
         cadastro= findViewById(R.id.button)

        cadastro.setOnClickListener(this)


    }

    override fun onStart() {
        super.onStart()

        auth = FirebaseAuth.getInstance();

        database = FirebaseDatabase.getInstance();

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


                auth.createUserWithEmailAndPassword(email.text.toString(),senha.text.toString()).addOnCompleteListener{

                    if(it.isSuccessful){

                        Log.i("droid to do", it.result.toString())

                        val user = User(nome.text.toString(),email.text.toString(),senha.text.toString());

                        val userRef = database.getReference("users")

                        userRef.child(auth.currentUser!!.uid).setValue(user)

                        val dialog = AlertDialog.Builder(this@CadastroActivity)
                            .setMessage("Cadastero feito com sucesso.")
                            .setTitle("Droid To Do")
                            .setCancelable(false)
                            .setNeutralButton("Ok") { dialog, id ->
                                dialog.dismiss()
                                finish()
                            }.create()

                        dialog.show()

                    }else{

                        Log.i("droid to do", it.exception!!.message)

                        val dialog = AlertDialog.Builder(this@CadastroActivity)
                            .setMessage(it.exception!!.message)
                            .setTitle("Droid To Do")
                            .setCancelable(false)
                            .setNeutralButton("Ok") { dialog, id ->
                                dialog.dismiss()
                                finish()
                            }.create()

                        dialog.show()

                    }
                }



                val it = Intent(this, LoginActivity::class.java)

                startActivity(it)
                finish()


            }



        }

    }


}
