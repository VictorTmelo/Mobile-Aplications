package br.unifor.cc.planetsapp.util

import android.content.Context
import androidx.core.content.ContextCompat
import br.unifor.cc.planetsapp.R
import br.unifor.cc.planetsapp.model.Planet

object PlanetsUtil {

    fun getPlanets(context: Context): List<Planet> {

        val planets = listOf(
            Planet("Mercurio", R.drawable.mercurio,
                ContextCompat.getColor(context, R.color.mercurio_light_background),
                ContextCompat.getColor(context, R.color.mercurio_dark_background)),
            Planet("Venus", R.drawable.venus,
                ContextCompat.getColor(context, R.color.venus_light_background),
                ContextCompat.getColor(context, R.color.venus_dark_background)),
            Planet("Terra", R.drawable.terra,
                ContextCompat.getColor(context, R.color.terra_light_backgroung),
                ContextCompat.getColor(context, R.color.terra_dark_backgroung)),
            Planet("Marte", R.drawable.marte,
                ContextCompat.getColor(context, R.color.marte_light_background),
                ContextCompat.getColor(context, R.color.marte_dark_background)),
            Planet("Jupiter", R.drawable.jupiter,
                ContextCompat.getColor(context, R.color.jupiter_light_background),
                ContextCompat.getColor(context, R.color.jupiter_dark_background)),
            Planet("Saturno", R.drawable.saturno,
                ContextCompat.getColor(context, R.color.saturno_light_background),
                ContextCompat.getColor(context, R.color.saturno_dark_background))
        )

        return planets
    }
//
//    fun getPlanetByName(name:String):Planet?{
//
//        var planet:Planet? = null
//        this.planets.forEach {
//            if (it.name.equals(name)){
//                planet = it
//            }
//        }
//
//        return planet
//    }

}