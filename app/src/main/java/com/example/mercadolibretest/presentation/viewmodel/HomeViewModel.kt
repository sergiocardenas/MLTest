package com.example.mercadolibretest.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mercadolibretest.domain.usecase.MLSearchUsecase
import com.example.mercadolibretest.presentation.mapper.toState
import com.example.mercadolibretest.presentation.state.MLHomeState
import com.example.mercadolibretest.presentation.state.MLItemState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.processor.internal.definecomponent.codegen._dagger_hilt_android_components_ActivityComponent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel  @Inject constructor(
    private val useCase: MLSearchUsecase
) : ViewModel()   {

    private val _list = MutableStateFlow<MutableList<MLItemState>>(mutableListOf())
    val list: StateFlow<MutableList<MLItemState>> = _list
    private val _homeState = MutableStateFlow(MLHomeState())
    val homeState: StateFlow<MLHomeState> = _homeState
    val query = MutableStateFlow<String>("")

    fun searchItem(item: String){
        if(item.isNotEmpty()){
            viewModelScope.launch {
                useCase.getSearchResult(item).collect{ searchResult ->
                    if(searchResult.isNotEmpty()){
                        _homeState.value = MLHomeState(true, item)
                        _list.value = searchResult.map { it.toState() }.toMutableList()
                    }else{
                        _list.value = mutableListOf()
                    }
                }
            }
        }
    }

    fun resetHomeState(){
        if(_homeState.value.hasSearched){
            _homeState.value = MLHomeState()
        }
    }
}