package br.unifor.cc.planetsapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import br.unifor.cc.planetsapp.R
import br.unifor.cc.planetsapp.acitivity.MainActivity
import br.unifor.cc.planetsapp.model.Planet

class PlanetsAdapter(val context:Context, val planets:List<Planet>, val listener: MainActivity):RecyclerView.Adapter<PlanetsAdapter.PlanetViewHolder>() {

    private val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanetViewHolder { //Criação do card.
        val view = layoutInflater.inflate(R.layout.item_planet, parent, false)
        return PlanetViewHolder(view, context, listener)
    }

    override fun getItemCount(): Int {
        return this.planets.size
    }

    override fun onBindViewHolder(holder: PlanetViewHolder, position: Int) {
        holder.name.text = planets[position].name
        holder.photo.setImageResource(planets[position].photo)

        holder.cardview.setBackgroundColor(planets[position].lightBackgroud)
        holder.name.setBackgroundColor(planets[position].darkBackground)
    }

    class PlanetViewHolder(view: View, context: Context, listener: PlanetClickListener):RecyclerView.ViewHolder(view){
        val cardview:CardView = view.findViewById(R.id.item_planet_cardview)
        val name:TextView = view.findViewById(R.id.item_planet_name)
        val photo:ImageView  = view.findViewById(R.id.item_planet_image)

        init {
            view.setOnClickListener{
                listener.onClick(it, adapterPosition)
            }
        }
    }

}