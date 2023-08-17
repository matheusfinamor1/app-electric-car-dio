package com.example.eletriccarapp.presentation

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.eletriccarapp.R
import com.example.eletriccarapp.data.CarsApi
import com.example.eletriccarapp.data.local.CarRepository
import com.example.eletriccarapp.databinding.CarFragmentBinding
import com.example.eletriccarapp.domain.Car
import com.example.eletriccarapp.presentation.adapter.CarAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CarFragment : Fragment() {

    private var _binding: CarFragmentBinding? = null
    private val binding get() = _binding!!

    lateinit var carsApi: CarsApi

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CarFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * Quando o android ja conseguiu acabar de desenhar a tela para o usuario
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRetrofit()
    }

    override fun onResume() {
        super.onResume()
        if (checkForInternet(context)) {
            getAllCars()
        } else {
            emptyState()
        }
    }

    private fun getAllCars() {
        carsApi.getAllCars().enqueue(object : Callback<List<Car>> {
            override fun onResponse(call: Call<List<Car>>, response: Response<List<Car>>) {
                if (response.isSuccessful) {
                    binding.pbLoader.isVisible = false
                    binding.tvNoWifi.isVisible = false
                    binding.ivEmptyState.isVisible = false
                    response.body()?.let { listCars ->
                        setupList(listCars)
                    }
                } else {
                    Toast.makeText(context, R.string.response_error, Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<List<Car>>, t: Throwable) {
                Toast.makeText(context, R.string.response_error, Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun emptyState() {
        binding.pbLoader.isVisible = false
        binding.rvListCars.isVisible = false
        binding.ivEmptyState.isVisible = true
        binding.tvNoWifi.isVisible = true
    }

    private fun setupRetrofit() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://igorbag.github.io/cars-api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        carsApi = retrofit.create(CarsApi::class.java)
    }

    private fun setupList(list: List<Car>) {
        val carAdapter = CarAdapter(list)
        binding.rvListCars.apply {
            isVisible = true
            adapter = carAdapter
        }

        carAdapter.carItemListener = { car ->
            CarRepository(requireContext()).saveIfNotExist(car)
        }

    }


    private fun checkForInternet(context: Context?): Boolean {
        val connectivityManager =
            context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            @Suppress("DEPRECATION")
            val networkInfo = connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}