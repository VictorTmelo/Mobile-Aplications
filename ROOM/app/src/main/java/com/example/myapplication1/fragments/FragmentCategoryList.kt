package com.example.myapplication1.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.myapplication1.R
import com.example.myapplication1.adapter.ListAdapter
import com.example.myapplication1.entity.Category
import com.example.myapplication1.util.DataBaseUtil
import com.example.myapplication1.util.FragmentListener
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.support.v4.runOnUiThread

class FragmentCategoryList(private val userId:Int) : Fragment() {

    private lateinit var recycleView:RecyclerView

    private lateinit var floatButton:FloatingActionButton

    private var fragmentListener:FragmentListener? = null;

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

            doAsync {

                val layoutManager = LinearLayoutManager(context)

                val userCategories =
                    DataBaseUtil.getInstance(context!!).userDao().findUserCategories(userId)

                val adapter = ListAdapter(context!!, userCategories.categories);

                runOnUiThread {

                    recycleView.layoutManager = layoutManager;

                    recycleView.adapter = adapter;

                }

            }


        }

    fun addFragmentListener(listener: FragmentListener){

        this.fragmentListener = listener;

    }


}
