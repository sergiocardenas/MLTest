package com.example.mercadolibretest.domain.usecase

import com.example.mercadolibretest.data.response.MLItemResponse
import com.example.mercadolibretest.domain.model.MLItemModel
import kotlinx.coroutines.flow.Flow

interface MLSearchUsecase {
    suspend fun getSearchResult(query: String): Flow<List<MLItemModel>>
}