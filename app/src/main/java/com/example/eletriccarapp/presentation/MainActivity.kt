package com.example.eletriccarapp.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.eletriccarapp.R

class MainActivity : AppCompatActivity() {

    lateinit var btnRedirectionCalculate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
        setupListeners()
    }

    private fun setupView() {
        btnRedirectionCalculate = findViewById(R.id.btn_redirection_calculate)
    }

    private fun setupListeners() {
        btnRedirectionCalculate.setOnClickListener {
            startActivity(Intent(this, CalculateAutonomyActivity::class.java))
        }
    }

}