package com.example.eletriccarapp.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.eletriccarapp.R
import com.example.eletriccarapp.domain.Car


class CarAdapter(private val cars: List<Car>, private val isFavoriteScreen: Boolean = false) :
    RecyclerView.Adapter<CarAdapter.CarsViewHolder>() {

    var carItemListener: (Car) -> Unit = {}

    /**
     * Cria uma nova view.
     * É onde coloca a implementação para recuperar o layout que foi criado.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarsViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.car_item, parent, false)
        return CarsViewHolder(view)
    }

    /**
     * Quantidade de itens da lista
     */
    override fun getItemCount(): Int = cars.size

    /**
     * Pega o conteudo da view e troca pela informacao de item de uma lista
     */
    override fun onBindViewHolder(holder: CarsViewHolder, position: Int) {
        holder.price.text = cars[position].preco
        holder.battery.text = cars[position].bateria
        holder.power.text = cars[position].potencia
        holder.recharge.text = cars[position].recarga
        if (isFavoriteScreen) {
            holder.favorite.setImageResource(R.drawable.ic_star_selected)
        }
        holder.favorite.setOnClickListener {
            val car = cars[position]
            carItemListener(car)
            setupFavorite(car, holder)
        }
    }

    private fun setupFavorite(
        car: Car,
        holder: CarsViewHolder
    ) {
        car.isFavorite = !car.isFavorite

        if (car.isFavorite) {
            holder.favorite.setImageResource(R.drawable.ic_star_selected)
        } else {
            holder.favorite.setImageResource(R.drawable.ic_star)
        }

        if(isFavoriteScreen){
            holder.favorite.setImageResource(R.drawable.ic_star)
        }
    }

    /**
     * ViewHolder: Pega cada item e coloca na tela, ele quem gerencia.
     */
    class CarsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val price: TextView
        val battery: TextView
        val power: TextView
        val recharge: TextView
        val favorite: ImageView

        init {
            view.apply {
                price = findViewById(R.id.tv_preco_value)
                battery = findViewById(R.id.tv_bateria_value)
                power = findViewById(R.id.tv_potencia_value)
                recharge = findViewById(R.id.tv_recarga_value)
                favorite = findViewById(R.id.iv_favorite)
            }

        }

    }
}



