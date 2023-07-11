package com.example.eletriccarapp.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.eletriccarapp.R

class MainActivity : AppCompatActivity() {

    lateinit var btnRedirectionCalculate: Button
    lateinit var list: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
        setupListeners()
        setupList()
    }

    private fun setupView() {
        btnRedirectionCalculate = findViewById(R.id.btn_redirection_calculate)
        list = findViewById(R.id.lv_list_inf)
    }

    private fun setupListeners() {
        btnRedirectionCalculate.setOnClickListener {
            startActivity(Intent(this, CalculateAutonomyActivity::class.java))
        }
    }

    private fun setupList() {
        val data = arrayOf(
            "Cupcake",
            "Donut",
            "Froyo",
            "Gingerbread",
            "Honeycomb",
            "Ice cream Sandwich",
            "Jelly Bean"
        )
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, data)
        list.adapter = adapter
    }

}