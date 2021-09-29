package com.example.myapplication1.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.myapplication1.R
import com.example.myapplication1.fragments.FragmentCategoryList
import com.example.myapplication1.fragments.FragmentCategoryRegister
import com.example.myapplication1.fragments.FragmentTaskRegister
import com.example.myapplication1.util.FragmentListener
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity(), FragmentListener {

    private lateinit var auth:FirebaseAuth

    var categoryId:String = "";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        val fragmentCategoryList = FragmentCategoryList(auth.currentUser!!.uid)

        fragmentCategoryList.addFragmentListener(this);

        supportFragmentManager.beginTransaction()
            .replace(R.id.framelayout_container, fragmentCategoryList)
            .addToBackStack(null)
            .commit()


    }


    override fun onFragmentClick(view: View) {

        when(view.id){

            R.id.floatingActionButton ->{

                val fragmentCategoryRegister = FragmentCategoryRegister(auth.currentUser!!.uid );

                supportFragmentManager.beginTransaction()
                    .replace(R.id.framelayout_container, fragmentCategoryRegister)
                    .addToBackStack(null)
                    .commit()

            }


            R.id.floatingActionButtonList ->{

                val fragmentTaskRegister = FragmentTaskRegister(categoryId);

                supportFragmentManager.beginTransaction()
                    .replace(R.id.framelayout_container, fragmentTaskRegister)
                    .addToBackStack(null)
                    .commit()

            }

        }


    }

}
