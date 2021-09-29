package com.example.myapplication1.fragments

import android.app.Activity
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.myapplication1.R
import com.example.myapplication1.entity.Task
import com.google.firebase.database.FirebaseDatabase

class FragmentTaskRegister(private val categoryId:String) : Fragment() {

    lateinit var registerName: EditText;

    lateinit var registerButton: Button;

    private lateinit var database: FirebaseDatabase

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_task_register, container, false)

        registerName = view.findViewById(R.id.fragment_task_register_name);

        registerButton = view.findViewById(R.id.fragment_task_register_button);

        return view;

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        registerButton.setOnClickListener{

            if(registerName.text.isEmpty()){

                registerName.error = "Esse Campo não pode ser nulo"

            }else{

                val name  = registerName.text.toString()

                val task = Task(name = name, categoryId = categoryId);

                val taskRef = database.getReference("tasks")

                val taskId = taskRef.push().key;

                taskRef.child(taskId!!).setValue(task).addOnSuccessListener {

                    val dialog = AlertDialog.Builder(context!!)
                        .setMessage("Tarefa inserida com sucesso")
                        .setTitle("Droid To Do")
                        .setCancelable(false)
                        .setNeutralButton("OK", DialogInterface.OnClickListener{ dialog, id ->
                            dialog.dismiss()
                            val imm = context!!.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                            imm.hideSoftInputFromWindow(view!!.windowToken, 0)
                            activity!!.supportFragmentManager.popBackStack()
                        }).create()
                    dialog.show()

                }


            }


        }

    }


    override fun onStart() {
        super.onStart()

        database = FirebaseDatabase.getInstance();
    }

}
