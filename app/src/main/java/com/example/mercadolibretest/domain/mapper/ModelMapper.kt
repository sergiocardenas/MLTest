package com.example.mercadolibretest.domain.mapper

import com.example.mercadolibretest.data.response.MLItemResponse
import com.example.mercadolibretest.domain.model.MLItemModel

fun MLItemResponse.toModel() = MLItemModel(
    id = id,
    title = title,
    condition = condition,
    thumbnail = thumbnail,
    catalog_product_id = catalog_product_id?:"",
    listing_type_id = listing_type_id,
    permalink = permalink,
    buying_mode = buying_mode,
    site_id = site_id,
    category_id = category_id,
    domain_id = domain_id,
    currency_id = currency_id,
    price = price,
    original_price = original_price?:-1f,
    sale_price = sale_price?:-1f,
    sold_quantity = sold_quantity,
    available_quantity = available_quantity,
    accepts_mercadopago = accepts_mercadopago,
    tags = tags?: listOf(),
)