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
            viewModel.displayStartingCount()
        }

        //asignacion del progreso maximo al indicador max en el layout
        lifecycleScope.launchWhenStarted {
            viewModel.maxProgress.collect {
                binding.circularProgressIndicator.max = it
            }
        }
        //deshabilitar los botones cuando el contador este running y setear a 100 elcirculo de progreso
        lifecycleScope.launchWhenStarted {
            viewModel.isRunning.collect() { isRunning ->
                if (isRunning) {
                    binding.btnPlus.isEnabled = false
                    binding.btnMinus.isEnabled = false
                    binding.btnStart.isEnabled = false
                } else {
                    binding.btnPlus.isEnabled = true
                    binding.btnMinus.isEnabled = true
                    binding.btnStart.isEnabled = true

                    binding.circularProgressIndicator.max = 100
                    binding.circularProgressIndicator.progress = 100
                }
            }
        }
        //que se actualice el valor del contador time en pantalla
        lifecycleScope.launchWhenStarted {
            viewModel.time.collect() { timeValue ->
                binding.tvContador.text = timeValue.toString()

                if (timeValue == 0) {
                    viewModel.restartTimer()
                }

                if (!viewModel.isRunning.value){
                    binding.btnStart.isEnabled = timeValue > 0
                }else{
                    binding.circularProgressIndicator.progress = timeValue
                }
            }
        }

    }
}