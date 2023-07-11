package com.example.eletriccarapp.presentation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.eletriccarapp.R
import com.example.eletriccarapp.data.CarFactory
import com.example.eletriccarapp.presentation.adapter.CarAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CarFragment: Fragment() {

    lateinit var fabRedirectionCalculate: FloatingActionButton
    lateinit var listCars: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.car_fragment, container,false)
    }

    /**
     * Quando o android ja conseguiu acabar de desenhar a tela para o usuario
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
        setupList()
        setupListeners()
    }

    fun setupView(view: View){
        fabRedirectionCalculate = view.findViewById(R.id.fab_calculate)
        listCars = view.findViewById(R.id.rv_list_cars)
    }

    private fun setupList() {
        val data = CarFactory.list
        val adapter = CarAdapter(data)
        listCars.adapter = adapter

    }

    private fun setupListeners() {
        fabRedirectionCalculate.setOnClickListener {
            startActivity(Intent(context, CalculateAutonomyActivity::class.java))
        }
    }
}