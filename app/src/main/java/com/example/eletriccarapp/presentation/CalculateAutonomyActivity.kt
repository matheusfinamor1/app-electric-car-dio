package com.example.eletriccarapp.presentation

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.eletriccarapp.R
import com.example.eletriccarapp.databinding.ActivityCalculateAutonomyBinding

class CalculateAutonomyActivity : AppCompatActivity() {

    private val binding by lazy { ActivityCalculateAutonomyBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupListener()
        setupCachedResult()
    }

    private fun setupCachedResult() {
        val valueCalculate = getSharedPref()
        binding.tvResultado.text = valueCalculate.toString()
    }

    private fun calculate() {
        val price = binding.etPrecoKwh.text.toString().toFloat()
        val km = binding.etKmPercorrido.text.toString().toFloat()
        val resultPrice = price / km
        binding.tvResultado.text = resultPrice.toString()
        saveSharedPref(resultPrice)
    }

    private fun setupListener() {
        binding.btnCalcular.setOnClickListener {
            calculate()
        }

        binding.ivClose.setOnClickListener {
            // Desempilha e volta para tela anterior
            finish()
        }
    }

    private fun saveSharedPref(result: Float) {
        // Context.MODE_PRIVATE = Preferencia privada (utilizada somente no escopo do app)
        val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putFloat(getString(R.string.saved_calc), result)
            apply()
        }
    }

    private fun getSharedPref(): Float {
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        return sharedPref.getFloat(getString(R.string.saved_calc), 0.0f)
    }

}