package com.example.myapplication1.fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.myapplication1.R
import com.example.myapplication1.adapter.CategoryAdapter
import com.example.myapplication1.entity.Category
import com.example.myapplication1.util.FragmentListener
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FragmentCategoryList(private val userId:String) : Fragment() {

    private lateinit var recycleView:RecyclerView

    private lateinit var floatButton:FloatingActionButton

    private var fragmentListener:FragmentListener? = null;

    private lateinit var database:FirebaseDatabase;

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_fragment_category_list, container, false)

        //RECUPERAR COMPONENTES VISUAIS

        recycleView = view.findViewById(R.id.fragment_recyclerview);

        floatButton = view.findViewById(R.id.floatingActionButton);

        return view;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        floatButton.setOnClickListener {

            fragmentListener?.onFragmentClick(it)

        }

    }

        override fun onStart() {
            super.onStart()

            val categories = mutableListOf<Category>()

            val layoutManager = LinearLayoutManager(context)

            val adapter = CategoryAdapter(context!!, categories);

            recycleView.layoutManager = layoutManager;

            recycleView.adapter = adapter;

            database = FirebaseDatabase.getInstance();

            val categoryRef = database.getReference("categories");

            val query = categoryRef.orderByChild("userId").equalTo(userId).addValueEventListener(object:ValueEventListener{

                override fun onCancelled(databaseError: DatabaseError) {

                    Log.e("droidToDo",databaseError.message);
                    Log.e("droidToDo",databaseError.details);
                }

                override fun onDataChange(dataSnapshot: DataSnapshot) {

                    dataSnapshot.children.forEach {

                        val category = it.getValue(Category::class.java);

                        categories.add(category!!)

                        adapter.notifyDataSetChanged()

                    }

                }

            })

        }




    fun addFragmentListener(listener: FragmentListener){

        this.fragmentListener = listener;

    }


}
