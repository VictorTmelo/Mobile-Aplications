package br.unifor.cc.planetsapp.acitivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import br.unifor.cc.planetsapp.R
import br.unifor.cc.planetsapp.util.PlanetsUtil

class DetailActivity : AppCompatActivity() {

    private lateinit var planetImage: ImageView
    private lateinit var planetDescription: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val planet = PlanetsUtil.getPlanets(this)[intent.getIntExtra("planet_position", 0)]
        planetImage = findViewById(R.id.detail_imageview_planet)
        planetImage.setImageResource(planet.photo)

        planetDescription = findViewById(R.id.detail_textview_description)

    }
}
