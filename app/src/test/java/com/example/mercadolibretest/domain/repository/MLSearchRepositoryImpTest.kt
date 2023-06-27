package com.example.mercadolibretest.domain.repository

import com.example.mercadolibretest.data.datasource.MLRemoteDataSource
import com.example.mercadolibretest.data.datasource.MLRemoteDataSourceImp
import com.example.mercadolibretest.data.service.MercadoLibreService
import com.example.mercadolibretest.data.utils.getMLItemResponseMock
import com.example.mercadolibretest.data.utils.getMLSearchResponseMock
import com.example.mercadolibretest.domain.utils.getMLItemModelMock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
internal class MLSearchRepositoryImpTest {
    @Mock
    private lateinit var remoteDataSource: MLRemoteDataSource

    private lateinit var repository: MLSearchRepository

    @Before
    fun setup() {
        repository = MLSearchRepositoryImp(remoteDataSource)
    }

    @Test
    fun testSearchFromRepositoryWithSoldPrice(): Unit = runBlocking {
        val searchResponse = listOf(
                getMLItemResponseMock(
                    hasSalePrice = true,
                    hasTags = true
                )
            )

        Mockito.`when`(remoteDataSource.getSearchResult(Mockito.anyString()))
            .thenReturn(flowOf(searchResponse))

        val searchResult = repository.getSearchResult(Mockito.anyString())
        val listResult = listOf(
            getMLItemModelMock(
                hasSalePrice = true,
                hasTags = true
            )
        )

        searchResult.collect{
            Assert.assertEquals(listResult.size, it.size)
            Assert.assertEquals(listResult[0].sale_price, it[0].sale_price)
            Assert.assertEquals(listResult[0].original_price, it[0].original_price)
            Assert.assertEquals(listResult[0].tags.size, it[0].tags.size)
        }

    }

    @Test
    fun testSearchFromRepositoryWithoutSoldPrice(): Unit = runBlocking {
        val searchResponse = listOf(
            getMLItemResponseMock(
                hasSalePrice = false,
                hasTags = false
            )
        )

        Mockito.`when`(remoteDataSource.getSearchResult(Mockito.anyString()))
            .thenReturn(flowOf(searchResponse))

        val searchResult = repository.getSearchResult(Mockito.anyString())
        val listResult = listOf(
            getMLItemModelMock(
                hasSalePrice = false,
                hasTags = false
            )
        )

        searchResult.collect{
            Assert.assertEquals(listResult.size, it.size)
            Assert.assertEquals(listResult[0].sale_price, -1)
            Assert.assertEquals(listResult[0].original_price, -1)
            Assert.assertEquals(listResult[0].tags.size, 0)
        }

    }
}