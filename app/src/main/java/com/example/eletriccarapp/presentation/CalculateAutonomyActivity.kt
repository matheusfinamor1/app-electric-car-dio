package com.example.eletriccarapp.presentation

import android.content.Context
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
        setupCachedResult()
    }

    private fun setupCachedResult() {
        val valueCalculate = getSharedPref()
        result.text = valueCalculate.toString()
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
        saveSharedPref(resultPrice)
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

    private fun saveSharedPref(result: Float){
        // Context.MODE_PRIVATE = Preferencia privada (utilizada somente no escopo do app)
        val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()){
            putFloat(getString(R.string.saved_calc), result)
            apply()
        }
    }

    private fun getSharedPref(): Float {
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        return sharedPref.getFloat(getString(R.string.saved_calc), 0.0f)
    }

}