package com.example.bluepocket.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.bluepocket.R
import com.example.bluepocket.entity.Despesa
import com.example.bluepocket.entity.Receita
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var userNameTextView: TextView

    lateinit var drawerLayot: DrawerLayout

    lateinit var navigationView: NavigationView

    lateinit var receitaButton: Button
    lateinit var despesaButton: Button

    lateinit var demonstrativoButton: Button

    lateinit var saldoTextView: TextView

    lateinit var receitaTextView: TextView
    lateinit var despesaTextView: TextView

    lateinit var mChart: LineChart

    lateinit var emptyChart: TextView

    lateinit var mNumberPicker: com.shawnlin.numberpicker.NumberPicker

    lateinit var view: View

    lateinit var mainAuth: FirebaseAuth

    lateinit var mainDatabase: FirebaseDatabase

    lateinit var calendar: Calendar

    var mes: Int = 0

    var receita: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayot = findViewById(R.id.drawer_layout)

        navigationView = findViewById(R.id.navigation_view)

        view = navigationView.getHeaderView(0)
        userNameTextView = view.findViewById(R.id.userName_textView)
        receitaButton = findViewById(R.id.receitas_button)
        despesaButton = findViewById(R.id.despesas_button)
        demonstrativoButton =  findViewById(R.id.demonstrativo_button)

        saldoTextView = findViewById(R.id.saldo_textView)

        receitaTextView = findViewById(R.id.receitas_textView)
        despesaTextView = findViewById(R.id.despesas_textView)

        emptyChart = findViewById(R.id.emptyChart_textView)

        mNumberPicker = findViewById(R.id.number_picker)

        mNumberPicker.minValue = 1

        mNumberPicker.maxValue = 12

        calendar = Calendar.getInstance()

        mes = calendar.get(Calendar.MONTH) + 1

        mNumberPicker.value = mes

        mNumberPicker.setOnValueChangedListener{ picker, oldVal, newVal ->

            drawChart(newVal)
        }

        mChart = findViewById(R.id.line_chart)

        mChart.visibility = View.GONE

        mChart.axisRight.isEnabled = false
        mChart.setTouchEnabled(true)
        mChart.setDrawGridBackground(false)
        mChart.setDragEnabled(true)
        mChart.setScaleEnabled(true)
        mChart.setPinchZoom(true)

        //mChart.xAxis.axisMaximum = 30f

        val intent = Intent(this, NavigationActivity::class.java)

        navigationView.bringToFront()

        navigationView.setNavigationItemSelectedListener( object: NavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when(item.itemId){
                    R.id.receita_item ->{
                        intent.putExtra("controle", 1)

                        startActivity(intent)
                    }

                    R.id.despesa_item->{
                        intent.putExtra("controle", 2)

                        startActivity(intent)
                    }
                }

                drawerLayot.closeDrawer(GravityCompat.START)

                return true
            }
        })

        receitaButton.setOnClickListener(this)
        despesaButton.setOnClickListener(this)
        demonstrativoButton.setOnClickListener(this)

        mainDatabase = FirebaseDatabase.getInstance()

        mainAuth = FirebaseAuth.getInstance()

        userNameTextView.text = mainAuth.currentUser!!.displayName
    }

    override fun onResume() {
        super.onResume()

        dashboardUpdate()

        drawChart(mes)
    }

    fun drawChart(mes: Int){
        var receitas = mutableListOf<Entry>()
        var despesas = mutableListOf<Entry?>()

        var dataSets = mutableListOf<ILineDataSet>()

        var formatterDia: SimpleDateFormat = SimpleDateFormat("dd")
        var formatterMes: SimpleDateFormat = SimpleDateFormat("MM")

        val receitaRef = mainDatabase.getReference("receitas")
        val query = receitaRef.orderByChild("userID").equalTo(mainAuth.currentUser!!.uid)

        val despesaRef = mainDatabase.getReference("despesas")
        val query2 = despesaRef.orderByChild("userID").equalTo(mainAuth.currentUser!!.uid)

        query.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(databseError: DatabaseError) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                dataSnapshot.children.forEach {

                    val receita = it.getValue(Receita::class.java)

                    var diaString = formatterDia.format(Date(receita!!.data))

                    var mesString = formatterMes.format(Date(receita.data))

                    if(mesString.toInt() == mes){
                        receitas.add(Entry(diaString.toFloat(), receita.valor.toFloat()))

                        mChart.visibility = View.VISIBLE
                        emptyChart.visibility = View.GONE
                    }

                    if(receitas.size == 0 && despesas.size == 0){
                        emptyChart.visibility = View.VISIBLE

                        mChart.visibility = View.GONE
                    }

                    receitas.sortBy{it.x}
                }

                var setOfReceitas: LineDataSet = LineDataSet(receitas, "Receitas")

                setOfReceitas.setCircleColor(Color.GREEN)

                setOfReceitas.fillAlpha = 110

                setOfReceitas.setColor(Color.GREEN)

                setOfReceitas.lineWidth = 3f

                setOfReceitas.enableDashedHighlightLine(10f, 5f, 0f)

                dataSets.add(setOfReceitas)

                Log.i("BluePocket", dataSets.toString())

                var data: LineData = LineData(dataSets)

                mChart.data = data

                mChart.axisLeft.axisMinimum = 0f
                mChart.xAxis.axisMinimum = 0f
                mChart.xAxis.axisMaximum = 32f
                mChart.description = null

                mChart!!.animateX(1500)

                query2.addValueEventListener(object : ValueEventListener {
                    override fun onCancelled(databseError: DatabaseError) {

                    }

                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        despesas.removeAll(despesas)

                        dataSnapshot.children.forEach {

                            val despesa = it.getValue(Despesa::class.java)

                            var diaString = formatterDia.format(Date(despesa!!.data))

                            var mesString = formatterMes.format(Date(despesa.data))

                            if(mesString.toInt() == mes){
                                despesas.add(Entry(diaString.toFloat(), despesa.valor.toFloat()))

                                mChart.visibility = View.VISIBLE
                                emptyChart.visibility = View.GONE
                            }

                            if(receitas.size == 0 && despesas.size == 0){
                                emptyChart.visibility = View.VISIBLE

                                mChart.visibility = View.GONE
                            }

                            despesas.sortBy{it!!.x}
                        }

                        var setOfDespesas: LineDataSet = LineDataSet(despesas, "Despesas")

                        setOfDespesas.setCircleColor(Color.RED)

                        setOfDespesas.fillAlpha = 110

                        setOfDespesas.setColor(Color.RED)

                        setOfDespesas.lineWidth = 3f

                        setOfDespesas.enableDashedHighlightLine(10f, 5f, 0f)

                        dataSets.add(setOfDespesas)

                        var data: LineData = LineData(dataSets)

                        mChart.data = data

                        mChart.axisLeft.axisMinimum = 0f
                        mChart.xAxis.axisMinimum = 0f

                        mChart.animateX(1500)
                    }
                })
            }
        })
    }

    fun dashboardUpdate(){

        var acumulaReceitaValor: Double = 0.0

        var acumulaDespesaValor: Double = 0.0

        val formatter: NumberFormat = NumberFormat.getCurrencyInstance()

        val receitaRef = mainDatabase.getReference("receitas")

        val despesaRef = mainDatabase.getReference("despesas")

        val query = receitaRef.orderByChild("userID").equalTo(mainAuth.currentUser!!.uid)

        val query2 = despesaRef.orderByChild("userID").equalTo(mainAuth.currentUser!!.uid)

        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(databseError: DatabaseError) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                acumulaReceitaValor = 0.0


                dataSnapshot.children.forEach {

                    val receita = it.getValue(Receita::class.java)

                    acumulaReceitaValor += receita!!.valor
                }

                val formattedNumber = formatter.format(acumulaReceitaValor)

                receitaTextView.text = formattedNumber

                saldoTextView.text = acumulaReceitaValor.toString()

                query2.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onCancelled(databseError: DatabaseError) {

                    }

                    override fun onDataChange(dataSnapshot: DataSnapshot) {

                        acumulaDespesaValor = 0.0

                        dataSnapshot.children.forEach {
                            val despesa = it.getValue(Despesa::class.java)

                            acumulaDespesaValor += despesa!!.valor
                        }

                        var formattedNumber = formatter.format(acumulaDespesaValor)

                        val despesaAnterior = despesaTextView.text

                        despesaTextView.text = formattedNumber

                        var resumo: Double = 0.0

                        if(saldoTextView.text.contains("R") && despesaAnterior.contains("R")){
                            var despesaAnteriorString = despesaAnterior.substring(3, despesaAnterior.toString().length)
                            despesaAnteriorString = despesaAnteriorString.replace(",", ".")

                            var despesaAnteriorDouble = despesaAnteriorString.toDouble()

                            var saldoTextViewString = saldoTextView.text.substring(4, saldoTextView.text.toString().length)
                            saldoTextViewString = saldoTextViewString.replace(",", ".")

                            var saldoTextViewDouble = saldoTextViewString.toDouble()

                            resumo = saldoTextViewDouble - acumulaDespesaValor + despesaAnteriorDouble

                        }else{
                            resumo = saldoTextView.text.toString().toDouble() - acumulaDespesaValor
                        }

                        formattedNumber = formatter.format(resumo)

                        saldoTextView.text = formattedNumber
                    }
                })
            }
        })
    }

    override fun onClick(v: View?) {
        val intent = Intent(this, NavigationActivity::class.java)

        when (v?.id) {
            R.id.receitas_button -> {
                intent.putExtra("controle", 1)

                startActivity(intent)
            }

            R.id.despesas_button -> {
                intent.putExtra("controle", 2)

                startActivity(intent)
            }

            R.id.demonstrativo_button ->{
                val intent2 = Intent(this, DemonstrativoActivity::class.java)

                startActivity(intent2)
            }
        }
    }
}
