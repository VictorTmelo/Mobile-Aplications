package com.example.bluepocket.adapter

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Adapter
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.bluepocket.R
import com.example.bluepocket.entity.Receita
import com.example.bluepocket.util.IFragmentListener
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class ReceitaAdapter(val context: Context, val receitas: MutableList<Receita>) :
    RecyclerView.Adapter<ReceitaAdapter.ReceitaViewHolder>() {

    private val layoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReceitaViewHolder {
        val view = layoutInflater.inflate(R.layout.itens_recyclerview_list, parent, false)
        val holder = ReceitaViewHolder(context, view, receitas, this)

        return holder
    }

    override fun getItemCount(): Int {
        return receitas.size
    }

    override fun onBindViewHolder(holder: ReceitaViewHolder, position: Int) {

        val formatterCurrency: NumberFormat = NumberFormat.getCurrencyInstance()

        var formatter: SimpleDateFormat = SimpleDateFormat("dd/MM/yyyy")

        var dateString = formatter.format(Date(receitas[position].data))

        holder.listName.text = receitas[position].nome
        holder.listDate.text = dateString
        holder.listvalue.text = formatterCurrency.format(receitas[position].valor)
        holder.removeListener(position)
    }

    class ReceitaViewHolder(val context: Context, val view: View, val receitas: MutableList<Receita>, val adapter: ReceitaAdapter) : RecyclerView.ViewHolder(view) {

        private var mFragmentListener: IFragmentListener? = context as IFragmentListener

        var listName: TextView = view.findViewById(R.id.itens_textView_name)

        var listDate: TextView = view.findViewById(R.id.itens_textView_date)

        var listvalue: TextView = view.findViewById(R.id.itens_textView_value)

        var mRemoveButton: ImageButton = view.findViewById(R.id.remove_imageButton)

        fun removeListener(position: Int) {

            mRemoveButton.setOnClickListener {

                val dialog = AlertDialog.Builder(context!!)
                    .setMessage("Tem certeza que deseja remover essa Receita?")
                    .setTitle("Blue Pocket")
                    .setCancelable(false)
                    .setPositiveButton("Remover", DialogInterface.OnClickListener { dialog, id ->
                        remover(position)
                        dialog.dismiss()
                    })
                    .setNegativeButton("Cancelar", DialogInterface.OnClickListener { dialog, id ->
                        dialog.dismiss()
                    }).create()

                dialog.setOnShowListener(object: DialogInterface.OnShowListener {
                    override fun onShow(p0: DialogInterface?) {
                        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK)
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.RED)
                    }
                })

                dialog.show()
            }
        }

        fun remover(position: Int){
            val itemToRemove = receitas.get(position)

            receitas.removeAt(position)

            mFragmentListener?.onRemoveItem(position, itemToRemove)

            adapter.notifyItemRemoved(position)
            adapter.notifyItemRangeChanged(position, receitas.size)
        }
    }
}