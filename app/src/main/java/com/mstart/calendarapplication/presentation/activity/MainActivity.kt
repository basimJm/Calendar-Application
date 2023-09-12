package com.mstart.calendarapplication.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.mstart.calendarapplication.R
import com.mstart.calendarapplication.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initNavigation()
    }

    private fun initNavigation() {
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home_page -> {
                    val navigator = findNavController(R.id.fragment_container)
                    navigator.navigate(R.id.calendarFragment)
                    true
                }

                R.id.event_page -> {
                    val navigator = findNavController(R.id.fragment_container)
                    navigator.navigate(R.id.eventFragment)
                    true
                }

                else -> false
            }
        }
    }
}
