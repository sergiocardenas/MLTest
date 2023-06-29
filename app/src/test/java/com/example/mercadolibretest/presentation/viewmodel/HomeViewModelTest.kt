package com.example.mercadolibretest.presentation.viewmodel

import com.example.mercadolibretest.data.utils.getMLItemResponseMock
import com.example.mercadolibretest.domain.model.MLItemModel
import com.example.mercadolibretest.domain.usecase.MLSearchUsecase
import com.example.mercadolibretest.domain.utils.getMLItemModelMock
import com.example.mercadolibretest.presentation.state.MLItemState
import com.example.mercadolibretest.presentation.utils.getMLItemStateMock
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {
    @Mock
    private lateinit var useCase: MLSearchUsecase

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        viewModel = HomeViewModel(useCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun testSearchListSuccessful(): Unit = runBlocking {
        val query = "test"

        val searchResponse = listOf(
            getMLItemModelMock(
                hasSalePrice = true,
                hasTags = true
            )
        )

        val finalList = listOf(
            getMLItemStateMock()
        )

        Mockito.`when`(useCase.getSearchResult(query))
            .thenReturn(flowOf(searchResponse))

        viewModel.searchItem(query){}

        Assert.assertEquals(viewModel.list.value.size, finalList.size)
        Assert.assertEquals(viewModel.list.value[0].id, finalList[0].id)
        Assert.assertEquals(viewModel.homeState.value.hasSearched, true)
        Assert.assertEquals(viewModel.homeState.value.query, query)
    }

    @Test
    fun testSearchListError(): Unit = runBlocking {
        val query = "test"

        val searchResponse = listOf<MLItemModel>()

        val finalList = listOf<MLItemState>()

        Mockito.`when`(useCase.getSearchResult(query))
            .thenReturn(flowOf(searchResponse))

        viewModel.searchItem(query){}

        Assert.assertEquals(viewModel.list.value.size, finalList.size)
        Assert.assertEquals(viewModel.homeState.value.hasSearched, false)
        Assert.assertEquals(viewModel.homeState.value.query, query)
    }
}