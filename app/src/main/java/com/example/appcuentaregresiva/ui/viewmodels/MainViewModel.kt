package com.example.appcuentaregresiva.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

class MainViewModel : ViewModel() {
    //es un mensaje que le vamos a mostrar al usuario y como no queremos que se vuelva a mostrar cuando se rote la pantalla
    //uso un sharedFlow
    private var _startingCount = MutableSharedFlow<Int>()
    val startingCount = _startingCount.asSharedFlow()

    fun displayStartingCount() {
        (3 downTo 0).asFlow().onEach {
            if (it > 0) {
                _startingCount.emit(it)
                delay(1000)

            } else {
                startTimer()
            }
        }.launchIn(viewModelScope)//este flow lo voy a estar emitiendo en el viewModelScope
    }

    private fun startTimer() {

    }
}