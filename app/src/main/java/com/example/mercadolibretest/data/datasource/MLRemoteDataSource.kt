package com.example.mercadolibretest.data.datasource

import com.example.mercadolibretest.data.response.MLItemResponse
import kotlinx.coroutines.flow.Flow

interface MLRemoteDataSource {
    suspend fun getSearchResult(query: String): Flow<List<MLItemResponse>>
}