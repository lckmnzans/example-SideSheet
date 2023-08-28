package com.simple.sidesheet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    private val _optionSelected = MutableLiveData<Int>()
    val optionSelected: LiveData<Int> = _optionSelected

    fun setOptionSelected(option: Int) {
        _optionSelected.value = option
    }
}