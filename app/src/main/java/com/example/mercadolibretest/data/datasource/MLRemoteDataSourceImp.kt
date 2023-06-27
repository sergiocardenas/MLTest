package com.example.mercadolibretest.data.datasource

import com.example.mercadolibretest.data.response.MLItemResponse
import com.example.mercadolibretest.data.service.MercadoLibreService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MLRemoteDataSourceImp @Inject constructor(
    private val service: MercadoLibreService
) : MLRemoteDataSource{
    override suspend fun getSearchResult(query: String): Flow<List<MLItemResponse>> = flow {
        val list = withContext(Dispatchers.IO) {
            var searchList: List<MLItemResponse> = listOf()
            val result = service.getSearch(query)
            if(result.isSuccessful){
                service.getSearch(query).body()?.let {
                    searchList = it.results
                }
            }
            searchList
        }
        emit(list)
    }
}