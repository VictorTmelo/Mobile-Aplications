package br.unifor.cc.planetsapp.acitivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.unifor.cc.planetsapp.R
import br.unifor.cc.planetsapp.adapter.PlanetClickListener
import br.unifor.cc.planetsapp.adapter.PlanetsAdapter
import br.unifor.cc.planetsapp.util.PlanetsUtil

class MainActivity : AppCompatActivity(), PlanetClickListener {

    private lateinit var planetsList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        planetsList = findViewById(R.id.main_recyclerview_planets_list)

        planetsList.layoutManager = LinearLayoutManager(this)

        val adapter = PlanetsAdapter(this, PlanetsUtil.getPlanets(this), this)
        planetsList.adapter = adapter

    }

    override fun onClick(view: View, position: Int) {
        val it = Intent(this, DetailActivity::class.java)
        it.putExtra("planet_position", position)
        startActivity(it)
    }
}
