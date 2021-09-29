package com.example.teladascores.activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import android.widget.TextView
import com.example.teladascores.R

class MainActivity : AppCompatActivity(), SeekBar.OnSeekBarChangeListener {

    lateinit var viewColor:View
    lateinit var seekbarA:SeekBar
    lateinit var seekbarRed:SeekBar
    lateinit var seekbarGreen:SeekBar
    lateinit var seekbarBlue:SeekBar
    lateinit var aTextView: TextView
    lateinit var redTextView: TextView
    lateinit var greenTextView: TextView
    lateinit var blueTextView:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewColor = findViewById(R.id.main_view_color)
        seekbarA = findViewById(R.id.main_seekbarA)
        seekbarRed = findViewById(R.id.main_seekbarRed)
        seekbarGreen = findViewById(R.id.main_seekbarGreen)
        seekbarBlue = findViewById(R.id.main_seekbarBlue)
        aTextView = findViewById(R.id.main_textview_a)
        redTextView = findViewById(R.id.main_textview_red)
        greenTextView = findViewById(R.id.main_textview_green)
        blueTextView = findViewById(R.id.main_textview_blue)

        seekbarA.setOnSeekBarChangeListener(this)
        seekbarA.max = 255;

        seekbarRed.setOnSeekBarChangeListener(this)
        seekbarRed.max = 255;

        seekbarGreen.setOnSeekBarChangeListener(this)
        seekbarGreen.max = 255;

        seekbarBlue.setOnSeekBarChangeListener(this)
        seekbarBlue.max = 255;


    }


    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {


        when(seekBar?.id) {

            R.id.main_seekbarA ->{

                aTextView.text = progress.toString()

            }

            R.id.main_seekbarRed ->{

                redTextView.text = progress.toString()

            }

            R.id.main_seekbarGreen ->{

                greenTextView.text = progress.toString()

            }

            R.id.main_seekbarBlue ->{

                blueTextView.text = progress.toString()

            }

        }


        viewColor.setBackgroundColor(Color.argb(
            aTextView.text.toString().toInt(),
            redTextView.text.toString().toInt(),
            greenTextView.text.toString().toInt(),
            blueTextView.text.toString().toInt()))
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {

    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {

    }


}



