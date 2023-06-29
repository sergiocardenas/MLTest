package com.example.mercadolibretest.presentation.utils

import com.example.mercadolibretest.domain.model.MLItemModel
import com.example.mercadolibretest.presentation.state.MLItemState

fun getMLItemStateMock() = MLItemState(
    id = "MLA1149572373",
    title = " Moto G5 Plus 32 Gb  Gris Lunar 2 Gb Ram",
    condition = "new",
    thumbnail = "http://http2.mlstatic.com/D_889938-MLA40645964182_022020-I.jpg",
    catalog_product_id = "MLA6353279",
    listing_type_id = "gold_special",
    permalink = "https://www.mercadolibre.com.ar/moto-g5-plus-32-gb-gris-lunar-2-gb-ram/p/MLA6353279",
    buying_mode = "buy_it_now",
    site_id = "MLA",
    category_id = "MLA1055",
    domain_id = "MLA-CELLPHONES",
    currency_id = "ARS",
    price = 108000f,
    original_price = 180000f,
    sale_price = 180000f,
    sold_quantity = 2,
    available_quantity = 1,
    accepts_mercadopago = true,
    tags = listOf(
        "extended_warranty_eligible",
        "good_quality_picture",
        "good_quality_thumbnail",
        "ahora-paid-by-buyer",
        "immediate_payment",
        "shipping_guaranteed")
)