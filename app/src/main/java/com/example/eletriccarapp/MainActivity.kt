package com.example.eletriccarapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var price: EditText
    lateinit var btnCalculate: Button
    lateinit var kmTraveled: EditText
    lateinit var result: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
        setupListeners()
    }

    private fun setupView() {
        price = findViewById(R.id.et_preco_kwh)
        btnCalculate = findViewById(R.id.btn_calcular)
        kmTraveled = findViewById(R.id.et_km_percorrido)
        result = findViewById(R.id.tv_resultado)

    }

    private fun setupListeners() {
        btnCalculate.setOnClickListener {
            calculate()
        }
    }

    private fun calculate() {
        val price = price.text.toString().toFloat()
        val km = kmTraveled.text.toString().toFloat()
        val resultPrice = price / km
        result.text = resultPrice.toString()
    }


}