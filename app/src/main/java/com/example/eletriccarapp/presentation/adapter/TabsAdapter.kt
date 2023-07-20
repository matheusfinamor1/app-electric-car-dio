package com.example.eletriccarapp.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.eletriccarapp.presentation.CarFragment
import com.example.eletriccarapp.presentation.FavoritesFragment

class TabsAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    /**
     * Retorna o numero de tabs (carros e favoritos).
     */
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CarFragment()
            1 -> FavoritesFragment()
            else -> CarFragment()
        }
    }
}