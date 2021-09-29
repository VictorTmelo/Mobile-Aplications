package com.example.myapplication1.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication1.R
import com.example.myapplication1.activity.MainActivity
import com.example.myapplication1.entity.Category
import com.example.myapplication1.fragments.FragmentCategoryList
import com.example.myapplication1.fragments.FragmentTaskList


class CategoryAdapter(val context:Context, val categories: List<Category>): RecyclerView.Adapter<CategoryAdapter.ListViewHolder>() {

    private val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {

        val view = layoutInflater.inflate(R.layout.item_recyclerview, parent, false);

        val holder = ListViewHolder(context,categories,view);

        return holder;

    }

    override fun getItemCount(): Int {

        return categories.size

    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {

        holder.listName.text = categories[position].name

        holder.vai();

    }



    class ListViewHolder(val context:Context, val categories: List<Category>, view:View):RecyclerView.ViewHolder(view), View.OnClickListener{

        var listName:TextView =  view.findViewById(R.id.item)

        var view = view;

        fun vai(){

            view.setOnClickListener(this);

        }

        override fun onClick(v: View?) {

            val fragmentTaskList = FragmentTaskList(categories[position].categoryId)

            Log.i("droidtodo",fragmentTaskList.toString());

            if (context is MainActivity) {
                fragmentTaskList.addFragmentListener(context)

                context.categoryId = categories[position].categoryId

                context.supportFragmentManager.beginTransaction()
                    .replace(R.id.framelayout_container, fragmentTaskList)
                    .addToBackStack(null)
                    .commit()
            }
        }

    }


}