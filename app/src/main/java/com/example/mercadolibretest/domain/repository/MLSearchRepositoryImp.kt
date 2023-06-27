package com.example.mercadolibretest.domain.repository

import com.example.mercadolibretest.data.datasource.MLRemoteDataSource
import com.example.mercadolibretest.domain.mapper.toModel
import com.example.mercadolibretest.domain.model.MLItemModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MLSearchRepositoryImp @Inject constructor(
    private val remote: MLRemoteDataSource
) : MLSearchRepository {
    override suspend fun getSearchResult(query: String): Flow<List<MLItemModel>> {
        return remote.getSearchResult(query).map { list ->
            list.map { itemResponse ->
                itemResponse.toModel()
            }
        }
    }
}