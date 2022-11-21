package com.example.sql_onactivitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sql_onactivitys.data.MyAdapter
import com.example.sql_onactivitys.data.MyPhBookManager
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val dbManager = MyPhBookManager(this)
    private val myAdapter = MyAdapter(ArrayList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        goToEditActivities()
        dbManager.openBD()
        initRecView()
        checkEmptyList()
    }

    private fun goToEditActivities() {
        val createView = findViewById<FloatingActionButton>(R.id.btn_create)
        createView.setOnClickListener {
            Toast.makeText(this, "Переход", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, EditActivity::class.java))
        }
    }

    private fun initRecView() {
        val rcView = findViewById<RecyclerView>(R.id.recView)
        rcView.layoutManager = LinearLayoutManager(this)
        rcView.adapter = myAdapter

    }
    private fun fillAdapter() {
        myAdapter.updateAdapter(dbManager.readDB())
    }
    private fun checkEmptyList() {
        val tvEmpty = findViewById<TextView>(R.id.tView_noElements)
        if(myAdapter.listArray.isEmpty()) {
            tvEmpty.isVisible
        } else tvEmpty.isGone
    }

    override fun onResume() {
        super.onResume()
        dbManager.openBD()
        fillAdapter()
    }

    override fun onDestroy() {
        super.onDestroy()
        dbManager.closeDB()
    }


}