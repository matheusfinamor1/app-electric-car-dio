package com.example.eletriccarapp.presentation

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.eletriccarapp.R

class CalculateAutonomyActivity : AppCompatActivity() {

    lateinit var price: EditText
    lateinit var kmTraveled: EditText
    lateinit var result: TextView
    lateinit var btnCalculate: Button
    lateinit var btnClose: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculate_autonomy)
        setupView()
        setupListener()
    }

    private fun setupView() {
        price = findViewById(R.id.et_preco_kwh)
        kmTraveled = findViewById(R.id.et_km_percorrido)
        result = findViewById(R.id.tv_resultado)
        btnCalculate = findViewById(R.id.btn_calcular)
        btnClose = findViewById(R.id.iv_close)
    }

    private fun calculate() {
        val price = price.text.toString().toFloat()
        val km = kmTraveled.text.toString().toFloat()
        val resultPrice = price / km
        result.text = resultPrice.toString()
    }

    private fun setupListener(){
        btnCalculate.setOnClickListener {
            calculate()
        }

        btnClose.setOnClickListener {
            // Desempilha e volta para tela anterior
            finish()
        }
    }

}