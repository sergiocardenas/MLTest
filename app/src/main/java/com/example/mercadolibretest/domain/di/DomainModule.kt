package com.example.mercadolibretest.domain.di

import com.example.mercadolibretest.data.datasource.MLRemoteDataSource
import com.example.mercadolibretest.domain.repository.MLSearchRepository
import com.example.mercadolibretest.domain.repository.MLSearchRepositoryImp
import com.example.mercadolibretest.domain.usecase.MLSearchUsecase
import com.example.mercadolibretest.domain.usecase.MLSearchUsecaseImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Singleton
    @Provides
    fun provideMLRepository(remote: MLRemoteDataSource): MLSearchRepository =
        MLSearchRepositoryImp(remote)

    @Singleton
    @Provides
    fun provideMLSearchUseCase(repository: MLSearchRepository): MLSearchUsecase =
        MLSearchUsecaseImp(repository)
}