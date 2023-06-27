package com.example.mercadolibretest.data.di

import com.example.mercadolibretest.data.RemoteConstants.BASE_URL
import com.example.mercadolibretest.data.datasource.MLRemoteDataSource
import com.example.mercadolibretest.data.datasource.MLRemoteDataSourceImp
import com.example.mercadolibretest.data.service.MercadoLibreService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create()


    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit): MercadoLibreService =
        retrofit.create(MercadoLibreService::class.java)

    @Singleton
    @Provides
    fun provideRemoteDataSource(service: MercadoLibreService): MLRemoteDataSource =
        MLRemoteDataSourceImp(service)
}