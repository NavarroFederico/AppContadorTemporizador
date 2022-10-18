package com.example.appcuentaregresiva.ui.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel : ViewModel() {

    private var _time = MutableStateFlow(0)
    val time: StateFlow<Int> = _time


    fun increaseTime() {
        _time.value = _time.value.plus(1)
    }

    fun decreaseTime() {
        if (_time.value > 0) {
            _time.value = _time.value.minus(1)
        }
    }
}
