package com.example.eletriccarapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.eletriccarapp.R
import com.example.eletriccarapp.presentation.adapter.TabsAdapter
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {


    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
        setupTabs()
    }

    private fun setupView() {
        tabLayout = findViewById(R.id.tab_layout)
        viewPager = findViewById(R.id.vp_view_pager)
    }

    private fun setupTabs(){
        val tabsAdapter = TabsAdapter(this)
        viewPager.adapter = tabsAdapter
        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {tabLayout ->
                    viewPager.currentItem = tabLayout.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
        viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.getTabAt(position)?.select()
            }
        })
    }

}