package com.example.bluepocket.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.bluepocket.R
import com.example.bluepocket.entity.Despesa
import com.example.bluepocket.entity.Receita
import com.example.bluepocket.fragments.FragmentRegister
import com.example.bluepocket.fragments.FragmentTypeList
import com.example.bluepocket.fragments.FragmentTypeRegister
import com.example.bluepocket.util.IFragmentListener
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlin.contracts.contract

class NavigationActivity : AppCompatActivity(), IFragmentListener {
    lateinit var userNameTextView: TextView

    lateinit var drawerLayot: DrawerLayout

    lateinit var navigationView: NavigationView

    lateinit var view: View

    lateinit var mainAuth: FirebaseAuth

    var userControle: Int = 0

    var mTypeId = ""

    lateinit var mainDatabase: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        drawerLayot = findViewById(R.id.drawer_layout_navigation)
        navigationView = findViewById(R.id.navigation_view)

        view = navigationView.getHeaderView(0)

        userNameTextView = view.findViewById(R.id.userName_textView)

        mainAuth = FirebaseAuth.getInstance()

        userNameTextView.text = mainAuth.currentUser!!.displayName

        userControle = intent.getIntExtra("controle", 0)

        mainDatabase = FirebaseDatabase.getInstance()

        var fragmentTypeList = FragmentTypeList(mainAuth.currentUser!!.uid, userControle)
        fragmentTypeList.addFragmentListener(this)

        supportFragmentManager.beginTransaction()
            .replace(R.id.navigation_framelayout_container, fragmentTypeList)
            .commit()

        navigationView.bringToFront()

        navigationView.setNavigationItemSelectedListener( object: NavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when(item.itemId){
                    R.id.receita_item ->{

                        fragmentTypeList = FragmentTypeList(mainAuth.currentUser!!.uid, 1)
                        fragmentTypeList.addFragmentListener(this@NavigationActivity)

                        supportFragmentManager.beginTransaction()
                            .replace(R.id.navigation_framelayout_container, fragmentTypeList)
                            .commit()
                    }

                    R.id.despesa_item->{

                        fragmentTypeList = FragmentTypeList(mainAuth.currentUser!!.uid, 2)
                        fragmentTypeList.addFragmentListener(this@NavigationActivity)

                        supportFragmentManager.beginTransaction()
                            .replace(R.id.navigation_framelayout_container, fragmentTypeList)
                            .commit()
                    }
                }

                drawerLayot.closeDrawer(GravityCompat.START)

                return true
            }
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()

        if(supportFragmentManager.backStackEntryCount == 0){
            finish()
        }
    }

    override fun onFragmentClick(view: View) {

        when (view.id) {
            R.id.fragment_type_floatingButton_add -> {

                val fragmentTypeRegister = FragmentTypeRegister(mainAuth.currentUser!!.uid, userControle)

                supportFragmentManager.beginTransaction()
                    .replace(R.id.navigation_framelayout_container, fragmentTypeRegister)
                    .addToBackStack(null)
                    .commit()
            }

            R.id.fragment_floatingButton_add ->{
                val fragmentRegister = FragmentRegister(mTypeId, userControle)

                supportFragmentManager.beginTransaction()
                    .replace(R.id.navigation_framelayout_container, fragmentRegister)
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

    override fun onRemoveItem(position: Int, item: Any) {
        val receitaRef = mainDatabase.getReference("receitas")
        val despesRef = mainDatabase.getReference("despesas")

        if(item is Receita){
            receitaRef.child(item.id).removeValue()
        }else if(item is Despesa){
            despesRef.child(item.id).removeValue()
        }
    }

    fun setmTypeId(id: String){
        mTypeId = id
    }
}
