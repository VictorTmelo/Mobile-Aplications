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
import com.example.myapplication1.adapter.TaskAdapter
import com.example.myapplication1.entity.Task
import com.example.myapplication1.util.FragmentListener
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FragmentTaskList(private val categoryId:String) : Fragment() {

    private lateinit var recycleView: RecyclerView

    private lateinit var floatButton: FloatingActionButton

    private var fragmentListener: FragmentListener? = null;

    private lateinit var database: FirebaseDatabase;


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_task_list, container, false)

        //RECUPERAR COMPONENTES VISUAIS

        recycleView = view.findViewById(R.id.task_recyclerview);

        floatButton = view.findViewById(R.id.floatingActionButtonList);

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

        val tasks = mutableListOf<Task>()

        val layoutManager = LinearLayoutManager(context)

        val adapter = TaskAdapter(context!!, tasks);

        recycleView.layoutManager = layoutManager;

        recycleView.adapter = adapter;

        database = FirebaseDatabase.getInstance();

        val taskRef = database.getReference("tasks");

        val query = taskRef.orderByChild("categoryId").equalTo(categoryId).addValueEventListener(object:
            ValueEventListener {

            override fun onCancelled(databaseError: DatabaseError) {

                Log.e("droidToDo",databaseError.message);
                Log.e("droidToDo",databaseError.details);
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                dataSnapshot.children.forEach {

                    val task = it.getValue(Task::class.java);

                    tasks.add(task!!)

                    adapter.notifyDataSetChanged()

                }

            }

        })

    }




    fun addFragmentListener(listener: FragmentListener){

        this.fragmentListener = listener;

    }


}
