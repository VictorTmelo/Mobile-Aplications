package br.unifor.cc.planetsapp.acitivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.unifor.cc.planetsapp.R
import br.unifor.cc.planetsapp.adapter.PlanetsAdapter
import br.unifor.cc.planetsapp.util.PlanetsUtil

class MainActivity : AppCompatActivity() {

    private lateinit var planetsList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        planetsList = findViewById(R.id.main_recyclerview_planets_list)

        planetsList.layoutManager = LinearLayoutManager(this)

        val adapter = PlanetsAdapter(this, PlanetsUtil.getPlanets(this))
        planetsList.adapter = adapter

    }
}
