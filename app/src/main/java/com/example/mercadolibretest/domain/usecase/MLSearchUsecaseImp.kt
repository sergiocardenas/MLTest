package com.example.mercadolibretest.domain.usecase

import com.example.mercadolibretest.data.datasource.MLRemoteDataSource
import com.example.mercadolibretest.domain.mapper.toModel
import com.example.mercadolibretest.domain.model.MLItemModel
import com.example.mercadolibretest.domain.repository.MLSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MLSearchUsecaseImp @Inject constructor(
    private val repository: MLSearchRepository
) : MLSearchUsecase {
    override suspend fun getSearchResult(query: String): Flow<List<MLItemModel>> {
        return repository.getSearchResult(query)
    }
}