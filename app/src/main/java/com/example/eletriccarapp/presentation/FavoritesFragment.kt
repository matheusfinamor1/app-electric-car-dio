package com.example.eletriccarapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.eletriccarapp.R
import com.example.eletriccarapp.data.local.CarRepository
import com.example.eletriccarapp.domain.Car
import com.example.eletriccarapp.presentation.adapter.CarAdapter

class FavoritesFragment: Fragment() {

    private lateinit var listCarsFavorite: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.favorites_fragment, container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
        setupList()
    }

    private fun getCarsOnLocalDb(): List<Car> {
        val repository = CarRepository(requireContext())
        val carList = repository.getAll()
        return carList
    }

    private fun setupList() {
        val carList = getCarsOnLocalDb()
        val carAdapter = CarAdapter(carList, isFavoriteScreen = true)
        listCarsFavorite.apply {
            isVisible = true
            adapter = carAdapter
        }

        carAdapter.carItemListener = { car ->
            CarRepository(requireContext()).deleteCarById(car)
        }
    }
    private fun setupView(view: View) {
        listCarsFavorite = view.findViewById(R.id.rv_list_cars_favorite)
    }
}