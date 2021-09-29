package com.example.bluepocket.adapter

import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.bluepocket.R
import com.example.bluepocket.entity.Despesa
import com.example.bluepocket.entity.Receita
import com.example.bluepocket.util.IFragmentListener
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class DespesaAdapter (val context: Context, val despesas: MutableList<Despesa>): RecyclerView.Adapter<DespesaAdapter.DespesaViewHolder>(){

    private val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DespesaViewHolder {
        val view = layoutInflater.inflate(R.layout.itens_recyclerview_list, parent, false)
        val holder = DespesaViewHolder(context, view, despesas, this)

        return holder
    }

    override fun getItemCount(): Int {
        return despesas.size
    }

    override fun onBindViewHolder(holder: DespesaViewHolder, position: Int) {
        val formatterCurrency: NumberFormat = NumberFormat.getCurrencyInstance()
        var formatter: SimpleDateFormat = SimpleDateFormat("dd/MM/yyyy")

        var dateString = formatter.format(Date(despesas[position].data))

        holder.listName.text = despesas[position].nome
        holder.listDate.text = dateString
        holder.listvalue.text = formatterCurrency.format(despesas[position].valor)
        holder.removeListener(position)
    }

    class DespesaViewHolder(val context: Context, view: View, val despesas: MutableList<Despesa>, val adapter: DespesaAdapter): RecyclerView.ViewHolder(view){
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
            val itemToRemove = despesas.get(position)

            despesas.removeAt(position)

            mFragmentListener?.onRemoveItem(position, itemToRemove)

            adapter.notifyItemRemoved(position)
            adapter.notifyItemRangeChanged(position, despesas.size)
        }
    }
}