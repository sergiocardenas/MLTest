package com.example.mercadolibretest.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mercadolibretest.presentation.state.MLItemState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class NavViewModel(): ViewModel() {
    private val _item = MutableStateFlow<MLItemState?>(null)
    val item: StateFlow<MLItemState?> = _item

    fun clearState(){
        _item.value = null
    }

    fun passItem(item: MLItemState){
        _item.value = item
    }
}