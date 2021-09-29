package com.example.myapplication1.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication1.R
import com.example.myapplication1.entity.Category


class ListAdapter(val context:Context, val categories: List<Category>): RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    private val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {

        val view = layoutInflater.inflate(R.layout.item_recyclerview, parent, false);

        val holder = ListViewHolder(view)

        return holder;

    }

    override fun getItemCount(): Int {

        return categories.size

    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {

        holder.listName.text = categories[position].name

    }



    class ListViewHolder(view:View):RecyclerView.ViewHolder(view){

        var listName:TextView =  view.findViewById(R.id.item)



    }
}