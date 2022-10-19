package com.example.appcuentaregresiva.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel : ViewModel() {
    private var _time = MutableStateFlow(0)
    val time: StateFlow<Int> = _time

    //es un mensaje que le vamos a mostrar al usuario y como no queremos que se vuelva a mostrar cuando se rote la pantalla
    //uso un sharedFlow
    private var _startingCount = MutableSharedFlow<Int>()
    val startingCount = _startingCount.asSharedFlow()

    private var _maxProgress = MutableStateFlow<Int>(0)
    val maxProgress: StateFlow<Int> = _maxProgress

    private var _isRunning = MutableStateFlow(false)
    val isRunning: StateFlow<Boolean> = _isRunning


    fun increaseTime() {
        _time.value = _time.value.plus(1)
    }

    fun decreaseTime() {
        if (_time.value > 0) {
            _time.value = _time.value.minus(1)
        }
    }


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
        _isRunning.value = true
        _maxProgress.value = _time.value

        (_time.value downTo 0).asFlow().onEach {
            _time.value = it
            delay(1000)
        }.launchIn(viewModelScope)
    }
    fun restartTimer(){
        _isRunning.value = false
        _time.value = 0
        _maxProgress.value = 0
    }
}



