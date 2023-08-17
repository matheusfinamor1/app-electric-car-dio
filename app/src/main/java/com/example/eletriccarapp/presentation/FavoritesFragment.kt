package com.example.eletriccarapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.eletriccarapp.data.local.CarRepository
import com.example.eletriccarapp.databinding.FavoritesFragmentBinding
import com.example.eletriccarapp.domain.Car
import com.example.eletriccarapp.presentation.adapter.CarAdapter

class FavoritesFragment : Fragment() {

    private var _binding: FavoritesFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FavoritesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
        binding.rvListCarsFavorite.apply {
            isVisible = true
            adapter = carAdapter
        }

        carAdapter.carItemListener = { car ->
            CarRepository(requireContext()).deleteCarById(car)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}