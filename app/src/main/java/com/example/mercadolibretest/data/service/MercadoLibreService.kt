package com.example.mercadolibretest.data.service

import com.example.mercadolibretest.data.RemoteConstants.SEARCH_ENDPOINT
import com.example.mercadolibretest.data.response.MLSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MercadoLibreService {

    @GET(SEARCH_ENDPOINT)
    suspend fun getSearch(@Query("q") item: String): Response<MLSearchResponse>
}