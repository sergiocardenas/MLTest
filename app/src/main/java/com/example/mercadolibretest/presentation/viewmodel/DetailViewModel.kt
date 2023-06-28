package com.example.mercadolibretest.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mercadolibretest.presentation.state.MLItemState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DetailViewModel(): ViewModel() {

    private val empty = MLItemState(
        id = "",
        title = "",
        condition = "",
        thumbnail = "",
        catalog_product_id = "",
        listing_type_id = "",
        permalink = "",
        buying_mode = "",
        site_id = "",
        category_id = "",
        domain_id = "",
        currency_id = "",
        price = -1f,
        original_price = -1f,
        sale_price = -1f,
        sold_quantity = 0,
        available_quantity = 0,
        accepts_mercadopago = false,
        tags = listOf(),
    )

    private val _item = MutableStateFlow<MLItemState>(empty)
    val item: StateFlow<MLItemState> = _item

    fun setItem(newItem: MLItemState){
        _item.value = newItem
    }
}