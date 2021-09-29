package com.example.myapplication1.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication1.R
import com.example.myapplication1.adapter.ListAdapter
import com.example.myapplication1.entity.Category
import com.example.myapplication1.fragments.FragmentCategoryList
import com.example.myapplication1.fragments.FragmentCategoryRegister
import com.example.myapplication1.util.FragmentListener

class MainActivity : AppCompatActivity(), FragmentListener {

    private var userId:Int = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userId = intent.getIntExtra("user_id", 0);

        val fragmentCategoryList = FragmentCategoryList(userId)

        fragmentCategoryList.addFragmentListener(this);

        supportFragmentManager.beginTransaction()
            .replace(R.id.framelayout_container, fragmentCategoryList)
            .addToBackStack(null)
            .commit()


    }

    override fun onFragmentClick(view: View) {


        when(view.id){

            R.id.floatingActionButton ->{

                val fragmentCategoryRegister = FragmentCategoryRegister(userId);

                supportFragmentManager.beginTransaction()
                    .replace(R.id.framelayout_container, fragmentCategoryRegister)
                    .addToBackStack(null)
                    .commit()

            }

        }


    }
}
