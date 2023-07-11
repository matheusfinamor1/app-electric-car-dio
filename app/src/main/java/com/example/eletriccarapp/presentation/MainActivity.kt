package com.example.eletriccarapp.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.eletriccarapp.R
import com.example.eletriccarapp.data.CarFactory
import com.example.eletriccarapp.presentation.adapter.CarAdapter

class MainActivity : AppCompatActivity() {

    lateinit var btnRedirectionCalculate: Button
    lateinit var listCars: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
        setupListeners()
        setupList()
    }

    private fun setupView() {
        btnRedirectionCalculate = findViewById(R.id.btn_redirection_calculate)
        listCars = findViewById(R.id.rv_list_cars)
    }

    private fun setupListeners() {
        btnRedirectionCalculate.setOnClickListener {
            startActivity(Intent(this, CalculateAutonomyActivity::class.java))
        }
    }

    private fun setupList() {
        val data = CarFactory.list
        val adapter = CarAdapter(data)
        listCars.adapter = adapter

    }

}