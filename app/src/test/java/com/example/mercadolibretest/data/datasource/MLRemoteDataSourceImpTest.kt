package com.example.mercadolibretest.data.datasource

import com.example.mercadolibretest.data.response.MLSearchResponse
import com.example.mercadolibretest.data.service.MercadoLibreService
import com.example.mercadolibretest.data.utils.getMLItemResponseMock
import com.example.mercadolibretest.data.utils.getMLSearchResponseMock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
internal class MLRemoteDataSourceImpTest{
    @Mock
    private lateinit var service: MercadoLibreService

    private lateinit var dataSource: MLRemoteDataSource

    @Before
    fun setup() {
        dataSource = MLRemoteDataSourceImp(service)
    }

    @Test
    fun testSearchFromRemoteDataSource(): Unit = runBlocking {
        val searchResponse = getMLSearchResponseMock(
            listOf(getMLItemResponseMock(
                hasSalePrice = false,
                hasTags = false
            ))
        )

        val retrofitResponse = Response.success(searchResponse)

        Mockito.`when`(service.getSearch(Mockito.anyString())).thenReturn(retrofitResponse)

        val datasourceResult = dataSource.getSearchResult("test")
        val resultList = listOf(getMLItemResponseMock(
            hasSalePrice = false,
            hasTags = false
        ))

        datasourceResult.collect{
            assertEquals(resultList.size, it.size)
            assertEquals(resultList[0], it[0])
        }
    }
    @Test
    fun testSearchFromRemoteDataSourceFailure(): Unit = runBlocking {
        val retrofitResponse = Response.error<MLSearchResponse>(500, "Error".toResponseBody())

        Mockito.`when`(service.getSearch(Mockito.anyString())).thenReturn(retrofitResponse)

        val datasourceResult = dataSource.getSearchResult("test")

        datasourceResult.collect{
            assertTrue(it.isEmpty())
        }
    }
}
