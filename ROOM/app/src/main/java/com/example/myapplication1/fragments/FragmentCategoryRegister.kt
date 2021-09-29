package com.example.myapplication1.fragments


import android.app.Activity
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

import com.example.myapplication1.R
import com.example.myapplication1.entity.Category
import com.example.myapplication1.util.DataBaseUtil
import org.jetbrains.anko.doAsync


class FragmentCategoryRegister(private val userId:Int) : Fragment() {

    lateinit var registerName:EditText;

    lateinit var registerButton:Button;


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_fragment_category_register, container, false)

        registerName = view.findViewById(R.id.fragment_category_register_name);

        registerButton = view.findViewById(R.id.fragment_category_register_button);

        return view;

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        registerButton.setOnClickListener{

            if(registerName.text.isEmpty()){

                registerName.error = "Esse Campo não pode ser nulo"

            }else{

                val name  = registerName.text.toString()

                val category = Category(name = name, userId = userId);

                doAsync {

                    DataBaseUtil.getInstance(context!!).categoryDao().insert(category)

                }

                val dialog = AlertDialog.Builder(context!!)
                    .setMessage("Categoria inserida com sucesso")
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
