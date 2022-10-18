package com.example.appcuentaregresiva.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.appcuentaregresiva.databinding.ActivityMainBinding
import com.example.appcuentaregresiva.ui.viewmodels.MainViewModel
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnMinus.setOnClickListener {

        }
        binding.btnPlus.setOnClickListener {
                viewModel.increaseTime()
        }
        binding.btnStart.setOnClickListener {

        }




    }
}